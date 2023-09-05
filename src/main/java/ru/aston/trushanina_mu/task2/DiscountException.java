package ru.aston.trushanina_mu.task2;

public class DiscountException extends RuntimeException {

    public DiscountException(int errorCode, String message) {
        super(message);
    }
}
