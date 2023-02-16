package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.HandballPlayer;
import com.example.mostvaluableplayer.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandballPlayerService implements PlayerService<HandballPlayer> {

    @Override
    public List<HandballPlayer> getPlayers(List<String> lines) {
        List<HandballPlayer> handballPlayers = new ArrayList<>();
        for (String line : lines) {
            handballPlayers.add(parseStatsToPlayer(line));
        }
        return handballPlayers;
    }

    private HandballPlayer parseStatsToPlayer(String line) {
        String[] split = line.split(";");
        HandballPlayer player = new HandballPlayer();
        player.setName(split[0]);
        player.setNickname(split[1]);
        player.setNumber(Integer.parseInt(split[2]));
        player.setTeamName(split[3]);
        player.setGoalMade(Integer.parseInt(split[4]));
        player.setGoalReceive(Integer.parseInt(split[5]));
        return player;
    }
}
