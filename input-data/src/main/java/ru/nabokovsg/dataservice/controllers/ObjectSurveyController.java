package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.surveyObject.NewSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.SurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.ShortSurveyObjectDto;
import ru.nabokovsg.dataservice.dto.surveyObject.UpdateSurveyObjectDto;
import ru.nabokovsg.dataservice.services.ObjectSurveyService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/objects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Объект обследования",
        description="API для работы с данными объекта обследования")
public class ObjectSurveyController {

    private final ObjectSurveyService service;

    @Operation(summary = "Добавление сведений об объекте")
    @PostMapping
    public ResponseEntity<List<ShortSurveyObjectDto>> save(
                                  @RequestBody @Valid
                                  @Parameter(description = "Объект обследования") List<NewSurveyObjectDto> objectsDto) {
        return ResponseEntity.ok().body(service.save(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @PatchMapping
    public ResponseEntity<List<ShortSurveyObjectDto>> update(
                               @RequestBody @Valid
                               @Parameter(description = "Объект обследования") List<UpdateSurveyObjectDto> objectsDto) {
        return ResponseEntity.ok().body(service.update(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @GetMapping("/{id}")
    public ResponseEntity<SurveyObjectDto> get(@PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удаление объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор объекта") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Объект был удален");
    }
}