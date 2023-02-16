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
    private List<Player> players = new ArrayList<>();
    private List<Game> games = new ArrayList<>();
    private Player mvp;
    private List<String> gameTypes = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Tournament{" +
//                "players=" + players +
//                ", matches=" + games +
//                ", mvp=" + mvp +
//                '}';
//    }
}
