package ru.aston.trushanina_mu.task2;

public class OrderException extends RuntimeException {

    public OrderException(int errorCode, String message) {
        super(message);
    }
}
