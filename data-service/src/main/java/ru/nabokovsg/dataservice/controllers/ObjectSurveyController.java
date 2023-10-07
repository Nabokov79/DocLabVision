package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectSurvey.NewObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.ShortObjectSurveyDto;
import ru.nabokovsg.dataservice.dto.objectSurvey.UpdateObjectSurveyDto;
import ru.nabokovsg.dataservice.services.ObjectSurveyService;

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
    public ResponseEntity<List<ShortObjectSurveyDto>> save(
                     @RequestBody @Parameter(description = "Объект обследования") List<NewObjectSurveyDto> objectsDto) {
        return ResponseEntity.ok().body(service.save(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @PatchMapping
    public ResponseEntity<List<ShortObjectSurveyDto>> update(
                    @RequestBody @Parameter(description = "Объект обследования") List<UpdateObjectSurveyDto> objectsDto) {
        return ResponseEntity.ok().body(service.update(objectsDto));
    }

    @Operation(summary = "Изменение сведений об объекте")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectSurveyDto> get(@PathVariable @Parameter(description = "Индентификатор объекта")
                                                             @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удаление объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Parameter(description = "Индентификатор объекта") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Объект был удален");
    }
}