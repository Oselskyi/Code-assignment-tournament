package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.gateway.PlayerStatsGateway;
import com.example.mostvaluableplayer.model.*;
import com.example.mostvaluableplayer.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HandballService implements GameService {

    private final PlayerStatsGateway<HandballPlayer> handballPlayerPlayerStatsGateway;
    private final TeamService teamService;

    private static final SportType SPORT_TYPE = SportType.HANDBALL;

    @Override
    public SportType getType() {
        return SPORT_TYPE;
    }

    @Override
    public List<Sportsman> calculateRatingForEveryPlayer(GameStats gameStats) {

        List<HandballPlayer> handballPlayers = handballPlayerPlayerStatsGateway.parseGameStatsToPlayers(gameStats.getLines());
        String winner = defineWinners(handballPlayers);

        return handballPlayers.stream()
                .map(player -> new Sportsman(player.getNickname(), calculateGameRating(player, winner)))
                .collect(Collectors.toList());
    }

    private String defineWinners(List<HandballPlayer> playerStatsList) {
        var teamScoreMap = playerStatsList.stream()
                .collect(Collectors.groupingBy(Player::getTeamName, Collectors.summingInt(HandballPlayer::getGoalMade)));

        return teamService.getWinner(teamScoreMap);
    }

    public int calculateGameRating(HandballPlayer player, String winnerTeam) {
        int rating = player.getGoalMade() * 2 - player.getGoalReceive();
        if (player.getTeamName().equals(winnerTeam)) {
            rating += 10;
        }
        return rating;

    }
}
