package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Game {
    private String name;
    private List <Team> teams = new ArrayList<>();
//    private List<Player> players = new ArrayList<>();
    private Team winner;
}
