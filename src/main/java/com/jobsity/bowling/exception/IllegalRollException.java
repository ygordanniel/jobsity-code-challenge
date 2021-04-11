package com.jobsity.bowling.exception;

public class IllegalRollException extends RuntimeException {

    public IllegalRollException(RuleError error) {
        super(error.getMessage());
    }
}
