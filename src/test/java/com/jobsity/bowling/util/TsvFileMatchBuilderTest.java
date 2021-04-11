package com.jobsity.bowling.util;

import com.jobsity.bowling.exception.InvalidMatchException;
import com.jobsity.bowling.match.Match;
import com.jobsity.bowling.match.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TsvFileMatchBuilderTest {

    private final MatchBuilder<String> builder = new TsvFileMatchBuilder();

    @Test
    @DisplayName("Should build ordinary Ten Pin match")
    public void should_build_ordinary_match() throws FileNotFoundException {
        String file = "ordinary_match.txt";
        Match match = builder.build(Thread.currentThread().getContextClassLoader().getResource(file).getPath());
        assertEquals(2, match.getPlayers().size());
        assertEquals(10, match.getMaxCalculableFrames());

        Player player1 = match.getPlayers().get(0);
        assertEquals("Jeff", player1.getName());
        assertEquals(167, player1.getFinalScore());

        Player player2 = match.getPlayers().get(1);
        assertEquals("John", player2.getName());
        assertEquals(151, player2.getFinalScore());
    }

    @Test
    @DisplayName("Should build worst Ten Pin match")
    public void should_build_worst_match() throws FileNotFoundException {
        String file = "worst_match.txt";
        Match match = builder.build(Thread.currentThread().getContextClassLoader().getResource(file).getPath());
        assertEquals(1, match.getPlayers().size());
        assertEquals(10, match.getMaxCalculableFrames());

        Player player1 = match.getPlayers().get(0);
        assertEquals("Jeff", player1.getName());
        assertEquals(0, player1.getFinalScore());
    }

    @Test
    @DisplayName("Should build perfect Ten Pin match")
    public void should_build_perfect_match() throws FileNotFoundException {
        String file = "perfect_match.txt";
        Match match = builder.build(Thread.currentThread().getContextClassLoader().getResource(file).getPath());
        assertEquals(2, match.getPlayers().size());
        assertEquals(10, match.getMaxCalculableFrames());

        Player player1 = match.getPlayers().get(0);
        assertEquals("Jeff", player1.getName());
        assertEquals(300, player1.getFinalScore());

        Player player2 = match.getPlayers().get(1);
        assertEquals("John", player2.getName());
        assertEquals(170, player2.getFinalScore());
    }

    @Test
    @DisplayName("Should throws InvalidMatchException")
    public void should_throws_invalid_match_exception() {
        String file = "invalid_match.txt";
        assertThrows(InvalidMatchException.class, () -> builder.build(Thread.currentThread().getContextClassLoader().getResource(file).getPath()));
    }

    @Test
    @DisplayName("Should throws FileNotFoundException")
    public void should_throws_file_not_found_exception() {
        String file = "not_found_file.txt";
        assertThrows(FileNotFoundException.class, () -> builder.build(file));
    }

}
