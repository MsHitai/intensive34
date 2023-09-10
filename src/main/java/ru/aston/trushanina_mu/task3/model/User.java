package ru.aston.trushanina_mu.task3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private Long orderId;
}
