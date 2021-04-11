package com.jobsity.bowling.match;

import com.jobsity.bowling.frame.Frame;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * This class is a {@link Scoreboard} implementation to display the scoreboard on the command line.
 */
@AllArgsConstructor
public class CmdScoreBoard implements Scoreboard {

    @Override
    public void display(List<Player> players) {
        System.out.println(this.buildDisplay(players));
    }

    private String buildDisplay(List<Player> players) {
        if(Objects.isNull(players) || players.isEmpty()) {
            return "No scoreboard to display.";
        }
        StringBuilder sb = new StringBuilder("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        players.forEach(player -> {
            sb.append("\n").append(player.getName());
            sb.append("\nPinfalls");
            for (int i = 0; i < player.getFrames().size(); i++) {
                sb.append("\t");
                sb.append(this.parseFrameScore(player.getFrames().get(i),
                        i >= player.getMaxCalculableFrames() - 1));
            }
            sb.append("\nScore");
            player.getCalculableFrames().forEach(frame -> sb.append("\t\t").append(frame.getAccumulatedScore()));
        });

        return sb.toString();
    }

    /**
     * Score display builds are tricky because we have to build the whole line.
     * This method builds the score of a singles frame and handles specific scenarios like:
     * - Strikes on the last frame should not start with a tab indentation;
     * - When a match had 3 Rolls on the last Frame, the second Roll of the Frame should be ignored.
     * @param frame with the score to display
     * @param isLastFrame flag saying the this frame is part of the 10th frame of a real Bowling game.
     * @return frame string score.
     */
    private String parseFrameScore(Frame frame, boolean isLastFrame) {
        if(frame.isSpare()) {
            return frame.getFirstRollScore() + "\t" + "/";
        }

        if(frame.isStrike()) {
            if(isLastFrame) {
                return "X";
            }
            return "\tX";
        }

        if(frame.getSecondRoll().isIgnore()) {
            return "" + frame.getFirstRoll().getScore();
        }

        return frame.getFirstRoll().getScore() + "\t" + frame.getSecondRoll().getScore();
    }
}
