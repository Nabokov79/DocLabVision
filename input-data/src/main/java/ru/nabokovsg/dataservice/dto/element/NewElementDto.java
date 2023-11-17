package ru.nabokovsg.dataservice.dto.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.subElement.NewSubElementDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового элемента объекта")
public class NewElementDto {

    @Schema(description = "Название элемента")
    @NotBlank(message = "element name should not be blank")
    private String elementName;
    @Schema(description = "Подэлементы элемента")
    private List<NewSubElementDto> subElements;

    @Override
    public String toString() {
        return "NewElementDto{" +
                "elementName='" + elementName + '\'' +
                ", subElements=" + subElements +
                '}';
    }
}