package com.example.mostvaluableplayer.controller;

import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.service.tournament.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping("/mvp")
    @ResponseStatus(HttpStatus.OK)
    public Sportsman getMvp(@RequestParam String path) {
        return tournamentService.playTournament(path);
    }
}
