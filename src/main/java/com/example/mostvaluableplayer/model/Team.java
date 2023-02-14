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
    private List<Player> players = new ArrayList<>();
    int scoredPoints;

    public void addPoints(int scoredPoints) {
        this.scoredPoints += scoredPoints;
    }
}
