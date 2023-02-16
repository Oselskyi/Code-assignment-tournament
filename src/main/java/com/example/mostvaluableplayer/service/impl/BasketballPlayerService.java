package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketballPlayerService implements PlayerService<BasketballPlayer> {

    @Override
    public List<BasketballPlayer> getPlayers(List<String> lines) {
        List<BasketballPlayer> basketballPlayers = new ArrayList<>();
        for (String line : lines) {
            basketballPlayers.add(parseStatsToPlayer(line));
        }
        return basketballPlayers;
    }

    private BasketballPlayer parseStatsToPlayer(String line) {
        String[] split = line.split(";");
        BasketballPlayer player = new BasketballPlayer();
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
