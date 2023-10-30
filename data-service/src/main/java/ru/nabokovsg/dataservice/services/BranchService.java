package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.branch.NewBranchDto;
import ru.nabokovsg.dataservice.dto.branch.ShortBranchDto;
import ru.nabokovsg.dataservice.dto.branch.UpdateBranchDto;
import ru.nabokovsg.dataservice.models.Licenses;

import java.util.List;

public interface BranchService {

    BranchDto save(NewBranchDto branchDto);

    BranchDto update(UpdateBranchDto branchDto);

    BranchDto get(Long id);

    List<ShortBranchDto> getAll(Long organizationId);

    void addLicense(Long id, Licenses license);

    void  delete(Long id);
}