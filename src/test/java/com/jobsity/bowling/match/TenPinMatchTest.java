package com.jobsity.bowling.match;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TenPinMatchTest {

    @Test
    @DisplayName("Should have max calculable frames equals to 10")
    public void should_have_max_calculable_frames_ten() {
        Match tenPinMatch = new TenPinMatch(new CmdScoreBoard());
        assertEquals(10, tenPinMatch.getMaxCalculableFrames());
    }

    @Test
    @DisplayName("Should display scoreboard")
    public void should_display_scoreboard() {
        Match tenPinMatch = new TenPinMatch(new CmdScoreBoard());
        tenPinMatch.displayScoreboard();
    }

    @Test
    @DisplayName("Should add players")
    public void should_add_players() {
        Match tenPinMatch = new TenPinMatch(new CmdScoreBoard());
        Player player = new Player("Player", Collections.emptyList(), tenPinMatch.getMaxCalculableFrames());
        assertTrue(tenPinMatch.addPlayer(Collections.singletonList(player)));
    }

}
