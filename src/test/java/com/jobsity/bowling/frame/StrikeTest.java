package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrikeTest {

    @Test
    @DisplayName("Should create a default strike")
    public void should_create_default_spare() {
        Frame frame = new Strike(new Roll(10));

        assertTrue(frame.isStrike());
        assertFalse(frame.isSpare());
        assertEquals(10, frame.getFirstRollScore());
        assertEquals(0, frame.getSecondRoll().getScore());
        assertTrue(frame.getSecondRoll().isIgnore());
        assertEquals(10, frame.getRollsScore());
    }

}
