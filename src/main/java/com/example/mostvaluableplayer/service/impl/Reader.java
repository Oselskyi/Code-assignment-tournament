package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Reader {

    public static FileDTO readFile(String file) throws IOException {
        FileDTO fileDTO = new FileDTO();
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String game = reader.readLine();
        checkGameType(game);
        fileDTO.setGameName(game);
        while ((line = reader.readLine()) != null) {
            fileDTO.setLines(Arrays.asList(line.split(",")));
        }

        return fileDTO;
    }

    private static void checkGameType(String game) {
        if (!Arrays.stream(Game.values()).map(Game::getName).toList().contains(game)) {
            throw new RuntimeException("//todo: Create own exception");
        }
    }

}
