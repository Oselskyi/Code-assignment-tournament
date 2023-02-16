package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.BasketballPlayerStats;
import com.example.mostvaluableplayer.service.PlayerStatsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketballPlayerStatsService implements PlayerStatsService<BasketballPlayerStats> {

    @Override
    public List<BasketballPlayerStats> getPlayerStats(List<String> lines) {
        List<BasketballPlayerStats> basketballPlayers = new ArrayList<>();
        for (String line : lines) {
            basketballPlayers.add(parseLinesToPlayerStats(line));
        }
        return basketballPlayers;
    }

    private BasketballPlayerStats parseLinesToPlayerStats(String line) {
        String[] split = line.split(";");
        BasketballPlayerStats player = new BasketballPlayerStats();
        player.setName(split[0]);
        player.setNickname(split[1]);
        player.setNumber(Integer.parseInt(split[2]));
        player.setTeamName(split[3]);
        player.setScoredPoints(Integer.parseInt(split[4]));
        player.setRebounds(Integer.parseInt(split[5]));
        player.setAssist(Integer.parseInt(split[6]));
        return player;
    }
}
