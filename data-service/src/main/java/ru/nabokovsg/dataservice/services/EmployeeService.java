package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.employee.EmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.UpdateEmployeeDto;
import ru.nabokovsg.dataservice.dto.employee.NewEmployeeDto;
import ru.nabokovsg.dataservice.models.Employee;

import java.util.List;

public interface EmployeeService {

    ShortEmployeeDto save(NewEmployeeDto employeeDto);

    ShortEmployeeDto update(UpdateEmployeeDto employeeDto);

    EmployeeDto get(Long id);

    Employee getById(Long id);

    List<ShortEmployeeDto> getAll();

    List<Employee> getAllById(List<Long> ids);

    void delete(Long id);
}
