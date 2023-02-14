package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Match;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.model.Tournament;
import com.example.mostvaluableplayer.service.FileService;
import com.example.mostvaluableplayer.service.MatchService;
import com.example.mostvaluableplayer.service.TournamentService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TournamentServiceImpl implements TournamentService {

    private final FileService fileService;
    private final MatchService matchService;

    private List<FileDTO> fileDTOs;

    public TournamentServiceImpl(FileService fileService, MatchService matchService) {
        this.fileService = fileService;
        this.matchService = matchService;
    }


    @Override
    public void tournamentProcessingFromFiles(String path) {
        fileDTOs = fileService.getFileDTOList(path);

        Tournament tournament = new Tournament();
        for (FileDTO fileDTO :
                fileDTOs) {
            Match match = matchService.parseFileDTOToMatch(fileDTO);
            tournament.getMatches().add(match);
        }

        setPlayerListForTournament(tournament);
        setMvp(tournament);
        System.out.println(tournament.getMvp());
    }

    @Override
    public void setMvp(Tournament tournament) {
        tournament.setMvp(tournament.getPlayers().stream()
                .max(Comparator.comparing(Player::getRating))
                .get());
    }

    @Override
    public void setPlayerListForTournament(Tournament tournament) {
        for (Match match : tournament.getMatches()) {
            for (Player player : match.getPlayers()) {
                Optional<Player> playerByNickname = findPlayerByNickNameInTournament(tournament, player.getNickname());
                if (playerByNickname.isPresent()) {
                    playerByNickname.get().addRatingPoints(player.getRating());
                } else {
                    Player newPlayer = new Player();
                    newPlayer.setNickname(player.getNickname());
                    newPlayer.setRating(player.getRating());
                    tournament.getPlayers().add(newPlayer);
                }
            }
        }
    }

    @Override
    public Optional<Player> findPlayerByNickNameInTournament(Tournament tournament, String nickname) {
        return Optional.of(tournament.getPlayers().stream()
                .filter(player -> player.getNickname().equals(nickname))
                .findFirst())
                .orElse(Optional.empty());
    }
}
