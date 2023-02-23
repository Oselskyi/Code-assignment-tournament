package com.example.mostvaluableplayer.service.game;

import com.example.mostvaluableplayer.gateway.HandballPlayerStatsGateway;
import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.HandballPlayer;
import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.service.team.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandballServiceTest {

    private GameService gameService;

    GameStats gameStats;
    List<String> lines;
    List<Sportsman> playerList;
    List<HandballPlayer> handballPlayers;

    @BeforeEach
    void setUp() {
        gameService = new HandballService(new HandballPlayerStatsGateway(), new TeamServiceImpl());

        lines = List.of("player 1;nick1;4;Team A;0;20",
                "player 2;nick2;8;Team A;15;20",
                "player 3;nick3;15;Team A;10;20",
                "player 4;nick4;16;Team B;1;25",
                "player 5;nick5;23;Team B;12;25",
                "player 6;nick6;42;Team B;8;25"
        );
        playerList = List.of(new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7),
                new Sportsman("nick1", 7)
        );
        handballPlayers = List.of(
                new HandballPlayer("player 1", "nick1", 4, "Team A", 0, 20),
                new HandballPlayer("player 2", "nick2", 8, "Team A", 15, 20),
                new HandballPlayer("player 3", "nick3", 15, "Team A", 10, 20),
                new HandballPlayer("player 4", "nick4", 16, "Team B", 1, 25),
                new HandballPlayer("player 5", "nick5", 23, "Team B", 12, 25),
                new HandballPlayer("player 6", "nick6", 42, "Team B", 8, 25)
        );
    }

    @Test
    void calculateRatingReturnEmptyListIfInputListEmptyTest() {
        List<String> emptyList = new ArrayList<>();
        gameStats = new GameStats("HANDBALL", emptyList);

        playerList = gameService.calculateRatingForEveryPlayer(gameStats);

        assertEquals(0, playerList.size());
    }

    @Test
    void calculateRatingReturnListWithTheSameSizeAsInputListTest() {
        gameStats = new GameStats("HANDBALL", lines);

        playerList = gameService.calculateRatingForEveryPlayer(gameStats);

        assertEquals(6, playerList.size());
    }

    @Test
    void calculateRatingPlayerIfTeamWonGameTest(){
        var player = new HandballPlayer("player 1", "nick1", 4, "Team A", 15, 20);

        var actualRating = new HandballService(new HandballPlayerStatsGateway(), new TeamServiceImpl())
                .calculateGameRating(player, player.getTeamName());

        assertEquals(20, actualRating);
    }
    @Test
    void calculateRatingPlayerIfTeamLooseGameTest(){
        var player = new HandballPlayer("player 1", "nick1", 4, "Team A", 15, 20);

        var actualRating = new HandballService(new HandballPlayerStatsGateway(), new TeamServiceImpl())
                .calculateGameRating(player, "fake team");

        assertEquals(10, actualRating);
    }
}