package com.example.mostvaluableplayer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum SportType {
    BASKETBALL("BASKETBALL"), HANDBALL("HANDBALL");

    private final String name;

    public String getName() {
        return name;
    }
}
