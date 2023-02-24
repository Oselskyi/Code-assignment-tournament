package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.GameType;
import com.example.mostvaluableplayer.model.Sportsman;

import java.util.List;

public interface GameService {
    GameType getType();
    List<Sportsman> calculateRatingForEveryPlayer(GameStats gameStats);
}
