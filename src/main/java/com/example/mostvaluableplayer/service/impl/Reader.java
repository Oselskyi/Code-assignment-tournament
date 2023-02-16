package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.dto.FileDTO;
import com.example.mostvaluableplayer.model.GameType;
import com.example.mostvaluableplayer.service.FileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class Reader implements FileService {

    @Override
    public List<FileDTO> readFile(String filePath) {
        List<FileDTO> fileDTOS = new ArrayList<>();

        FileDTO fileDTO = new FileDTO();
        File[] receivedFiles = new File(filePath).listFiles();
        for (File file : receivedFiles) {
            checkFileFormat(file);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String game = reader.readLine();
                fileDTO = new FileDTO();
                checkGameType(game);
                fileDTO.setGameName(game);
                while (reader.ready()) {
                    fileDTO.getLines().add(reader.readLine());
                }
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }

            fileDTOS.add(fileDTO);
        }
        return fileDTOS;
    }

    private static void checkFileFormat(File file) {
        if (!file.getName().endsWith("csv")) throw new RuntimeException("Create own exception");
    }

    private static void checkGameType(String game) {
        if (!Arrays.stream(GameType.values()).map(GameType::getName).toList().contains(game)) {
            throw new RuntimeException("//todo: Create own exception");
        }
    }

}
