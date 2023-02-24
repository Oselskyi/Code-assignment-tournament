package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BasketballPlayerStatsGatewayTest {

    private PlayerStatsGateway<BasketballPlayer> playerStatsGateway;



    @BeforeEach
    void setUp() {
        playerStatsGateway = new BasketballPlayerStatsGateway();
    }

    @Test
    void shouldThrowNotValidLineExceptionWhenLineIsNotValidTest() {
        var invalidLineList = List.of("player 1;nick1;4;10;Team A;2;7");


        assertThrows(NotValidLineException.class, () -> playerStatsGateway.parseGameStatsToPlayers(invalidLineList),
                "Line " + invalidLineList.get(0) + " is not valid");

    }

    @Test
    void convertToPlayerStatsReturnValidPlayerIfInputLineIsValidTest() {
        var player = new BasketballPlayer("player 1", "nick1", 4,
                "Team A", 10, 2, 7);
        var line = List.of("player 1;nick1;4;Team A;10;2;7");
        var actualPlayers = playerStatsGateway.parseGameStatsToPlayers(line);

        assertEquals(player, actualPlayers.get(0));
    }

    @Test
    void parseGameStatsToPlayersShouldReturnListWithTheSameSizeAsInputListTest() {
        List<String> lines = List.of("player 1;nick1;4;Team A;10;2;7",
                "player 2;nick2;8;Team A;0;10;0",
                "player 3;nick3;15;Team A;15;10;4"
                );


        var actualPlayers = playerStatsGateway.parseGameStatsToPlayers(lines);

        assertEquals(lines.size(), actualPlayers.size());
    }
}