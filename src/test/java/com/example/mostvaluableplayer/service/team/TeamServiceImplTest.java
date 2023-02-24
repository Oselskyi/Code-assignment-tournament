package com.example.mostvaluableplayer.service.team;

import com.example.mostvaluableplayer.exception.NoWinnerException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamServiceImplTest {

    TeamService teamService = new TeamServiceImpl();
    Map<String, Integer> teamScoreMap = new HashMap<>();

    @Test
    void getWinnerIfTeamsHaveDifferentScoreTest() {
        teamScoreMap.put("Team A", 20);
        teamScoreMap.put("Team B", 23);

        String actualWinner = teamService.getWinner(teamScoreMap);
        String expectedWinner = "Team B";

        assertEquals(expectedWinner, actualWinner);
    }

    @Test
    void getWinnerShouldThrowExceptionIfTeamsHaveSameScoreTest() {

        teamScoreMap.put("Team A", 20);
        teamScoreMap.put("Team B", 20);

        assertThrows(NoWinnerException.class, () -> teamService.getWinner(teamScoreMap),
                "Every game must have a winner team");
    }
}