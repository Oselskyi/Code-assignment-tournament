package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.BasketballPlayerStats;
import com.example.mostvaluableplayer.model.PlayerStats;
import com.example.mostvaluableplayer.service.GameService;
import com.example.mostvaluableplayer.service.PlayerStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketballService implements GameService {

    private final PlayerStatsService<BasketballPlayerStats> basketballPlayerStatsService;
    private final TeamServiceImpl teamService;

    public List<PlayerStats> calculateRating(FileDTO fileDTO) {

        List<BasketballPlayerStats> players = basketballPlayerStatsService.getPlayerStats(fileDTO.getLines());
        List<PlayerStats> ratedPlayerStats = new ArrayList<>();
        String winner = defineWinners(players);
        for (BasketballPlayerStats player :
                players) {
            calculateGameRating(player, winner);
            ratedPlayerStats.add(player);
        }
        return ratedPlayerStats;
    }

    public String defineWinners(List<BasketballPlayerStats> players) {

        var teamScoreMap = players.stream()
                .collect(Collectors.groupingBy(PlayerStats::getTeamName, Collectors.summingInt(BasketballPlayerStats::getScoredPoints)));

        return teamService.getWinner(teamScoreMap);
    }

    public void calculateGameRating(BasketballPlayerStats player, String winnerTeam) {
        int rating = player.getScoredPoints() * 2
                + player.getAssist()
                + player.getRebounds();
        if (player.getTeamName().equals(winnerTeam)) {
            rating += 10;
        }
        player.setRating(rating);

    }

}
