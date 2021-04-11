package com.jobsity.bowling.exception;

public class InvalidMatchException extends RuntimeException {

    public InvalidMatchException() {
        super("Match with incorrect number of frames.");
    }
}
