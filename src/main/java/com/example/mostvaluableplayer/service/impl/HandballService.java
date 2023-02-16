package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.HandballPlayerStats;
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
public class HandballService implements GameService {

    private final PlayerStatsService<HandballPlayerStats> handballPlayerPlayerStatsService;
    private final TeamServiceImpl teamService;

    public List<PlayerStats> calculateRating(FileDTO fileDTO) {

        List<HandballPlayerStats> players = handballPlayerPlayerStatsService.getPlayerStats(fileDTO.getLines());
        List<PlayerStats> ratedPlayerStats = new ArrayList<>();
        String winner = defineWinners(players);
        for (HandballPlayerStats player :
                players) {
            calculateGameRating(player, winner);
            ratedPlayerStats.add(player);
        }
        return ratedPlayerStats;
    }

    public String defineWinners(List<HandballPlayerStats> players) {
        var teamScoreMap = players.stream()
                .collect(Collectors.groupingBy(PlayerStats::getTeamName, Collectors.summingInt(HandballPlayerStats::getGoalMade)));

        return teamService.getWinner(teamScoreMap);
    }

    public void calculateGameRating(HandballPlayerStats player, String winnerTeam) {
        int rating = player.getGoalMade() * 2
                - player.getGoalReceive();
        if (player.getTeamName().equals(winnerTeam)) {
            rating += 10;
        }
        player.setRating(rating);

    }
}
