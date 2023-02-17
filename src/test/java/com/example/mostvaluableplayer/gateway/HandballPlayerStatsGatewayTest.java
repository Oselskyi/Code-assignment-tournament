package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.HandballPlayer;
import com.example.mostvaluableplayer.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class HandballPlayerStatsGatewayTest {

    private PlayerStatsGateway playerStatsGateway;
    private HandballPlayer player;

    @BeforeEach
    void setUp() {
        playerStatsGateway = new HandballPlayerStatsGateway();
    }

    @Test
    void shouldThrowNotValidLineExceptionWhenLineIsNotValidTest() {
        String invalidLine = "player 1;nick1;a;Team A;0;10";

        assertThatThrownBy(() -> playerStatsGateway.convertToPlayerStats(invalidLine))
                .isInstanceOf(NotValidLineException.class)
                .hasMessageContaining("Line " + invalidLine + " is not valid");
    }

    @Test
    void convertToPlayerStatsReturnValidPlayerIfInputLineIsValidTest() {
        player = new HandballPlayer("player 1", "nick1", 4, "Team A", 0, 10);
        String line = "player 1;nick1;4;Team A;0;10";
        Player actualPlayer =  playerStatsGateway.convertToPlayerStats(line);

        assertEquals(player, actualPlayer);
    }
}