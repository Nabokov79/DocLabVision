package ru.nabokovsg.dataservice.dto.plotOfObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового участка типа объекта обследования")
public class NewPlotOfObjectDto {

    @Schema(description = "Название участка")
    @NotBlank(message = "plot name should not be blank")
    private String plotName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPlotOfObjectDto that = (NewPlotOfObjectDto) o;
        return Objects.equals(plotName, that.plotName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plotName);
    }
}