package org.mypet.accountservice.exception;

public class AccountIsClosedException extends RuntimeException {
    private static final String MESSAGE = "Account is closed";

    public AccountIsClosedException() {
        super(MESSAGE);
    }
}
