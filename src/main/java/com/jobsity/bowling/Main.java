package com.jobsity.bowling;

import com.jobsity.bowling.match.Match;
import com.jobsity.bowling.util.MatchBuilder;
import com.jobsity.bowling.util.TsvFileMatchBuilder;

import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if(Objects.isNull(args) || args.length == 0) {
            System.out.println("Please provide a file path with the Match data.");
        }
        MatchBuilder<String> tsvFileMatchBuilder = new TsvFileMatchBuilder();
        Match match = tsvFileMatchBuilder.build(args[0]);
        match.displayScoreboard();
    }
}
