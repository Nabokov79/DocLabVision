package ru.nabokovsg.temlservice.dto.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные нового подраздела раздела отчета")
public class NewSubsectionTemplateDto {

    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential subsection number should not be null")
    @Positive(message = "sequential subsection number can only be positive")
    private double sequentialSubsectionNumber;
    @Schema(description = "Название подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Текст подраздела")
    private String subsectionText;
    @Schema(description = "Тип данных подраздела")
    @NotBlank(message = "data type subsection should not be blank")
    private String dataType;
    @Schema(description = "Показать номер подраздела в документе")
    @NotNull(message = "subsection number should not be null")
    private boolean subsectionNumber;
    @Schema(description = "Индентификатор структурного подразделения организации")
    private Long divisionId;
    @Schema(description = "Вид структурного подразделения организации")
    private String divisionType;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String divisionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewSubsectionTemplateDto that = (NewSubsectionTemplateDto) o;
        return Double.compare(that.sequentialSubsectionNumber, sequentialSubsectionNumber) == 0
                                        && subsectionNumber == that.subsectionNumber
                                        && Objects.equals(subsectionName, that.subsectionName)
                                        && Objects.equals(subsectionText, that.subsectionText)
                                        && Objects.equals(dataType, that.dataType)
                                        && Objects.equals(divisionId, that.divisionId)
                                        && Objects.equals(divisionType, that.divisionType)
                                        && Objects.equals(divisionName, that.divisionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequentialSubsectionNumber, subsectionName
                            , subsectionText, dataType, subsectionNumber, divisionId, divisionType, divisionName);
    }
}