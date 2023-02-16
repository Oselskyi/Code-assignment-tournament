package com.example.mostvaluableplayer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameType {
    BASKETBALL("BASKETBALL"), HANDBALL("HANDBALL");

    private String name;
}
