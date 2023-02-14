package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.Game;
import com.example.mostvaluableplayer.model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Reader {

    public static List<FileDTO> readFiles(Set<String> files) throws IOException {
        List<FileDTO> fileDTOS = new ArrayList<>();
        FileDTO fileDTO = new FileDTO();
        String line = "";
        String splitBy = ",";

        for (String file:
             files) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String game = reader.readLine();
            if (!Arrays.stream(Game.values()).map(Game::getName).toList().contains(game)) {
                throw new RuntimeException("My custom exception");
            }
            fileDTO.setGameName(game);

            while ((line = reader.readLine()) != null) {
                fileDTO.getLines().add(line);
            }
            fileDTOS.add(fileDTO);
        }

//
//
////            while ((line = reader.readLine()) != null) {
////                String [] splitArray = line.split(splitBy);
////
////                BasketballPlayer player = new BasketballPlayer(splitArray[0], splitArray[1], Integer.parseInt(splitArray[2]), splitArray[3],
////                        0, Integer.parseInt(splitArray[4]), Integer.parseInt(splitArray[5]), Integer.parseInt(splitArray[6]));
////                players.add(player);
        return fileDTOS;
    }

}
