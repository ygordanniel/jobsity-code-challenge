package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @Test
    @DisplayName("Should create a default spare")
    public void should_create_default_spare() {
        List<Roll> rolls = Stream.of(5, 5)
                .map(Roll::new)
                .collect(Collectors.toList());
        Frame frame = new Spare(rolls.get(0), rolls.get(1));

        assertTrue(frame.isSpare());
        assertFalse(frame.isStrike());
        assertEquals(5, frame.getFirstRollScore());
        assertEquals(5, frame.getSecondRoll().getScore());
        assertEquals(10, frame.getRollsScore());
    }

}
