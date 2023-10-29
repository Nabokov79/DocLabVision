package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.norms.NewNormsDto;
import ru.nabokovsg.dataservice.dto.norms.NormsDto;
import ru.nabokovsg.dataservice.dto.norms.UpdateNormsDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeNormDto;
import ru.nabokovsg.dataservice.services.NormsService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/elements/norms",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Норма браковки элементов объекта",
        description="API для работы с данными типа объекта")
public class NormsController {

    private final NormsService service;

    @Operation(summary = "Добавление новых норм браковки")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeNormDto>> save(
                                       @RequestParam("objectsTypeId") @NotNull @NotEmpty
                                       @Parameter(description = "Индентификатор типа объекта") List<Long> objectsTypeId,
                                       @RequestBody @Valid
                                       @Parameter(description = "Нормы") List<NewNormsDto> normsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, normsDto));
    }

    @Operation(summary = "Изменение данных норм браковки")
    @PatchMapping
    public ResponseEntity<List<NormsDto>> update(@RequestBody @Valid
                                                 @Parameter(description = "Нормы") List<UpdateNormsDto> normsDto) {
        return ResponseEntity.ok().body(service.update(normsDto));
    }
}
