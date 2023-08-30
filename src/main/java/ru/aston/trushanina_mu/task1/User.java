package ru.aston.trushanina_mu.task1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int age;
    private String lastName;
    private String firstName;
}
