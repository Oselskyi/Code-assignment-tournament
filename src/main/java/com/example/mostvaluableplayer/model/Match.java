package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Match {
    private List<Team> teams = new ArrayList<>();
    private Set<Player> players = new HashSet<>();
    private Team winner;
}
