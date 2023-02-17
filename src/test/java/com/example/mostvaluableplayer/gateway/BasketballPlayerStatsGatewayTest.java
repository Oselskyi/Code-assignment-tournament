package com.example.mostvaluableplayer.gateway;

import com.example.mostvaluableplayer.exception.NotValidLineException;
import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BasketballPlayerStatsGatewayTest {

    private PlayerStatsGateway playerStatsGateway;
    private BasketballPlayer player;

    @BeforeEach
    void setUp() {
        playerStatsGateway = new BasketballPlayerStatsGateway();
    }

    @Test
    void shouldThrowNotValidLineExceptionWhenLineIsNotValid() {
        String invalidLine = "player 1;nick1;4;10;Team A;2;7";

        assertThatThrownBy(() -> playerStatsGateway.convertToPlayerStats(invalidLine))
                .isInstanceOf(NotValidLineException.class)
                .hasMessageContaining("Line " + invalidLine + " is not valid");
    }

    @Test
    void convertToPlayerStatsReturnValidPlayerIfInputLineIsValidTest() {
        player = new BasketballPlayer("player 1", "nick1", 4, "Team A", 10, 2, 7);
        String line = "player 1;nick1;4;Team A;10;2;7";
        Player actualPlayer =  playerStatsGateway.convertToPlayerStats(line);

        assertEquals(player, actualPlayer);
    }
}