package com.wrobank.banking.common.domain;


public class UserWasNotFoundException extends IllegalArgumentException {
    public UserWasNotFoundException() {
        super();
    }

    public UserWasNotFoundException(String s) {
        super(s);
    }

    public UserWasNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWasNotFoundException(Throwable cause) {
        super(cause);
    }
}
