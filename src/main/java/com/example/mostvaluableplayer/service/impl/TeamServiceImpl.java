package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.Team;
import com.example.mostvaluableplayer.service.TeamService;

import java.util.List;
import java.util.Optional;

public class TeamServiceImpl implements TeamService {
    @Override
    public Optional<Team> getTeamByName(List<Team> teams, String name) {
        return Optional.of(
                teams.stream()
                        .filter(team -> team.getName().equals(name))
                        .findFirst()
        ).orElse(Optional.empty());
    }
}
