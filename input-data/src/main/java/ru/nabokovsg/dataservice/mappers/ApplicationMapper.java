package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.ObjectsIds;
import ru.nabokovsg.dataservice.dto.application.ApplicationDto;
import ru.nabokovsg.dataservice.dto.application.NewApplicationDto;
import ru.nabokovsg.dataservice.dto.application.UpdateApplicationDto;
import ru.nabokovsg.dataservice.models.Application;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    Application mapToNewApplication(NewApplicationDto applicationDto);

    ApplicationDto mapToApplicationDto(Application application);

    Application mapToUpdateApplication(UpdateApplicationDto applicationDto);

    List<ApplicationDto> mapToApplicationsDto(List<Application> applications);

    ObjectsIds mapFromNewApplicationDto(NewApplicationDto applicationDto);

    ObjectsIds mapFromUpdateApplicationDto(UpdateApplicationDto applicationDto);
}