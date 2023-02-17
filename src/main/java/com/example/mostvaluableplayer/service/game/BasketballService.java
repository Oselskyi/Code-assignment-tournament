package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.gateway.PlayerStatsGateway;
import com.example.mostvaluableplayer.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketballService implements GameService<BasketballPlayer> {

    private final PlayerStatsGateway<BasketballPlayer> basketballPlayerStatsGateway;
    private final TeamService teamService;

    @Override
    public List<Sportsman> calculateRatingForEveryPlayer(GameStats gameStats) {

        List<BasketballPlayer> basketballPlayers = basketballPlayerStatsGateway.parseGameStatsToPlayers(gameStats.getLines());
        String winner = defineWinners(basketballPlayers);

        return basketballPlayers.stream()
                .map(player -> new Sportsman(player.getNickname(), calculateGameRating(player, winner)))
                .collect(Collectors.toList());
    }

    private String defineWinners(List<BasketballPlayer> players) {

        var teamScoreMap = players.stream()
                .collect(Collectors.groupingBy(Player::getTeamName, Collectors.summingInt(BasketballPlayer::getScoredPoints)));

        return teamService.getWinner(teamScoreMap);
    }

    public int calculateGameRating(BasketballPlayer player, String winnerTeam) {
        int rating = player.getScoredPoints() * 2
                + player.getAssist()
                + player.getRebounds();
        if (player.getTeamName().equals(winnerTeam)) {
            rating += 10;
        }
        return rating;

    }

}
