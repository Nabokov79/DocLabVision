package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import java.util.List;

public interface DefectsService {

    List<DefectDto> save(List<Long> objectsTypeId, List<NewDefectDto> defects);

    List<DefectDto> update(List<UpdateDefectDto> defects);
}