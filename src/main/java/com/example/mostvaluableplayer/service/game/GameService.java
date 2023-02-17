package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.model.Sportsman;

import java.util.List;

public interface GameService <T extends Player> {
    List<Sportsman> calculateRatingForEveryPlayer(GameStats gameStats);
}
