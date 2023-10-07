package ru.nabokovsg.dataservice.dto.subElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.place.NewPlaceDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные о подэлементе элемента объекта")
public class NewSubElementDto {

    @Schema(description = "Название подэлемента")
    @NotBlank(message = "sub element name should not be blank")
    private String subElementName;
    @Schema(description = "Участок подэлемента")
    private List<NewPlaceDto> places;

    @Override
    public String toString() {
        return "NewSubElementDto{" +
                "subElementName='" + subElementName + '\'' +
                ", places=" + places +
                '}';
    }
}