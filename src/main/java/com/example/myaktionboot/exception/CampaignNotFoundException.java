package com.example.myaktionboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException() {
    }

    public CampaignNotFoundException(String message) {
        super(message);
    }

    public CampaignNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampaignNotFoundException(Throwable cause) {
        super(cause);
    }

    public CampaignNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
