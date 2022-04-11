package com.example.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByName() {
        //given
        Employee employee=new Employee("Arianit","Mehana",24,"Software Developer");
        underTest.save(employee);

        //when
        Optional<Employee> expected = underTest.findByName(employee.getName());

        //then
        assertTrue(expected.isPresent());
        ;
    }

    @Test
    void shouldNotFindByName() {
        //given
        Employee employee=new Employee("John","Doe",23,"Software Developer");

        //when
        Optional<Employee> expected = underTest.findByName(employee.getName());

        //then
        assertFalse(expected.isPresent());
        ;
    }
}