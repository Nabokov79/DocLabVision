package ru.nabokovsg.dataservice.dto.place;

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
@Schema(description = "Наименование нового места замера")
public class NewPlaceDto {

    @Schema(description = "Место замера на элементе")
    @NotBlank(message = "element place should not be blank")
    private String placeName;

    @Override
    public String toString() {
        return "NewPlaceDto{" +
                "placeName='" + placeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPlaceDto that = (NewPlaceDto) o;
        return Objects.equals(placeName, that.placeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName);
    }
}
