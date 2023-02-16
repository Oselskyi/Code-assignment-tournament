package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.service.GameService;
import org.springframework.stereotype.Component;

@Component
public class GameServiceFactory {

    public GameService getGameService(String gameType) {
        switch (gameType) {
            case "BASKETBALL":
                return new BasketballService(new BasketballPlayerStatsService(), new TeamServiceImpl());
            case "HANDBALL":
                return new HandballService(new HandballPlayerStatsService(), new TeamServiceImpl());
            default:
                throw new RuntimeException("Create own Exception");
        }
    }
}
