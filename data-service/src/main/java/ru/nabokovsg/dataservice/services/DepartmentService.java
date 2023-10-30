package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.department.DepartmentDto;
import ru.nabokovsg.dataservice.dto.department.NewDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.department.UpdateDepartmentDto;
import ru.nabokovsg.dataservice.models.Licenses;

import java.util.List;

public interface DepartmentService {

    DepartmentDto save(NewDepartmentDto departmentDto);

    DepartmentDto update(UpdateDepartmentDto departmentDto);

    DepartmentDto get(Long id);

    List<ShortDepartmentDto> getAll(Long branchId);

    void addLicense(Long id, Licenses license);

    void delete(Long id);
}