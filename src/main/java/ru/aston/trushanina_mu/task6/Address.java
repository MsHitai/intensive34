package ru.aston.trushanina_mu.task6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private City city;
    private String street;
    private String house;
}
