package ru.nabokovsg.dataservice.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.branch.ShortBranchDto;
import ru.nabokovsg.dataservice.dto.department.ShortDepartmentDto;
import ru.nabokovsg.dataservice.dto.employee.ShortEmployeeDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.organization.ShortOrganizationDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные о выполненной работе")
public class ApplicationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Организация")
    private ShortOrganizationDto organization;
    @Schema(description = "Филиал организации")
    private ShortBranchDto branch;
    @Schema(description = "Подразделение филиала организации")
    private ShortDepartmentDto department;
    @Schema(description = "Дата проведения обследования/контроля")
    private LocalDate date;
    @Schema(description = "Адрес места проведения обследования/контроля")
    private AddressDto address;
    @Schema(description = "Объект обследования/контроля")
    private ShortSurveyObjectDto surveyObject;
    @Schema(description = "Вид выполненной работы")
    private String workPerformed;
    @Schema(description = "Отчетный документ")
    private ReportingDocumentDto reportingDocument;
    @Schema(description = "Список индентификаторов сотрудников, проводивших обследование/контроль объекта")
    private List<ShortEmployeeDto> employees;
    @Schema(description = "Основание для проведения работы по обследованию")
    private String taskSource;
    @Schema(description = "Необходимость выполнения чертежа")
    @NotNull(message = "task source should not be blank")
    private Boolean needDrawing;
    @Schema(description = "Комментари")
    private String comment;
}