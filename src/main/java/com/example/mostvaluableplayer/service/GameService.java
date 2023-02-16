package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Player;

import java.util.List;

public interface GameService {
    List<Player> calculateRating(FileDTO fileDTO);
}
