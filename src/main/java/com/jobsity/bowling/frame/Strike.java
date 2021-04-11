package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;

/**
 * This class represents a Strike Frame.
 * Strikes frames have the second roll ignored for score calculation and scoreboard display purposes.
 */
public class Strike extends Frame {

    public Strike(Roll roll) {
        super(roll);
    }

    @Override
    public boolean isStrike() {
        return true;
    }
}
