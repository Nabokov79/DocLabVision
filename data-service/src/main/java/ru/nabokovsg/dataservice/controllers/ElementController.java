package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.element.ElementDto;
import ru.nabokovsg.dataservice.dto.element.NewElementDto;
import ru.nabokovsg.dataservice.dto.element.UpdateElementDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.services.ElementService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Элемент объекта",
        description="API для работы с данными елемента объекта")
public class ElementController {

    private final ElementService service;

    @Operation(summary = "Добавление новых элементов объекта")
    @PostMapping
    public ResponseEntity<List<ElementDto>> save(
            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
            @RequestBody @Parameter(description = "Список элементов") @Valid List<NewElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, elementsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<ElementDto>> update(
            @RequestBody @Parameter(description = "Список элементов") @Valid List<UpdateElementDto> elementsDto) {
        return ResponseEntity.ok().body(service.update(elementsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @GetMapping("/{objectsTypeId}")
    public ResponseEntity<ObjectsTypeDto> getAll(
            @PathVariable
            @Parameter(description = "Индентификатор типа объекта") @NotNull @Positive Long objectsTypeId) {
        return ResponseEntity.ok().body(service.getAll(objectsTypeId));
    }
}