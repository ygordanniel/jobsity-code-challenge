package com.jobsity.bowling.roll;

import com.jobsity.bowling.exception.IllegalRollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RollParserTest {

    private final RollParser parser = new RollParser();

    @Test
    @DisplayName("Should parse default scores")
    public void should_parse_default_scores() {
        for (Integer i = 0; i <= 10; i++) {
            assertEquals(i, parser.parse(i.toString()));
        }
    }

    @Test
    @DisplayName("Should parse foul scores")
    public void should_parse_foul_scores() {
        assertEquals(0, parser.parse("F"));
    }

    @Test
    @DisplayName("Should parse strike scores")
    public void should_parse_strike_scores() {
        assertEquals(10, parser.parse("X"));
    }

    @Test
    @DisplayName("Should throws IllegalRollException for negative score")
    public void should_throws_illegal_roll_exception_for_negative_score() {
        assertThrows(IllegalRollException.class, () -> parser.parse("-1"));
    }

    @Test
    @DisplayName("Should throws IllegalRollException for score higher than 10")
    public void should_throws_illegal_roll_exception_for_score_higher_10() {
        assertThrows(IllegalRollException.class, () -> parser.parse("19"));
    }

    @Test
    @DisplayName("Should throws IllegalRollException for invalid number")
    public void should_throws_illegal_roll_exception_for_invalid_number() {
        assertThrows(IllegalRollException.class, () -> parser.parse("number"));
    }
}
