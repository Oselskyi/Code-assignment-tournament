package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player {

    private String name;
    private String nickname;
    private int number;
    private String teamName;
    private int rating;
    int pointsForTeamVictory;
    public void addRatingPoints(int points) {
        this.rating += points;
    }
}
