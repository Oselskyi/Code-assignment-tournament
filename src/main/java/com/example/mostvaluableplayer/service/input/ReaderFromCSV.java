package com.example.mostvaluableplayer.service.input;

import com.example.mostvaluableplayer.exception.SportTypeException;
import com.example.mostvaluableplayer.exception.WrongFormatFileException;
import com.example.mostvaluableplayer.model.GameStats;
import com.example.mostvaluableplayer.model.Sport;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class ReaderFromCSV implements Reader {

    @Override
    public List<GameStats> readFile(String filePath) {
        List<GameStats> gameStatsList = new ArrayList<>();

        GameStats gameStats = new GameStats();
        File[] receivedFiles = new File(filePath).listFiles();
        for (File file : receivedFiles) {
            checkFileFormat(file);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String game = reader.readLine();
                gameStats = new GameStats();
                checkGameType(game);
                gameStats.setGameName(game);
                while (reader.ready()) {
                    gameStats.getLines().add(reader.readLine());
                }
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }

            gameStatsList.add(gameStats);
        }
        return gameStatsList;
    }

    private static void checkFileFormat(File file) {
        if (!file.getName().endsWith("csv")) throw new WrongFormatFileException("File should be .csv format");
    }

    private static void checkGameType(String game) {
        if (!Arrays.stream(Sport.values()).map(Sport::getName).toList().contains(game)) {
            throw new SportTypeException(game + " is incorrect sport");
        }
    }

}
