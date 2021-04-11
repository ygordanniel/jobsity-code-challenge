package com.jobsity.bowling.match;

import com.jobsity.bowling.fixture.RollFixture;
import com.jobsity.bowling.frame.Frame;
import com.jobsity.bowling.frame.FrameParser;
import com.jobsity.bowling.roll.Roll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final FrameParser parser = new FrameParser();

    @Test
    @DisplayName("Should calculate player game with spare for Ten Pin Bowling")
    public void should_calculate_player_game() {
        List<Roll> rolls = RollFixture.roll(10, 9, 1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Player", frames, 10);

        assertEquals(280, player.getFinalScore());
    }

    @Test
    @DisplayName("Should calculate player perfect game for Ten Pin Bowling")
    public void should_calculate_perfect_game() {
        List<Roll> rolls = RollFixture.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Perfect Player", frames, 10);

        assertEquals(300, player.getFinalScore());
    }

    @Test
    @DisplayName("Should calculate ordinary game for Ten Pin Bowling")
    public void should_calculate_ordinary_game_with_spare_and_strike() {
        List<Roll> rolls = RollFixture.roll(8, 2, 7, 3, 3, 4, 10, 2, 8, 10, 10, 8, 0, 10, 8, 2, 9);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Ordinary Player", frames, 10);

        assertEquals(170, player.getFinalScore());
    }

    @Test
    @DisplayName("Should calculate worst game for Ten Pin Bowling")
    public void should_calculate_worst_game_with_spare_and_strike() {
        List<Roll> rolls = RollFixture.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Worst Player", frames, 10);

        assertEquals(0, player.getFinalScore());
    }

    @Test
    @DisplayName("Should get max calculable frames")
    public void should_get_max_calculable_frames() {
        int maxCalculableFrames = 19;
        Player player = new Player("Player", Collections.emptyList(), maxCalculableFrames);

        assertEquals(maxCalculableFrames, player.getMaxCalculableFrames());
    }

    @Test
    @DisplayName("Should get frames")
    public void should_get_frames() {
        Player player = new Player("Player", Collections.emptyList(), 19);

        assertTrue(player.getFrames().isEmpty());
    }

    @Test
    @DisplayName("Should get name")
    public void should_get_name() {
        String somePlayer = "Some Player";
        Player player = new Player(somePlayer, Collections.emptyList(), 19);

        assertEquals(somePlayer, player.getName());
    }

}
