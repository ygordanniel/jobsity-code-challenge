package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;

/**
 * This class represents a Spare Frame.
 */
public class Spare extends Frame {

    public Spare(Roll firstRoll, Roll secondRoll) {
        super(firstRoll, secondRoll);
    }

    @Override
    public boolean isSpare() {
        return true;
    }
}
