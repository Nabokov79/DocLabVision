package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.NewSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.SurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.dto.surveyObjectSurveys.UpdateSurveyObjectSurveysDto;
import ru.nabokovsg.dataservice.services.SurveyObjectSurveysService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/passport/surveys",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Обследование объекта",
        description="API для работы с данными обследований объекта")
public class SurveyObjectSurveysController {

    private final SurveyObjectSurveysService service;

    @Operation(summary = "Добавление нового обследования")
    @PostMapping
    public ResponseEntity<List<SurveyObjectSurveysDto>> save(
                                  @RequestParam @Parameter(description = "Индентификатор объекта обследования")
                                  @NotNull @Positive Long surveyObjectId,
                                  @RequestBody @Valid
                                  @Parameter(description = "Обследования") List<NewSurveyObjectSurveysDto> surveysDto) {
        return ResponseEntity.ok().body(service.save(surveyObjectId,surveysDto));
    }

    @Operation(summary = "Изменение данных адреса")
    @PatchMapping
    public ResponseEntity<List<SurveyObjectSurveysDto>> update(
                               @RequestParam @Parameter(description = "Индентификатор объекта обследования")
                               @NotNull @Positive Long surveyObjectId,
                               @RequestBody @Valid
                               @Parameter(description = "Обследования") List<UpdateSurveyObjectSurveysDto> surveysDto) {
        return ResponseEntity.ok().body(service.update(surveyObjectId, surveysDto));
    }
}