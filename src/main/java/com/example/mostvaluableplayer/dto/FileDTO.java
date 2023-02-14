package com.example.mostvaluableplayer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FileDTO {
    private String gameName;
    private List<String> lines = new ArrayList<>();

}
