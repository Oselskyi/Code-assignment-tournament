package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.HandballPlayer;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HandballPlayerStatsGateway implements PlayerStatsGateway<HandballPlayer> {

    private static final Pattern handballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+");

    @Override
    public HandballPlayer convertToPlayerStats(String line) {

        Matcher mtch = handballPlayerPattern.matcher(line);
        if (!mtch.matches()){
            throw new NotValidLineException("Line " + line + " is not valid");
        }
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
