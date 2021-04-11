package com.jobsity.bowling.util;

import com.jobsity.bowling.match.Match;

import java.io.FileNotFoundException;

/**
 * Interface to build a {@link Match}.
 * @param <IN> The type of input for the {@link Match} data.
 */
public interface MatchBuilder<IN> {

    Match build(IN input) throws FileNotFoundException;
}
