package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BasketballPlayerStatsGateway implements PlayerStatsGateway<BasketballPlayer> {

    private static final Pattern basketballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+;\\d+");

    @Override
    public BasketballPlayer convertToPlayerStats(String line) {

        String[] split = line.split(";");
        Matcher mtch = basketballPlayerPattern.matcher(line);
        if (!mtch.matches()) {
            throw new NotValidLineException("Line " + line + " is not valid");
        }
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
