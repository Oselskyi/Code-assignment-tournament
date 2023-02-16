package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.service.FileService;
import com.example.mostvaluableplayer.service.GameService;
import com.example.mostvaluableplayer.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketballService implements GameService {


    private final PlayerService<BasketballPlayer> basketballPlayerService;


    public BasketballService(PlayerService<BasketballPlayer> basketballPlayerService) {
        this.basketballPlayerService = basketballPlayerService;
    }

    public List<Player> calculateRating(FileDTO fileDTO) {

        List<BasketballPlayer> players = basketballPlayerService.getPlayers(fileDTO.getLines());
        List<Player> ratedPlayers = new ArrayList<>();
        String winner = defineWinners(players);
        for (BasketballPlayer player :
                players) {
            calculateGameRating(player, winner);
            ratedPlayers.add(player);
        }
        return ratedPlayers;
    }

    public String defineWinners(List<BasketballPlayer> players) {
        String winner = "";
        int maxScore = 0;
        var teamScoreMap = players.stream()
                .collect(Collectors.groupingBy(Player::getTeamName, Collectors.summingInt(BasketballPlayer::getScoredPoints)));

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

    public void calculateGameRating(BasketballPlayer player, String winnerTeam) {
        int rating = player.getScoredPoints() * 2
                + player.getAssist()
                + player.getRebounds();
        if (player.getTeamName().equals(winnerTeam)) {
            rating += 10;
        }
        player.setRating(rating);

    }

}
