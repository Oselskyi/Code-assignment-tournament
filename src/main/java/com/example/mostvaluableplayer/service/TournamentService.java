package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.PlayerStats;
import com.example.mostvaluableplayer.model.Tournament;

import java.util.Optional;
import java.util.Set;

public interface TournamentService {
    void tournamentProcessingFromFiles(Set<String> files);
    void setMvp(Tournament tournament);
    void setPlayerListForTournament(Tournament tournament);
    Optional<PlayerStats> findPlayerByNickNameInTournament(Tournament tournament, String nickname);
}
