package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.NewSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.SurveyObjectRepairDto;
import ru.nabokovsg.dataservice.dto.surveyObjectRepair.UpdateSurveyObjectRepairDto;
import ru.nabokovsg.dataservice.services.SurveyObjectRepairService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/passport/repairs",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Ремонт объекта",
        description="API для работы с данными ремонтов объекта")
public class SurveyObjectRepairController {

    private final SurveyObjectRepairService service;

    @Operation(summary = "Добавление нового данных ремонтов объектов")
    @PostMapping
    public ResponseEntity<List<SurveyObjectRepairDto>> save(
            @RequestParam @Parameter(description = "Индентификатор объекта обследования")
            @NotNull @Positive Long surveyObjectId,
            @RequestBody @Validated @Parameter(description = "Ремонты") List<NewSurveyObjectRepairDto> repairsDto) {
        return ResponseEntity.ok().body(service.save(surveyObjectId,repairsDto));
    }

    @Operation(summary = "Изменение данных ремонтов объектов")
    @PatchMapping
    public ResponseEntity<List<SurveyObjectRepairDto>> update(
            @RequestParam @Parameter(description = "Индентификатор объекта обследования")
            @NotNull @Positive Long surveyObjectId,
            @RequestBody @Validated @Parameter(description = "Ремонты") List<UpdateSurveyObjectRepairDto> repairsDto) {
        return ResponseEntity.ok().body(service.update(surveyObjectId, repairsDto));
    }
}