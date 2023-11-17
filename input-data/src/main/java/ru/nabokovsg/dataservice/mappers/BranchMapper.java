package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.branch.BranchDto;
import ru.nabokovsg.dataservice.dto.branch.NewBranchDto;
import ru.nabokovsg.dataservice.dto.branch.ShortBranchDto;
import ru.nabokovsg.dataservice.dto.branch.UpdateBranchDto;
import ru.nabokovsg.dataservice.models.Branch;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    Branch mapToNewBranch(NewBranchDto branchDto);

    Branch mapToUpdateBranch(UpdateBranchDto branchDto);

    BranchDto mapToBranchDto(Branch branch);

    List<ShortBranchDto> mapToShortBranchDto(Set<Branch> branches);

    Branch mapToBranch(BranchDto branchDto);
}