package com.jobsity.bowling.frame;

import com.jobsity.bowling.roll.Roll;

import java.util.ArrayList;
import java.util.List;

public class FrameParser {

    public List<Frame> parse(List<Roll> rolls) {
        List<Frame> frames = new ArrayList<>();
        int i = 0;
        while (i < rolls.size()) {
            Frame frame;
            Roll firstRoll = rolls.get(i);
            if (i + 1 < rolls.size()) {
                Roll secondsRoll = rolls.get(i + 1);
                if (firstRoll.isStrike()) {
                    frame = new Strike(firstRoll);
                } else if (firstRoll.getScore() + secondsRoll.getScore() == 10) {
                    frame = new Spare(firstRoll, secondsRoll);
                } else {
                    frame = new Frame(firstRoll, secondsRoll);
                }
            } else {
                if(firstRoll.isStrike()) {
                    frame = new Strike(firstRoll);
                } else {
                    frame = new Frame(firstRoll);
                }
            }
            if (frame.isStrike()) {
                ++i;
            } else {
                i += 2;
            }
            frames.add(frame);
        }
        return frames;
    }
}
