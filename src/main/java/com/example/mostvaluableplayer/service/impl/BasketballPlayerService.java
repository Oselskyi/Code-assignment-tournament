package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.BasketballPlayer;
import com.example.mostvaluableplayer.model.Match;
import com.example.mostvaluableplayer.service.PlayerService;

public class BasketballPlayerService implements PlayerService<BasketballPlayer> {

    @Override
    public BasketballPlayer parseLineToPlayer(String line) {

        String [] splitArray = line.split(",");
        BasketballPlayer player = new BasketballPlayer();
        player.setName(splitArray[0]);
        player.setNickname(splitArray[1]);
        player.setNumber(Integer.parseInt(splitArray[2]));
        player.setTeamName(splitArray[3]);
        player.setScoredPoints(Integer.parseInt(splitArray[4]));
        player.setRebounds(Integer.parseInt(splitArray[5]));
        player.setAssist(Integer.parseInt(splitArray[6]));
        player.setPointsForTeamVictory(player.getScoredPoints());
        calculateRating(player);
        return player;
    }

    @Override
    public void calculateRatingPoints(BasketballPlayer player) {

    }

    private void calculateRating(BasketballPlayer player) {
        int rating = player.getScoredPoints() * 2
                + player.getRebounds()
                + player.getAssist();
        player.setRating(rating);
    }

    @Override
    public void addRatingPointsForWinner(Match match) {
        match.getPlayers().stream()
                .filter(player -> player.getTeamName().equals(match.getWinner().getName()))
                .forEach(player -> player.addRatingPoints(10));
    }
}
