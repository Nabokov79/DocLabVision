package ru.nabokovsg.dataservice.dto.subElement;

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
@Schema(description = "Данные о подэлементе элемента объекта")
public class NewSubElementDto {

    @Schema(description = "Порядковый номер подэлемента")
    @NotNull(message = "ordinalNumberSubElement should not be blank")
    @Positive(message = "ordinalNumberSubElement can only be positive")
    private Integer ordinalNumberSubElement;
    @Schema(description = "Название подэлемента")
    @NotBlank(message = "sub element name should not be blank")
    private String subElementName;



}