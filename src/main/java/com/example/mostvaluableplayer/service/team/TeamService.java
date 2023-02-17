package com.example.mostvaluableplayer.service.team;

import java.util.Map;

public interface TeamService {
    String getWinner(Map<String, Integer> teamScoreMap);
}
