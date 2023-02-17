package com.example.mostvaluableplayer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HandballPlayer extends Player {
    private int goalMade;
    private int goalReceive;
}
