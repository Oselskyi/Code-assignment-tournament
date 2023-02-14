package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Match;

public interface MatchService {
    Match parseFileDTOToMatch(FileDTO fileDTO);
    Match getMatchFromDataDTO(FileDTO fileDTO, PlayerService playerService);
    void setTeamsForMatch(Match match);
    void setWinner(Match match);
}
