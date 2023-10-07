package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.NormSearchParameters;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.models.Norm;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NormsMapper {

    Norm mapToNewNormsDto(NewNormsDto normDto);

    Norm mapToUpdateNormsDto(UpdateNormsDto normDto);

    List<NormsDto> mapToNormsDto(List<Norm> norms);

    NormSearchParameters mapToNormSearchParameters(NewNormsDto normDto);
}