package com.example.mostvaluableplayer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class HandballPlayer extends Player {
    private int goalMade;
    private int goalReceive;

    @Builder
    public HandballPlayer(String name, String nickname, int number, String teamName, int goalMade, int goalReceive) {
        super(name, nickname, number, teamName);
        this.goalMade = goalMade;
        this.goalReceive = goalReceive;
    }
}
