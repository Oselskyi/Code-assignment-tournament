package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.PlayerStats;

import java.util.List;

public interface GameService {
    List<PlayerStats> calculateRating(FileDTO fileDTO);
}
