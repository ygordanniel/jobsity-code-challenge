package com.jobsity.bowling.match;

import com.jobsity.bowling.fixture.RollFixture;
import com.jobsity.bowling.frame.Frame;
import com.jobsity.bowling.frame.FrameParser;
import com.jobsity.bowling.roll.Roll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CmdScoreBoardTest {

    private final Scoreboard scoreboard = new CmdScoreBoard();
    private final FrameParser parser = new FrameParser();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Should display scoreboard for worst player")
    public void should_display_scoreboard_worst_player() {
        List<Roll> rolls = RollFixture.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Worst Player", frames, 10);
        scoreboard.display(Collections.singletonList(player));

        String[] scoreboardLines = outContent.toString().split("\n");
        String frameLine = scoreboardLines[0];
        String playerLine = scoreboardLines[1];
        String pinfallsLine = scoreboardLines[2];
        String scoreLine = scoreboardLines[3];

        this.assertScoreboardFrameHeader(frameLine);

        assertEquals(player.getName(), playerLine);

        assertTrue(pinfallsLine.startsWith("Pinfalls"));
        Pattern pattern = Pattern.compile("[^0]*0");
        Matcher matcher = pattern.matcher(pinfallsLine);
        int zeroCount = 0;
        while (matcher.find()) {
            zeroCount++;
        }
        assertEquals(20, zeroCount);
        for (int i = 1; i <= 10; i++) {
            assertFalse(pinfallsLine.contains(i + ""));
        }

        assertTrue(scoreLine.startsWith("Score"));
        matcher = pattern.matcher(scoreLine);
        zeroCount = 0;
        while (matcher.find()) {
            zeroCount++;
        }
        assertEquals(10, zeroCount);
        for (int i = 1; i <= 10; i++) {
            assertFalse(scoreLine.contains(i + ""));
        }
    }

    @Test
    @DisplayName("Should display scoreboard for perfect player")
    public void should_display_scoreboard_perfect_player() {
        List<Roll> rolls = RollFixture.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Perfect Player", frames, 10);
        scoreboard.display(Collections.singletonList(player));

        String[] scoreboardLines = outContent.toString().split("\n");
        String frameLine = scoreboardLines[0];
        String playerLine = scoreboardLines[1];
        String pinfallsLine = scoreboardLines[2];
        String scoreLine = scoreboardLines[3];

        this.assertScoreboardFrameHeader(frameLine);
        assertEquals(player.getName(), playerLine);

        assertTrue(pinfallsLine.startsWith("Pinfalls"));
        Pattern strikeXPattern = Pattern.compile("[^X]*X");
        Matcher matcher = strikeXPattern.matcher(pinfallsLine);
        int strikeCounter = 0;
        while (matcher.find()) {
            strikeCounter++;
        }
        assertEquals(12, strikeCounter);

        assertTrue(scoreLine.startsWith("Score"));
        for (int i = 1; i <= 10; i++) {
            assertTrue(scoreLine.contains(i * 30 + ""));
        }
    }

    @Test
    @DisplayName("Should display scoreboard with spare")
    public void should_display_scoreboard_with_spare() {
        List<Roll> rolls = RollFixture.roll(8, 2, 7, 3, 3, 4, 10, 2, 8, 10, 10, 8, 0, 10, 8, 2, 9);
        List<Frame> frames = parser.parse(rolls);

        Player player = new Player("Perfect Player", frames, 10);
        scoreboard.display(Collections.singletonList(player));

        String[] scoreboardLines = outContent.toString().split("\n");
        String frameLine = scoreboardLines[0];
        String playerLine = scoreboardLines[1];
        String pinfallsLine = scoreboardLines[2];
        String scoreLine = scoreboardLines[3];

        this.assertScoreboardFrameHeader(frameLine);

        assertEquals(player.getName(), playerLine);

        assertTrue(pinfallsLine.startsWith("Pinfalls"));
        Pattern sparePattern = Pattern.compile("[^/]*/");
        Matcher matcher = sparePattern.matcher(pinfallsLine);
        int strikeCounter = 0;
        while (matcher.find()) {
            strikeCounter++;
        }
        assertEquals(4, strikeCounter);

        assertTrue(scoreLine.startsWith("Score"));
        Stream.of(17, 30, 37, 57, 77, 105, 123, 131, 151, 170)
                .map(Object::toString)
                .forEach(scoreStr -> assertTrue(scoreLine.contains(scoreStr)));
    }

    private void assertScoreboardFrameHeader(String frameLine) {
        assertTrue(frameLine.startsWith("Frame"));
        for (int i = 1; i <= 10; i++) {
            assertTrue(frameLine.contains(i + ""));
        }
    }

}
