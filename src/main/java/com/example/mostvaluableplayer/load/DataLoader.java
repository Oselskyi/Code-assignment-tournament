package com.example.mostvaluableplayer.load;

import com.example.mostvaluableplayer.service.tournament.TournamentServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final TournamentServiceImpl tournamentService;

    public DataLoader(TournamentServiceImpl tournamentService) {
        this.tournamentService = tournamentService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(tournamentService.playTournament("src/main/resources/games"));
    }
}
