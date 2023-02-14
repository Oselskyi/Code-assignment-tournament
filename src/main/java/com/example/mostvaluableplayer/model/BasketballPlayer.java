package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BasketballPlayer extends Player {
    private int scoredPoints;
    private int rebounds;
    private int assist;


}
