package com.example.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService underTest;

    @BeforeEach
    void setUp() {
        underTest=new EmployeeService(employeeRepository);
    }

    @Test
    void canShowAllEmployees() {
        //when
        underTest.showAllEmployees();

        //then
        verify(employeeRepository).findAll();

    }

    @Test
    void canShowEmployeeByID() {
        //given
        Long id =1L;

        //when
        underTest.showEmployee(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor=ArgumentCaptor.forClass(Long.class);
        verify(employeeRepository).findById(longArgumentCaptor.capture());
    }

    @Test
    void canRegisterEmployee() {
        //given
        Employee employee=new Employee("Arianit","Mehana",24,"Software Developer");

        //when
        underTest.registerEmployee(employee);

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor=ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertEquals(capturedEmployee,employee);
    }

    @Test
    void willNotRegisterWhenEmployeeAlreadyExists() {
        //given
        Optional<Employee> employee= Optional.of(new Employee(1L, "Arianit", "Mehana", 21, "test"));
        given(employeeRepository.findByName(employee.get().getName())).willReturn(employee);

        //when
        //then
        assertThatThrownBy(()->underTest.registerEmployee(employee.get()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Employee already exists");
    }

    @Test
    void canUpdateEmployee() {
        //given
        Optional<Employee> employee= Optional.of(new Employee(1L, "Arianit", "Mehana", 21, "test"));
        given(employeeRepository.findById(1L)).willReturn(employee);
        String name="Enver";
        String position="Cook";


        //when
        underTest.updateEmployee(1L,name,position);

        //then
        assertEquals(name,employee.get().getName());
        assertEquals(position,employee.get().getPosition());
    }

    @Test
    void willNotUpdateWhenEmployeeDoesNotExist() {
        //given
        Long id=1L;
        String name="Enver";
        String position="Cook";

        //when
        //then
        assertThatThrownBy(()->underTest.updateEmployee(id,name,position))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Employee with id"+id+"does not exist");
    }

    @Test
    void canDeleteEmployee() {
        //given
        Long id=1L;
        Optional<Employee> employee= Optional.of(new Employee(id, "Arianit", "Mehana", 21, "test"));
        given(employeeRepository.findById(id)).willReturn(employee);


        //when
        underTest.deleteEmployee(id);

        //then
        verify(employeeRepository).deleteById(id);
    }

    @Test
    void willNotDeleteWhenEmployeeDoesNotExist() {
        //given
        Long id=1L;

        //when
        //then
        assertThatThrownBy(()->underTest.deleteEmployee(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Employee does not exist");
    }
}