package ru.nabokovsg.dataservice.dto.dataPassportOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные паспорта для изменения информации")
public class UpdateDataPassportOfObjectDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id object passport data template should not be blank")
    @Positive(message = "id object passport data template must be positive")
    private Long id;
    @Schema(description = "Индентификатор шаблона данных паспорта объекта")
    @NotNull(message = "id template should not be blank")
    @Positive(message = "id template can only be positive")
    private Long templateId;
    @Schema(description = "Значение данных паспорта объекта")
    @NotBlank(message = "meaning should not be blank")
    private String meaning;
}