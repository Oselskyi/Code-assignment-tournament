package com.example.mostvaluableplayer.service.tournament;

import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.service.game.GameServiceFactory;
import com.example.mostvaluableplayer.service.input.ReaderFromCSV;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentServiceImplTest {

    @Test
    void defineMvpForMultiplePlayerShouldCountProperlyTest() {
        var ratings = List.of(
            new Sportsman("n1", 10),
            new Sportsman("n2", 10),
            new Sportsman("n3", 10),
            new Sportsman("n1", 15)
        );

        var result = new TournamentServiceImpl(new ReaderFromCSV(), new GameServiceFactory()).defineMvp(ratings);

        assertEquals(new Sportsman("n1", 25), result);
    }
}