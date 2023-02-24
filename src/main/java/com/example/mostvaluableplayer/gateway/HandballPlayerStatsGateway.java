package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.HandballPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HandballPlayerStatsGateway implements PlayerStatsGateway<HandballPlayer> {

    private static final Pattern handballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+");

    @Override
    public List<HandballPlayer> parseGameStatsToPlayers(List<String> lines) {

        return lines.stream().map(this::convertToPlayerStats)
                .collect(Collectors.toList());
    }

    private HandballPlayer convertToPlayerStats(String line) {

        Matcher mtch = handballPlayerPattern.matcher(line);
        if (!mtch.matches()) {
            throw new NotValidLineException("Line " + line + " is not valid");
        }
        String[] stats = line.split(";");

        return HandballPlayer.builder()
                .name(stats[0])
                .nickname(stats[1])
                .number(Integer.parseInt(stats[2]))
                .teamName(stats[3])
                .goalMade(Integer.parseInt(stats[4]))
                .goalReceive(Integer.parseInt(stats[5]))
                .build();
    }
}
