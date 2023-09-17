package ru.aston.trushanina_mu.task6;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private Address address;

    @Override
    public String toString() {
        return "\n --->  " +
                "name='" + firstName + '\'' +
                ", surName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address;
    }
}
