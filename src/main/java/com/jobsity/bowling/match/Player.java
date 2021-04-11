package com.jobsity.bowling.match;

import com.jobsity.bowling.frame.Frame;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Player representation.
 */
@Data
@ToString(of = { "name", "finalScore" })
public class Player {

    private String name;
    private List<Frame> frames;
    private int finalScore;
    /**
     * Value representing the side of the frame subset to calculate the score.
     * This value depends on the type of Bowling that is being played.
     */
    private int maxCalculableFrames;

    public Player(String name, List<Frame> frames, int maxCalculableFrames) {
        this.name = name;
        this.frames = new ArrayList<>(frames);
        this.maxCalculableFrames = maxCalculableFrames;
        this.processScore();
    }

    private void processScore() {
        for (Frame frame : this.getCalculableFrames()) {
            int frameScore = frame.getScore(this.frames);
            this.finalScore += frameScore;
            frame.setAccumulatedScore(this.finalScore);
        }
    }

    /**
     * Final score calculation should be based on a subset of the player's frames to make sure that calculation is done
     * correctly and to avoid errors like {@link IndexOutOfBoundsException} when processing player's frames.
     * The subset of frames is based on {@link #maxCalculableFrames }.
     * @return relevant subset of frames
     */
    public List<Frame> getCalculableFrames() {
        if (this.frames.isEmpty()) {
            return this.frames;
        } else {
            return this.frames.subList(0, this.maxCalculableFrames);
        }
    }
}
