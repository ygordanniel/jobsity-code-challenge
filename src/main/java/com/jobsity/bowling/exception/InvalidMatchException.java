package com.jobsity.bowling.exception;

public class InvalidMatchException extends RuntimeException {

    public InvalidMatchException(RuleError error) {
        super(error.getMessage());
    }
}
