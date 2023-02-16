package com.example.mostvaluableplayer.service;

import java.util.Map;

public interface TeamService {
    String getWinner(Map<String, Integer> teamScoreMap);
}
