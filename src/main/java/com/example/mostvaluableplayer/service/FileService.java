package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.dto.FileDTO;

import java.util.List;

public interface FileService {

    List<FileDTO> readFile(String filePath);
}
