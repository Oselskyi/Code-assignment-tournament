package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.model.Tournament;

import java.util.Optional;

public interface TournamentService {
    void tournamentProcessingFromFiles(String path);
    void setMvp(Tournament tournament);
    void setPlayerListForTournament(Tournament tournament);
    Optional<Player> findPlayerByNickNameInTournament(Tournament tournament, String nickname);
}
