package com.example.mostvaluableplayer.service;

import com.example.mostvaluableplayer.model.PlayerStats;

import java.util.List;

public interface PlayerStatsService<T extends PlayerStats> {

    List<T> getPlayerStats(List<String> lines);
}
