package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.temlservice.dto.client.ObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.models.PassportDataTemplate;

@Mapper(componentModel = "spring")
public interface PassportDataTemplateMapper {

    @Mappings({
            @Mapping(source = "passportData.sequentialSubsectionNumber", target = "sequentialNumber"),
            @Mapping(source = "passportData.dataName", target = "dataName")
    })
    PassportDataTemplate mapToPassportDataTemplate(ObjectPassportDataTemplateDto passportData);
}
