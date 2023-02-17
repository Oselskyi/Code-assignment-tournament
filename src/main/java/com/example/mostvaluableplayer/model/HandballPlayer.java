package com.example.mostvaluableplayer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class HandballPlayer extends Player {
    private int goalMade;
    private int goalReceive;

    public HandballPlayer(String name, String nickname, int number, String teamName, int goalMade, int goalReceive) {
        super(name, nickname, number, teamName);
        this.goalMade = goalMade;
        this.goalReceive = goalReceive;
    }
}
