package com.example.test.employee;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )


    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String position;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, Integer age, String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
    }

    public Employee(String name, String surname, Integer age, String position) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
    }


}

