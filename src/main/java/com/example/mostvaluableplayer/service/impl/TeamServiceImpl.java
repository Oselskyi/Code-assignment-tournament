package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService {

    public String getWinner(Map<String, Integer> teamScoreMap) {
        String winner = "";
        int maxScore = 0;
        for (String team :
                teamScoreMap.keySet()) {
            Integer score = teamScoreMap.get(team);
            if (score > maxScore) {
                winner = team;
                maxScore = score;
            }
        }
        return winner;
    }

}
