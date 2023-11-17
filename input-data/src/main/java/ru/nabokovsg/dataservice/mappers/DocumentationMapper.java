package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.models.Documentation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    List<Documentation> mapToNewDocumentations(List<NewDocumentationDto> documentationsDto);

    List<Documentation> mapToUpdateDocumentation(List<UpdateDocumentationDto> documentationsDto);

    List<DocumentationDto> mapToDocumentationDto(List<Documentation> documentations);
}