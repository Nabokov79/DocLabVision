package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectsType.NewObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.ShortObjectsTypeDto;
import ru.nabokovsg.dataservice.dto.objectsType.UpdateObjectsTypeDto;
import ru.nabokovsg.dataservice.services.ObjectsTypeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/objects/type",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Тип объекта",
        description="API для работы с данными типа объекта")
public class ObjectsTypeController {

    private final ObjectsTypeService service;

    @Operation(summary = "Добавление данных типа объекта")
    @PostMapping
    public ResponseEntity<List<ShortObjectsTypeDto>> save(
                                      @RequestBody @Valid
                                      @Parameter(description = "Тип объекта") List<NewObjectsTypeDto> objectsTypeDto ) {
        return ResponseEntity.ok().body(service.save(objectsTypeDto));
    }

    @Operation(summary = "Изменение данных типа объекта")
    @PatchMapping
    public ResponseEntity<List<ShortObjectsTypeDto>> update(
                                    @RequestBody @Valid
                                    @Parameter(description = "Тип объекта") List<UpdateObjectsTypeDto> objectsTypeDto) {
        return ResponseEntity.ok().body(service.update(objectsTypeDto));
    }

    @Operation(summary = "Удаление типа объекта")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectsTypeDto> get(@PathVariable @NotNull @Positive
                                              @Parameter(description = "Индентификатор типа объекта") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Удаление типа объекта")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор типа объекта") Long id) {
        service.delete(id);
        return ResponseEntity.ok("Тип объекта удален.");
    }
}