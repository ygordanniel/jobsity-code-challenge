package com.jobsity.bowling.roll;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * This class represents a singles Bowling Ball roll.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roll {

    private Integer score;

    public static Roll ignore() {
        return new Roll();
    }

    public boolean isStrike() {
        return this.score == 10;
    }

    public Integer getScore() {
        if(isIgnore()) {
            return 0;
        }

        return score;
    }

    /**
     * Used for cases when the second {@link Roll} of a {@link com.jobsity.bowling.frame.Frame } should be ignored.
     * @return true if the score is null, false otherwise
     */
    public boolean isIgnore() {
        return Objects.isNull(score);
    }
}
