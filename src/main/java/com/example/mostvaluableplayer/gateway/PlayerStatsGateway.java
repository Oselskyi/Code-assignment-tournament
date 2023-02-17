package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.model.Player;

import java.util.ArrayList;
import java.util.List;

public interface PlayerStatsGateway<T extends Player> {

    default List<T> parseGameStatsToPlayers(List<String> lines) {
        List<T> players = new ArrayList<>();
        for (String line : lines) {
            players.add(convertToPlayerStats(line));
        }
        return players;
    }

    T convertToPlayerStats(String line);
}
