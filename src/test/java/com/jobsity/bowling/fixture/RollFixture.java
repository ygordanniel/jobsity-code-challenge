package com.jobsity.bowling.fixture;

import com.jobsity.bowling.roll.Roll;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RollFixture {

    public static Roll roll(int score) {
        return new Roll(score);
    }

    public static List<Roll> roll(Integer ...scores) {
        return Stream.of(scores)
                .map(Roll::new)
                .collect(Collectors.toList());
    }
}
