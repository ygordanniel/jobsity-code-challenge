package com.jobsity.bowling.match;

import java.util.List;

/**
 * Interface that represents a Bowling Match.
 */
public interface Match {

    List<Player> getPlayers();
    int getMaxCalculableFrames();
    void displayScoreboard();
    boolean addPlayer(List<Player> players);
}
