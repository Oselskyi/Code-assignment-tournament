package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.HandballPlayer;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.service.GameService;
import com.example.mostvaluableplayer.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HandballService implements GameService {

    private final PlayerService<HandballPlayer> handballPlayerPlayerService;

    public HandballService(PlayerService<HandballPlayer> handballPlayerPlayerService) {
        this.handballPlayerPlayerService = handballPlayerPlayerService;
    }

    public List<Player> calculateRating(FileDTO fileDTO) {

        List<HandballPlayer> players = handballPlayerPlayerService.getPlayers(fileDTO.getLines());
        List<Player> ratedPlayers = new ArrayList<>();
        String winner = defineWinners(players);
        for (HandballPlayer player:
                players) {
            calculateGameRating(player, winner);
            ratedPlayers.add(player);
        }
        return ratedPlayers;
    }

    public String defineWinners(List<HandballPlayer> players) {
        String winner = "";
        int maxScore = 0;
        var teamScoreMap = players.stream()
                .collect(Collectors.groupingBy(Player::getTeamName, Collectors.summingInt(HandballPlayer::getGoalMade)));

        for (String team :
                teamScoreMap.keySet()) {
            Integer score = teamScoreMap.get(team);
            if (score > maxScore) {
                winner = team;
                maxScore = score;
            }
        }
        return winner;
    }

    public void calculateGameRating(HandballPlayer player, String winnerTeam) {
        int rating = player.getGoalMade() * 2
                - player.getGoalReceive();
        if (player.getTeamName().equals(winnerTeam)){
            rating += 10;
        }
        player.setRating(rating);

    }
}
