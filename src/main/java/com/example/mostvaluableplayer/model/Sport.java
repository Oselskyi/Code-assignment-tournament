package com.example.mostvaluableplayer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Sport {
    BASKETBALL("BASKETBALL"), HANDBALL("HANDBALL");

    private String name;

    public String getName() {
        return name;
    }
}
