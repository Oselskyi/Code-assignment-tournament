package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.model.Player;

import java.util.List;

public interface PlayerStatsGateway<T extends Player> {

    List<T> parseGameStatsToPlayers(List<String> lines);
}
