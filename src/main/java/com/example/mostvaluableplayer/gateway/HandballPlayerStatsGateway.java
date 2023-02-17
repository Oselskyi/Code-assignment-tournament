package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.model.HandballPlayer;
import org.springframework.stereotype.Service;

@Service
public class HandballPlayerStatsGateway implements PlayerStatsGateway<HandballPlayer> {

    @Override
    public HandballPlayer convertToPlayerStats(String line) {
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
