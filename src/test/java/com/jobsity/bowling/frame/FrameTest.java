package com.jobsity.bowling.frame;

import com.jobsity.bowling.fixture.RollFixture;
import com.jobsity.bowling.roll.Roll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    private final FrameParser parser = new FrameParser();

    @Test
    @DisplayName("Should create a default frame")
    public void should_create_default_frame() {
        List<Roll> rolls = Stream.of(8, 1)
                .map(Roll::new)
                .collect(Collectors.toList());
        Frame frame = new Frame(rolls.get(0), rolls.get(1));

        assertFalse(frame.isSpare());
        assertFalse(frame.isStrike());
        assertEquals(8, frame.getFirstRollScore());
        assertEquals(1, frame.getSecondRoll().getScore());
        assertEquals(9, frame.getRollsScore());
    }

    @Test
    @DisplayName("Should create a frame with a second roll ignored")
    public void should_create_frame_with_ignore_roll() {
        Frame frame = new Frame(new Roll(6));

        assertFalse(frame.isSpare());
        assertFalse(frame.isStrike());
        assertEquals(6, frame.getFirstRollScore());
        assertEquals(0, frame.getSecondRoll().getScore());
        assertTrue(frame.getSecondRoll().isIgnore());
        assertEquals(6, frame.getRollsScore());
    }

    @Test
    @DisplayName("Should calculate perfect game for Ten Pin Bowling")
    public void should_calculate_perfect_game() {
        List<Roll> rolls = RollFixture.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        int finalScore = 0;
        for (Frame frame : frames.subList(0, 10)) {
            int frameScore = frame.getScore(frames);
            finalScore += frameScore;
            frame.setAccumulatedScore(finalScore);
        }

        assertEquals(300, finalScore);
    }

    @Test
    @DisplayName("Should calculate game with spare for Ten Pin Bowling")
    public void should_calculate_game_with_spare() {
        List<Roll> rolls = RollFixture.roll(10, 9, 1, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        int finalScore = 0;
        for (Frame frame : frames.subList(0, 10)) {
            int frameScore = frame.getScore(frames);
            finalScore += frameScore;
            frame.setAccumulatedScore(finalScore);
        }

        assertEquals(280, finalScore);
    }

    @Test
    @DisplayName("Should calculate ordinary game for Ten Pin Bowling")
    public void should_calculate_ordinary_game_with_spare_and_strike() {
        List<Roll> rolls = RollFixture.roll(8, 2, 7, 3, 3, 4, 10, 2, 8, 10, 10, 8, 0, 10, 8, 2, 9);
        List<Frame> frames = parser.parse(rolls);

        int finalScore = 0;
        for (Frame frame : frames.subList(0, 10)) {
            int frameScore = frame.getScore(frames);
            finalScore += frameScore;
            frame.setAccumulatedScore(finalScore);
        }

        assertEquals(170, finalScore);
    }

    @Test
    @DisplayName("Should calculate worst game for Ten Pin Bowling")
    public void should_calculate_worst_game_with_spare_and_strike() {
        List<Roll> rolls = RollFixture.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Frame> frames = parser.parse(rolls);

        int finalScore = 0;
        for (Frame frame : frames.subList(0, 10)) {
            int frameScore = frame.getScore(frames);
            finalScore += frameScore;
            frame.setAccumulatedScore(finalScore);
        }

        assertEquals(0, finalScore);
    }

}
