package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.models.Defect;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    Defect mapToNewDefect(NewDefectDto defectDto);

    Defect mapToUpdateDefect(UpdateDefectDto defectsDto);

    List<DefectDto> mapToDefectDto(List<Defect> defects);
}
