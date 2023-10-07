package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDataDto;
import ru.nabokovsg.dataservice.services.ObjectsTypeDataService;


@RestController
@RequestMapping(
        value = "/data/object/type/data",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные объекта",
        description="API для работы с данными типа объекта")
public class ObjectsTypeDataController {

    private final ObjectsTypeDataService service;

    @Operation(summary = "Получить данные типа объекта")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectsTypeDataDto> get(
            @PathVariable @Parameter(description = "Индентификатор данных типа объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }
}