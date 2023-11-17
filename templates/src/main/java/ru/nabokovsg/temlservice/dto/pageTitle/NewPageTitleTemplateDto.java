package ru.nabokovsg.temlservice.dto.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.dto.header.NewPageTitleHeaderTemplateDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового заголовка листа")
public class NewPageTitleTemplateDto {

    @Schema(description = "Индентификатор типа объекта")
    @NotNull(message = "object type id should not be null")
    @Positive(message = "object type id must be positive")
    private Long objectsTypeId;
    @Schema(description = "Индентификатор типа отчетного документа")
    @NotNull(message = "reporting document id should not be null")
    @Positive(message = "reporting document id must be positive")
    private Long reportingDocumentId;
    @Schema(description = "Данные нового заголовка")
    @NotNull(message = "page header should not be null")
    @Valid
    private NewPageTitleHeaderTemplateDto header;
    @Schema(description = "Строка наименования объекта")
    @NotBlank(message = "object string should not be blank")
    private String objectString;
    @Schema(description = "Строка наименования места расположения объекта")
    @NotBlank(message = "installation location string should not be blank")
    private String installationLocationString;
    @Schema(description = "Строка указания адреса")
    @NotBlank(message = "address string should not be blank")
    private String addressString;
    @Schema(description = "Индентификатор сотрудника")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id can only be positive")
    private Long employeeId;
    @Schema(description = "Населенный пункт")
    @NotBlank(message = "city should not be blank")
    private String city;
}