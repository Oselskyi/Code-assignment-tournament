package com.example.mostvaluableplayer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class BasketballPlayer extends Player {
    private int scoredPoints;
    private int rebounds;
    private int assist;

    @Builder
    public BasketballPlayer(String name, String nickname, int number, String teamName, int scoredPoints, int rebounds, int assist) {
        super(name, nickname, number, teamName);
        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assist = assist;
    }

}
