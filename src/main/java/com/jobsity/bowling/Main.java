package com.jobsity.bowling;

import com.jobsity.bowling.match.Match;
import com.jobsity.bowling.util.MatchBuilder;
import com.jobsity.bowling.util.TsvFileMatchBuilder;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            MatchBuilder<String> tsvFileMatchBuilder = new TsvFileMatchBuilder();
            Match match = tsvFileMatchBuilder.build(args[0]);
            match.displayScoreboard();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
