package org.mypet.accountservice.exception;

public class NotEnoughBalanceException extends RuntimeException {
    private static final String MESSAGE = "Not enough funds on the balance";

    public NotEnoughBalanceException() {
        super(MESSAGE);
    }
}
