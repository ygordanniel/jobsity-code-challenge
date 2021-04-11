package com.jobsity.bowling.roll;

import com.jobsity.bowling.exception.RuleError;
import com.jobsity.bowling.exception.IllegalRollException;

/**
 * Parser to parse file score input into int score.
 * This parser is also responsible to validade bad score, e.g: negative scores or scores above the maximum.
 */
public class RollParser {

    public int parse(String rollStr) {
        switch (rollStr.toUpperCase()) {
            case "F":
                return 0;
            case "X":
                return 10;
            default:
                try {
                    int roll = Integer.parseInt(rollStr);
                    if(roll < 0 || roll > 10) {
                        throw new IllegalRollException(RuleError.INVALID_PINS_KNOCKED_QTY);
                    }
                    return roll;
                } catch (NumberFormatException nfe) {
                    throw new IllegalRollException(RuleError.INVALID_PINS_KNOCKED_QTY);
                }
        }
    }
}
