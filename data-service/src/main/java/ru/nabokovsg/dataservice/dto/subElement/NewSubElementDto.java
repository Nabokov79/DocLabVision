package ru.nabokovsg.dataservice.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о подэлементе элемента объекта")
public class NewSubElementDto {

    @Schema(description = "Название подэлемента")
    @NotBlank(message = "sub element name should not be blank")
    private String subElementName;
}