package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Team {
    private String name;
    private List<BasketballPlayerStats> players = new ArrayList<>();
    private int score;

    public Team(String name, List<BasketballPlayerStats> players) {
        this.name = name;
        this.players = players;
    }
}
