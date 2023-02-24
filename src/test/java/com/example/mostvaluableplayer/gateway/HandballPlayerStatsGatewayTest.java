package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.HandballPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandballPlayerStatsGatewayTest {

    private PlayerStatsGateway<HandballPlayer> playerStatsGateway;

    @BeforeEach
    void setUp() {
        playerStatsGateway = new HandballPlayerStatsGateway();
    }

    @Test
    void shouldThrowNotValidLineExceptionWhenLineIsNotValidTest() {
        List<String> invalidLineList = List.of("player 1;nick1;a;Team A;0;10");

        assertThrows(NotValidLineException.class,() -> playerStatsGateway.parseGameStatsToPlayers(invalidLineList),
                "Line " + invalidLineList.get(0) + " is not valid");
    }

    @Test
    void convertToPlayerStatsReturnValidPlayerIfInputLineIsValidTest() {
        var player = new HandballPlayer("player 1", "nick1", 4,
                "Team A", 0, 10);
        var lines = List.of("player 1;nick1;4;Team A;0;10");
        var actualPlayers =  playerStatsGateway.parseGameStatsToPlayers(lines);

        assertEquals(player, actualPlayers.get(0));
    }

    @Test
    void parseGameStatsToPlayersShouldReturnListWithTheSameSizeAsInputListTest() {
        List<String> lines = List.of("player 1;nick1;4;Team A;0;20",
                "player 2;nick2;8;Team A;15;20",
                "player 3;nick3;15;Team A;10;20",
                "player 4;nick4;16;Team B;1;25"
        );


        var actualPlayers = playerStatsGateway.parseGameStatsToPlayers(lines);

        assertEquals(lines.size(), actualPlayers.size());
    }
}