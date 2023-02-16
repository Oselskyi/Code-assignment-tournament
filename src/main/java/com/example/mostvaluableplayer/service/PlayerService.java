package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.Player;

import java.util.List;

public interface PlayerService<T extends Player> {

    List<T> getPlayers(List<String> lines);
}
