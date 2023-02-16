package com.example.mostvaluableplayer.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeamServiceImpl {

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
