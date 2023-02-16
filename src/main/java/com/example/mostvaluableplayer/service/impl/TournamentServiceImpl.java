package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Player;
import com.example.mostvaluableplayer.model.PlayerStats;
import com.example.mostvaluableplayer.service.GameService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl {

    private final Reader reader;
    private final GameServiceFactory gameServiceFactory;

    public TournamentServiceImpl(Reader reader, GameServiceFactory gameServiceFactory) {
        this.reader = reader;
        this.gameServiceFactory = gameServiceFactory;

    }

    public Player playTournament(String filePath) {
        List<FileDTO> fileDTOS = reader.readFile(filePath);
        List<PlayerStats> playerStats = new ArrayList<>();
        for (FileDTO fileDTO :
                fileDTOS) {
            GameService gameService = gameServiceFactory.getGameService(fileDTO.getGameName());
            playerStats.addAll(gameService.calculateRating(fileDTO));
        }
        Player mvp = defineMvp(playerStats);
        System.out.println(mvp);
        return mvp;
    }

    public Player defineMvp(List<PlayerStats> playerStatistics) {
        Map<String, Integer> map = playerStatistics.stream().collect(Collectors.groupingBy(PlayerStats::getNickname, Collectors.summingInt(PlayerStats::getRating)));

        Set<Player> players = new HashSet<>();

        for (PlayerStats playerStats :
                playerStatistics) {
            players.add(playerToPlayerDTO(playerStats, map));
        }
        return players.stream().max(Comparator.comparing(Player::getRating)).orElse(null);

    }

    private Player playerToPlayerDTO(PlayerStats playerStats, Map<String, Integer> map) {
        Player player = new Player();
        player.setName(playerStats.getName());
        player.setNickname(playerStats.getNickname());
        player.setRating(map.get(playerStats.getNickname()));
        return player;
    }
}
