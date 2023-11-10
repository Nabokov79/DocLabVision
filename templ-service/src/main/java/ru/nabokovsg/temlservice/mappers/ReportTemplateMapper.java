package ru.nabokovsg.temlservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.models.PageTitleTemplate;
import ru.nabokovsg.temlservice.models.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplateDto mapToReportTemplateDto(ReportTemplate reportTemplate);

   @Mappings({
           @Mapping(source = "objectsTypeId" , target = "objectsTypeId"),
           @Mapping(source = "reportingDocumentId" , target = "reportingDocumentId"),
           @Mapping(source = "pageTitle" , target = "pageTitle"),
           @Mapping( target = "id", ignore = true)
   })
    ReportTemplate mapToNewReportTemplate(PageTitleTemplate pageTitle
                                        , Long objectsTypeId
                                        , Long reportingDocumentId);
}