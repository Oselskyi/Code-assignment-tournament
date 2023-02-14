package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Game;
import com.example.mostvaluableplayer.model.Match;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.model.Team;
import com.example.mostvaluableplayer.service.MatchService;
import com.example.mostvaluableplayer.service.PlayerService;
import com.example.mostvaluableplayer.service.TeamService;

import java.util.Comparator;
import java.util.Optional;

public class MatchServiceImpl implements MatchService {

    private final PlayerService handballPlayerService;
    private final PlayerService basketballPlayerService;
    private final TeamService teamService;

    public MatchServiceImpl(PlayerService handballPlayerService, PlayerService basketballPlayerService, TeamService teamService) {
        this.handballPlayerService = handballPlayerService;
        this.basketballPlayerService = basketballPlayerService;
        this.teamService = teamService;
    }


    @Override
    public Match parseFileDTOToMatch(FileDTO fileDTO) {
        if (fileDTO.getGameName().equals(Game.BASKETBALL.getName())) {
            return getMatchFromDataDTO(fileDTO, basketballPlayerService);
        }
        if (fileDTO.getGameName().equals(Game.HANDBALL.getName())) {
            return getMatchFromDataDTO(fileDTO, handballPlayerService);
        }
        throw new IllegalStateException(String.format("Unknown game %s", fileDTO.getGameName()));
    }

    @Override
    public Match getMatchFromDataDTO(FileDTO fileDTO, PlayerService playerService) {
        Match match = new Match();
        for (String line : fileDTO.getLines()) {
            match.getPlayers().add(playerService.parseLineToPlayer(line));
        }
        if (fileDTO.getLines().size() > match.getPlayers().size()) {
            throw new IllegalStateException("One of the players has two roles in one match");
        }
        setTeamsForMatch(match);
        setWinner(match);
        handballPlayerService.addRatingPointsForWinner(match);
        return match;
    }

    @Override
    public void setTeamsForMatch(Match match) {
for (Player player: match.getPlayers()){
    Optional<Team> teamOptional = teamService.getTeamByName(match.getTeams(), player.getTeamName());
    if (teamOptional.isPresent()) {
        teamOptional.get().addPoints(player.getPointsForTeamVictory());
        teamOptional.get().getPlayers().add(player);
    } else {
        Team team = new Team();
        team.setName(player.getTeamName());
        team.addPoints(player.getPointsForTeamVictory());
        team.getPlayers().add(player);
        match.getTeams().add(team);
    }
}
if (match.getTeams().size() != 2) {
    throw new IllegalStateException("For match should be two teams");
}
    }

    @Override
    public void setWinner(Match match) {
        match.getTeams().sort(Comparator.comparing(Team::getScoredPoints).reversed());
        if (match.getTeams().get(0).getScoredPoints() == (match.getTeams().get(1).getScoredPoints())){
            throw new IllegalStateException("The match " + match + " has no winner");
        }
        match.setWinner(match.getTeams().get(0));
    }
}
