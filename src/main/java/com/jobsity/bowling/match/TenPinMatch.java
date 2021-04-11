package com.jobsity.bowling.match;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a specialization of @{@link Match} interface for Ten Pin Bowling.
 */
public class TenPinMatch implements Match {

    @Getter
    private final List<Player> players;
    private Scoreboard scoreboard;

    public TenPinMatch(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.players = new ArrayList<>();
    }

    @Override
    public int getMaxCalculableFrames() {
        return 10;
    }

    @Override
    public void displayScoreboard() {
        scoreboard.display(this.players);
    }

    @Override
    public boolean addPlayer(List<Player> players) {
        return this.players.addAll(players);
    }
}
