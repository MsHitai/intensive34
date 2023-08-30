package ru.aston.trushanina_mu.task1;

import java.math.BigDecimal;

public class OrderTicket extends Order {

    private final Route route;

    public OrderTicket(Route route) {
        this.route = route;
    }

    public OrderTicket(BigDecimal coefficient, BigDecimal amount, User user, int id, Route route) {
        super(coefficient, amount, user, id);
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public BigDecimal getDiscount() {
        BigDecimal result = new BigDecimal("0.0");
        switch (route) {
            case TRAMWAY -> result = new BigDecimal("0.06");
            case CITY_BUS -> result = new BigDecimal("0.05");
            case OUTSKIRTS_BUS -> result = new BigDecimal("0.07");
        }
        return coefficient.add(result);
    }
}
