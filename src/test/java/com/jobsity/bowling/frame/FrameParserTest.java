package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;
import com.jobsity.bowling.fixture.RollFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameParserTest {

    private final FrameParser parser = new FrameParser();

    @Test
    @DisplayName("Should parse worst game")
    public void should_parse_worst_game() {
        List<Roll> rolls = RollFixture.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Frame> frames = parser.parse(rolls);

        assertEquals(10, frames.size());

        frames.forEach(frame -> {
            assertFalse(frame.isStrike());
            assertFalse(frame.isStrike());
            assertEquals(0, frame.getRollsScore());
        });
    }

    @Test
    @DisplayName("Should parse perfect game")
    public void should_parse_perfect_game() {
        List<Roll> rolls = RollFixture.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        assertEquals(12, frames.size());

        frames.forEach(frame -> {
            assertTrue(frame.isStrike());
            assertEquals(10, frame.getRollsScore());
        });
    }

    @Test
    @DisplayName("Should parse almost perfect game")
    public void should_parse_almost_perfect_game() {
        List<Roll> rolls = RollFixture.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9);
        List<Frame> frames = parser.parse(rolls);

        assertEquals(12, frames.size());

        for (int i = 0; i < frames.size() - 1; i++) {
            assertTrue(frames.get(i).isStrike());
            assertEquals(10, frames.get(i).getRollsScore());
        }

        Frame lastFrame = frames.get(11);

        assertFalse(lastFrame.isStrike());
        assertFalse(lastFrame.isStrike());
        assertEquals(9, lastFrame.getRollsScore());

    }

    @Test
    @DisplayName("Should parse ordinary game")
    public void should_parse_ordinary_game() {
        List<Roll> rolls = RollFixture.roll(1, 7, 2, 5, 5, 4, 2, 2, 0, 1, 7, 0, 6, 0, 8, 1, 9, 0, 9, 0);
        List<Frame> frames = parser.parse(rolls);

        assertEquals(10, frames.size());

        // FRAME 1
        Frame frame1 = frames.get(0);
        assertFalse(frame1.isStrike());
        assertFalse(frame1.isSpare());
        assertEquals(8, frame1.getRollsScore());

        // FRAME 2
        Frame frame2 = frames.get(1);
        assertFalse(frame2.isStrike());
        assertFalse(frame2.isSpare());
        assertEquals(7, frame2.getRollsScore());

        // FRAME 3
        Frame frame3 = frames.get(2);
        assertFalse(frame3.isStrike());
        assertFalse(frame3.isSpare());
        assertEquals(9, frame3.getRollsScore());

        // FRAME 4
        Frame frame4 = frames.get(3);
        assertFalse(frame4.isStrike());
        assertFalse(frame4.isSpare());
        assertEquals(4, frame4.getRollsScore());

        // FRAME 5
        Frame frame5 = frames.get(4);
        assertFalse(frame5.isStrike());
        assertFalse(frame5.isSpare());
        assertEquals(1, frame5.getRollsScore());

        // FRAME 6
        Frame frame6 = frames.get(5);
        assertFalse(frame6.isStrike());
        assertFalse(frame6.isSpare());
        assertEquals(7, frame6.getRollsScore());

        // FRAME 7
        Frame frame7 = frames.get(6);
        assertFalse(frame7.isStrike());
        assertFalse(frame7.isSpare());
        assertEquals(6, frame7.getRollsScore());

        // FRAME 8
        Frame frame8 = frames.get(7);
        assertFalse(frame8.isStrike());
        assertFalse(frame8.isSpare());
        assertEquals(9, frame8.getRollsScore());

        // FRAME 9
        Frame frame9 = frames.get(8);
        assertFalse(frame9.isStrike());
        assertFalse(frame9.isSpare());
        assertEquals(9, frame9.getRollsScore());

        // FRAME 10
        Frame frame10 = frames.get(9);
        assertFalse(frame10.isStrike());
        assertFalse(frame10.isSpare());
        assertEquals(9, frame10.getRollsScore());
    }

    @Test
    @DisplayName("Should parse game with strike on last frame")
    public void should_parse_with_strike_on_last() {
        List<Roll> rolls = RollFixture.roll(10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1);
        List<Frame> frames = parser.parse(rolls);

        assertEquals(11, frames.size());

        // FRAME 1
        Frame frame1 = frames.get(0);
        assertTrue(frame1.isStrike());
        assertEquals(10, frame1.getRollsScore());

        // FRAME 2
        Frame frame2 = frames.get(1);
        assertTrue(frame2.isSpare());
        assertEquals(10, frame2.getRollsScore());

        // FRAME 3
        Frame frame3 = frames.get(2);
        assertFalse(frame3.isStrike());
        assertFalse(frame3.isSpare());
        assertEquals(9, frame3.getRollsScore());

        // FRAME 4
        Frame frame4 = frames.get(3);
        assertTrue(frame4.isStrike());
        assertEquals(10, frame4.getRollsScore());

        // FRAME 5
        Frame frame5 = frames.get(4);
        assertFalse(frame5.isStrike());
        assertFalse(frame5.isSpare());
        assertEquals(8, frame5.getRollsScore());

        // FRAME 6
        Frame frame6 = frames.get(5);
        assertTrue(frame6.isSpare());
        assertEquals(10, frame6.getRollsScore());

        // FRAME 7
        Frame frame7 = frames.get(6);
        assertFalse(frame7.isStrike());
        assertFalse(frame7.isSpare());
        assertEquals(6, frame7.getRollsScore());

        // FRAME 8
        Frame frame8 = frames.get(7);
        assertTrue(frame8.isStrike());
        assertEquals(10, frame8.getRollsScore());

        // FRAME 9
        Frame frame9 = frames.get(8);
        assertTrue(frame9.isStrike());
        assertEquals(10, frame9.getRollsScore());

        // FRAME 10
        Frame frame10 = frames.get(9);
        assertTrue(frame10.isStrike());
        assertEquals(10, frame10.getRollsScore());

        // FRAME 11
        Frame frame11 = frames.get(10);
        assertFalse(frame11.isStrike());
        assertFalse(frame11.isSpare());
        assertEquals(9, frame11.getRollsScore());
    }

}
