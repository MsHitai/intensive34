package ru.aston.trushanina_mu.task1;

import java.math.BigDecimal;
import java.util.List;

public interface TicketCalculation {

    BigDecimal getAmount();

    List<Order> getSortedOrders();
}
