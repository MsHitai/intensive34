package ru.aston.trushanina_mu.task1;

import java.math.BigDecimal;

public class DailyOrder extends Order {
    private final BigDecimal initSum;

    public DailyOrder(BigDecimal initSum) {
        super();
        this.initSum = initSum;
    }

    public DailyOrder(BigDecimal initSum, User user, BigDecimal coefficient, int id, BigDecimal amount) {
        super(coefficient, amount, user, id);
        this.initSum = initSum;
    }

    public BigDecimal getInitSum() {
        return initSum;
    }

    @Override
    public BigDecimal getDiscount() {
        return coefficient.add(initSum);
    }
}
