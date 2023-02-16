package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Tournament {
    private List<PlayerStats> playerStats = new ArrayList<>();
    private List<Game> games = new ArrayList<>();
    private PlayerStats mvp;
    private List<String> gameTypes = new ArrayList<>();


}
