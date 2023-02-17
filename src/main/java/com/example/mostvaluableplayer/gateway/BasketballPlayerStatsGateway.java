package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.model.BasketballPlayer;
import org.springframework.stereotype.Service;

@Service
public class BasketballPlayerStatsGateway implements PlayerStatsGateway<BasketballPlayer> {

    @Override
    public BasketballPlayer convertToPlayerStats(String line) {
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
