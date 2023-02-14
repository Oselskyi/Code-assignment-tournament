package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.Match;
import com.example.mostvaluableplayer.model.Player;

public interface PlayerService<T extends Player> {

    T parseLineToPlayer(String line);
    void calculateRatingPoints(T player);
    void addRatingPointsForWinner(Match match);
}
