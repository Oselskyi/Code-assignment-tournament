package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.gateway.BasketballPlayerStatsGateway;
import com.example.mostvaluableplayer.service.team.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketballServiceTest {

    private GameService<BasketballPlayer> gameService;

    GameStats gameStats;
    List<String> lines;
    List<Sportsman> playerList;
    List<BasketballPlayer> basketballPlayers;

    @BeforeEach
    void setUp() {
        gameService = new BasketballService(new BasketballPlayerStatsGateway(), new TeamServiceImpl());

        lines = List.of("player 1;nick1;4;Team A;10;2;7",
                "player 2;nick2;8;Team A;0;10;0",
                "player 3;nick3;15;Team A;15;10;4",
                "player 4;nick4;16;Team B;20;0;0",
                "player 5;nick5;23;Team B;4;7;7",
                "player 6;nick6;42;Team B;8;10;0"
        );
        playerList = List.of(new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7)
        );
        basketballPlayers = List.of(
                new BasketballPlayer("player 1", "nick1", 4, "Team A", 10, 2, 7),
                new BasketballPlayer("player 2", "nick2", 0, "Team A", 0, 10, 0),
                new BasketballPlayer("player 3", "nick3", 15, "Team A", 15, 10, 4),
                new BasketballPlayer("player 4", "nick4", 16, "Team B", 20, 0, 0),
                new BasketballPlayer("player 5", "nick5", 23, "Team B", 4, 7, 7),
                new BasketballPlayer("player 6", "nick6", 42, "Team B", 8, 10, 0)
        );
    }

    @Test
    void calculateRatingReturnEmptyListIfInputListEmptyTest() {
        List<String> emptyList = new ArrayList<>();
        gameStats = new GameStats("BASKETBALL", emptyList);

        playerList = gameService.calculateRatingForEveryPlayer(gameStats);

        assertEquals(0, playerList.size());
    }

    @Test
    void calculateRatingReturnListWithTheSameSizeAsInputListTest() {
        gameStats = new GameStats("BASKETBALL", lines);

        playerList = gameService.calculateRatingForEveryPlayer(gameStats);

        assertEquals(6, playerList.size());
    }

    @Test
    void calculateRatingPlayerIfTeamWonGameTest() {
        var player = new BasketballPlayer("player 1", "nick1", 4,
                "Team A", 10, 2, 7);

        var actualRating = new BasketballService(new BasketballPlayerStatsGateway(), new TeamServiceImpl())
                .calculateGameRating(player, player.getTeamName());

        assertEquals(39, actualRating);
    }

    @Test
    void calculateRatingPlayerIfTeamLooseGameTest() {
        var player = new BasketballPlayer("player 1", "nick1", 4,
                "Team A", 10, 2, 7);

        var actualRating = new BasketballService(new BasketballPlayerStatsGateway(), new TeamServiceImpl())
                .calculateGameRating(player, "fake team");

        assertEquals(29, actualRating);
    }
}