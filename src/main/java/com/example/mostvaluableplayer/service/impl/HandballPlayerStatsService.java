package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.HandballPlayerStats;
import com.example.mostvaluableplayer.service.PlayerStatsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandballPlayerStatsService implements PlayerStatsService<HandballPlayerStats> {

    @Override
    public List<HandballPlayerStats> getPlayerStats(List<String> lines) {
        List<HandballPlayerStats> handballPlayers = new ArrayList<>();
        for (String line : lines) {
            handballPlayers.add(parseLinesToPlayerStats(line));
        }
        return handballPlayers;
    }

    private HandballPlayerStats parseLinesToPlayerStats(String line) {
        String[] split = line.split(";");
        HandballPlayerStats player = new HandballPlayerStats();
        player.setName(split[0]);
        player.setNickname(split[1]);
        player.setNumber(Integer.parseInt(split[2]));
        player.setTeamName(split[3]);
        player.setGoalMade(Integer.parseInt(split[4]));
        player.setGoalReceive(Integer.parseInt(split[5]));
        return player;
    }
}
