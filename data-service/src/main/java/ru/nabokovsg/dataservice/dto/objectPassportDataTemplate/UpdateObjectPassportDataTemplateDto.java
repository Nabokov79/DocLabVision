package ru.nabokovsg.dataservice.dto.objectPassportDataTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для изменения шаблона паспортных данных объекта")
public class UpdateObjectPassportDataTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be blank")
    @Positive(message = "id must be positive")
    private Long id;
    @Schema(description = "Номер подраздела")
    @NotNull(message = "data sequence number must not be null")
    @Positive(message = "data sequence number must be negative")
    private Double sequentialSubsectionNumber;
    @Schema(description = "Название")
    @NotBlank(message = "data name must not be null")
    private String dataName;
    @Schema(description = "Применить данные в протоколе - true - да, false- нет")
    @NotNull(message = "applyProtocol must not be null")
    private Boolean applyProtocol;
}