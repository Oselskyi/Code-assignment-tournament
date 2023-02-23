package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.SportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameServiceFactory {

    private static Map<SportType, GameService> gameServiceMap;

    private GameServiceFactory(List<GameService> gameServices) {
        gameServiceMap = gameServices.stream().collect(Collectors.toMap(GameService::getType, Function.identity()));
    }

    public GameService getGameService(String gameType) {
        return Optional.ofNullable(gameServiceMap.get(SportType.valueOf(gameType)))
                .orElseThrow(IllegalArgumentException::new);
    }
}
