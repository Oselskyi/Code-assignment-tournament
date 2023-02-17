package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.Sport;
import com.example.mostvaluableplayer.gateway.BasketballPlayerStatsGateway;
import com.example.mostvaluableplayer.gateway.HandballPlayerStatsGateway;
import com.example.mostvaluableplayer.service.team.TeamServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class GameServiceFactory {

    public GameService getGameService(String gameType) {

        return switch (Sport.valueOf(gameType)) {
            case BASKETBALL -> new BasketballService(new BasketballPlayerStatsGateway(), new TeamServiceImpl());
            case HANDBALL -> new HandballService(new HandballPlayerStatsGateway(), new TeamServiceImpl());
            default -> throw new RuntimeException("Create own Exception");
        };
    }
}
