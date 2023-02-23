package com.example.mostvaluableplayer.service.tournament;

import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.Sportsman;
import com.example.mostvaluableplayer.service.game.GameService;
import com.example.mostvaluableplayer.service.game.GameServiceFactory;
import com.example.mostvaluableplayer.service.input.ReaderFromCSV;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService{

    private final ReaderFromCSV readerFromCSV;
    private final GameServiceFactory gameServiceFactory;

    public TournamentServiceImpl(ReaderFromCSV readerFromCSV, GameServiceFactory gameServiceFactory) {
        this.readerFromCSV = readerFromCSV;
        this.gameServiceFactory = gameServiceFactory;

    }

    public Sportsman playTournament(String filesPath) {
        List<GameStats> gameStatsList = readerFromCSV.readFile(filesPath);
        List<Sportsman> sportsmanList = new ArrayList<>();
        for (GameStats gameStats : gameStatsList) {
            GameService gameService = gameServiceFactory.getGameService(gameStats.getGameName());
            sportsmanList.addAll(gameService.calculateRatingForEveryPlayer(gameStats));
        }

        return defineMvp(sportsmanList);
    }

    public Sportsman defineMvp(List<Sportsman> sportsmen) {
        return sportsmen.stream()
                .collect(Collectors.groupingBy(Sportsman::getNickName, Collectors.summingInt(Sportsman::getRating)))
                .entrySet().stream()
                .map(entry -> new Sportsman(entry.getKey(), entry.getValue()))
                .max(Comparator.comparing(Sportsman::getRating)).orElse(null);
    }


}
