package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class represents a ordinary frame and can be extended to specialized frames.
 */

@Getter
@Setter
public class Frame {

    private Roll firstRoll;
    private Roll secondRoll;
    // Accumulated score based on Frame position on the match
    private int accumulatedScore;
    private int maxCalculableFrames;

    public Frame(Roll roll) {
        this(roll, Roll.ignore());
    }

    public Frame(Roll firstRoll, Roll secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    public int getRollsScore() {
        return firstRoll.getScore() + secondRoll.getScore();
    }

    public int getScore(List<Frame> frames) {
        return this.getRollsScore() + getBonusScore(frames);
    }

    private int getBonusScore(List<Frame> frames) {

        if (this.isSpare()) {
            return getSpareBonusScore(frames);
        }

        if (this.isStrike()) {
            return getStrikeBonusScore(frames);
        }

        return 0;
    }

    /**
     * A Spare score is calculated using the current frame roll scores plus the next frame first roll score.
     * @param frames list containing all frames of a player
     * @return spare score
     */
    private int getSpareBonusScore(List<Frame> frames) {
        Frame nextFrame = getNextFrame(this, frames);
        return nextFrame.getFirstRollScore();
    }

    /**
     * A Strike score is calculated using the current frame roll score plus the next two rolls scores.
     * The next two rolls score could be composed of a single {@link Spare} or {@link Frame} or with two {@link Strike}
     * @param frames list containing all frames of a player
     * @return strike score
     */
    private int getStrikeBonusScore(List<Frame> frames) {
        Frame nextFrame = getNextFrame(this, frames);
        if (nextFrame.isStrike()) {
            Frame nextNextFrame = getNextFrame(nextFrame, frames);
            return nextFrame.getFirstRollScore() + nextNextFrame.getFirstRollScore();
        }
        return nextFrame.getRollsScore();
    }

    private Frame getNextFrame(Frame frame, List<Frame> frames) {
        int index = frames.indexOf(frame);
        return frames.get(index + 1);
    }

    public int getFirstRollScore() {
        return firstRoll.getScore();
    }

    public boolean isStrike() {
        return false;
    }

    public boolean isSpare() {
        return false;
    }

}
