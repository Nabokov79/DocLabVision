package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.services.DataPassportOfObjectService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/passport/object",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные паспорта объекта",
        description="API для работы с данными паспорта объекта")
public class DataPassportOfObjectController {

    private final DataPassportOfObjectService service;

    @Operation(summary = "Добавление нового данных паспорта объекта")
    @PostMapping
    public ResponseEntity<List<DataPassportOfObjectDto>> save(
         @RequestParam @Parameter(description = "Индентификатор объекта обследования")
         @NotNull @Positive Long surveyObjectId,
         @RequestBody @Valid @Parameter(description = "Данные паспорта") List<NewDataPassportOfObjectDto> dataDto) {
        return ResponseEntity.ok().body(service.save(surveyObjectId, dataDto));
    }

    @Operation(summary = "Изменение данных паспорта объекта")
    @PatchMapping
    public ResponseEntity<List<DataPassportOfObjectDto>> update(
            @RequestParam @Parameter(description = "Индентификатор объекта обследования")
            @NotNull @Positive Long surveyObjectId,
            @RequestBody @Valid
            @Parameter(description = "Данные паспорта") List<UpdateDataPassportOfObjectDto> dataDto) {
        return ResponseEntity.ok().body(service.update(surveyObjectId, dataDto));
    }
}