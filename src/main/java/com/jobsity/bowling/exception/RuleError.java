package com.jobsity.bowling.exception;

import lombok.Getter;

public enum RuleError {
    INVALID_PINS_KNOCKED_QTY("The quantity of pins knocked is not valid."),
    INVALID_FRAMES_QTY("Match with incorrect number of frames."),
    INVALID_FILE_DATA("File has invalid data.");

    @Getter
    private String message;

    RuleError(String message) {
        this.message = message;
    }
}
