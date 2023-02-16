package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Player;
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
        List<Player> players = new ArrayList<>();
        for (FileDTO fileDTO :
                fileDTOS) {
            GameService gameService = gameServiceFactory.getGameService(fileDTO.getGameName());
            players = gameService.calculateRating(fileDTO);
        }


        Player mvp = defineMvp(players);
        System.out.println(mvp);
        return mvp;
    }

    public Player defineMvp(List<Player> players) {
        Map<String, Integer> map = players.stream().collect(Collectors.groupingBy(Player::getNickname, Collectors.summingInt(Player::getRating)));
        Map.Entry<String, Integer> stringIntegerEntry = map.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);

        return players.stream().max(Comparator.comparing(Player::getRating)).orElse(null);

    }
}
