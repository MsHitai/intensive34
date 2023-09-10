package ru.aston.trushanina_mu.task3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Order {

    private Long id;
    private LocalDateTime createdOn;
    private LocalDateTime deliveredOn;
}
