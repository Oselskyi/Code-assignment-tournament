package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.SportType;
import com.example.mostvaluableplayer.model.Sportsman;

import java.util.List;

public interface GameService {
    SportType getType();
    List<Sportsman> calculateRatingForEveryPlayer(GameStats gameStats);
}
