package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.dto.FileDTO;

import java.util.List;

public interface FileService {

    void getPathListByFolderPath(String path);
    List<FileDTO> getFileDTOList(String path);
}
