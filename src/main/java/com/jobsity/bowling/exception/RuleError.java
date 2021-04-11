package com.jobsity.bowling.exception;

import lombok.Getter;

public enum RuleError {
    INVALID_PINS_KNOCKED_QTY("The quantity of pins knocked is not valid.");

    @Getter
    private String message;

    RuleError(String message) {
        this.message = message;
    }
}
