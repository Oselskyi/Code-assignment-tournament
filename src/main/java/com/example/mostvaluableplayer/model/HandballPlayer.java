package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HandballPlayer extends Player {
    private int goalMade;
    private int goalReceive;
}
