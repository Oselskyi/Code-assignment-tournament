package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BasketballPlayerStatsGateway implements PlayerStatsGateway<BasketballPlayer> {

    private static final Pattern basketballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+;\\d+");

    @Override
    public List<BasketballPlayer> parseGameStatsToPlayers(List<String> lines) {

        return lines.stream().map(this::convertToPlayerStats)
                .collect(Collectors.toList());
    }

    private BasketballPlayer convertToPlayerStats(String line) {

        Matcher mtch = basketballPlayerPattern.matcher(line);
        if (!mtch.matches()) {
            throw new NotValidLineException("Line " + line + " is not valid");
        }
        String[] stats = line.split(";");

        return BasketballPlayer.builder()
                .name(stats[0])
                .nickname(stats[1])
                .number(Integer.parseInt(stats[2]))
                .teamName(stats[3])
                .scoredPoints(Integer.parseInt(stats[4]))
                .rebounds(Integer.parseInt(stats[5]))
                .assist(Integer.parseInt(stats[6]))
                .build();
    }
}
