package com.example.mostvaluableplayer.service.impl;

import com.example.mostvaluableplayer.model.HandballPlayer;
import com.example.mostvaluableplayer.model.Match;
import com.example.mostvaluableplayer.service.PlayerService;

public class HandballPlayerService implements PlayerService<HandballPlayer> {

    @Override
    public HandballPlayer parseLineToPlayer(String line) {
        String [] splitArray = line.split(",");

        HandballPlayer player = new HandballPlayer();
        player.setName(splitArray[0]);
        player.setNickname(splitArray[1]);
        player.setNumber(Integer.parseInt(splitArray[2]));
        player.setTeamName(splitArray[3]);
        player.setGoalMade(Integer.parseInt(splitArray[4]));
        player.setGoalReceive(Integer.parseInt(splitArray[5]));
        player.setPointsForTeamVictory(player.getGoalMade());
        calculateRating(player);
        return player;
    }

    public void calculateRatingPoints(HandballPlayer player) {

    }

    private void calculateRating(HandballPlayer player) {
        int rating = player.getGoalMade() * 2 - player.getGoalReceive();
        player.setRating(rating);
    }

    @Override
    public void addRatingPointsForWinner(Match match) {
        match.getPlayers()
                .stream()
                .filter(player -> player.getTeamName().equals(match.getWinner().getName()))
                .forEach(player -> player.addRatingPoints(10));
    }
}
