package com.example.mostvaluableplayer.service.input;

import com.example.mostvaluableplayer.model.GameStats;

import java.util.List;

public interface FileService {

    List<GameStats> readFile(String filePath);
}
