package com.example.mostvaluableplayer.service.tournament;

import com.example.mostvaluableplayer.gateway.BasketballPlayerStatsGateway;
import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.service.game.BasketballService;
import com.example.mostvaluableplayer.service.game.GameServiceFactory;
import com.example.mostvaluableplayer.service.input.Reader;
import com.example.mostvaluableplayer.service.input.ReaderFromCSV;
import com.example.mostvaluableplayer.service.team.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TournamentServiceImplTest {

    private TournamentService tournamentService;
    @Mock
    private Reader reader;
    @Mock
    private GameServiceFactory factory;
    private List<String> playersStats;

    @BeforeEach
    public void setUp() {
        this.reader = Mockito.mock(ReaderFromCSV.class);
        this.factory = Mockito.mock(GameServiceFactory.class);
        this.tournamentService = new TournamentServiceImpl(reader, factory);
        playersStats = List.of("player 1;nick1;4;Team A;10;2;7",
                "player 2;nick2;8;Team A;0;10;0",
                "player 3;nick3;15;Team A;15;10;4",
                "player 4;nick4;16;Team B;20;0;0",
                "player 5;nick5;23;Team B;4;7;7",
                "player 6;nick6;42;Team B;8;10;0"
        );
    }

    @Test
    void defineMvpForMultiplePlayerShouldCountProperlyTest() {
        var sportsmanList = List.of(
                new Sportsman("n2", 10),
                new Sportsman("n1", 10),
                new Sportsman("n3", 10),
                new Sportsman("n1", 15)
        );

        var result = new TournamentServiceImpl(new ReaderFromCSV(),
                new GameServiceFactory()).defineMvp(sportsmanList);

        assertEquals(new Sportsman("n1", 25), result);
    }

    @Test
    void playTournamentShouldReturnPlayerWithTheHighestRatingTest() {
        when(reader.readFile(anyString())).thenReturn(List.of(new GameStats("BASKETBALL", playersStats)));
        when(factory.getGameService(any())).thenReturn(new BasketballService(new BasketballPlayerStatsGateway(), new TeamServiceImpl()));

        var result = tournamentService.playTournament("filesPath");


        assertEquals(new Sportsman("nick4", 50), result);
    }
}