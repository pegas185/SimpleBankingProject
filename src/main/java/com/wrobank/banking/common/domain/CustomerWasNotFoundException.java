package com.wrobank.banking.common.domain;


public class CustomerWasNotFoundException extends IllegalArgumentException {
    public CustomerWasNotFoundException() {
        super();
    }

    public CustomerWasNotFoundException(String s) {
        super(s);
    }

    public CustomerWasNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerWasNotFoundException(Throwable cause) {
        super(cause);
    }
}
