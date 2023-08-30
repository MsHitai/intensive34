package ru.aston.trushanina_mu.task1;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AllTickets extends Order implements TicketCalculation {
    private List<Order> orders;

    public AllTickets(BigDecimal coefficient, BigDecimal amount, User user, int id, List<Order> orders) {
        super(coefficient, amount, user, id);
        this.orders = orders;
    }

    public AllTickets(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public BigDecimal getAmount() {
        BigDecimal amount = new BigDecimal("0.0");
        for (Order order : orders) {
            amount = amount.add(order.getAmount());
        }
        return amount;
    }

    @Override
    public List<Order> getSortedOrders() {
        return orders.stream()
                .sorted(Comparator.comparing(order -> order.getUser().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getDiscount() {
        return coefficient;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
