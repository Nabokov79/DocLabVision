package ru.nabokovsg.dataservice.dto.surveyObjectPassport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.SurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.SurveyObjectSurveysDto;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Паспортные данные объекта обследования")
public class SurveyObjectPassportDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Объект обследования")
    private ShortSurveyObjectDto object;
    @Schema(description = "Список проведенных обследований")
    private List<SurveyObjectSurveysDto> surveys;
    @Schema(description = "Список произведенных ремонтов")
    private List<SurveyObjectRepairDto> repairs;
    @Schema(description = "Паспортные данные объекта обследования")
    private Set<DataPassportOfObjectDto> dataPassport;
}