package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Optional<Team> getTeamByName(List<Team> teams, String name);
}
