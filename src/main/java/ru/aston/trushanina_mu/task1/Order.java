package ru.aston.trushanina_mu.task1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Order implements Discount {

    protected BigDecimal coefficient;
    protected BigDecimal amount;
    protected User user;
    protected int id;
}
