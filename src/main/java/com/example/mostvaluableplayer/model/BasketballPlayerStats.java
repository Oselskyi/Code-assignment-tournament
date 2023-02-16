package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BasketballPlayerStats extends PlayerStats {
    private int scoredPoints;
    private int rebounds;
    private int assist;


}
