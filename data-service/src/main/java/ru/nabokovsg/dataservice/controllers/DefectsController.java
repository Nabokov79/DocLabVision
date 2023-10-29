package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.defect.DefectDto;
import ru.nabokovsg.dataservice.dto.defect.NewDefectDto;
import ru.nabokovsg.dataservice.dto.defect.UpdateDefectDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeDefectDto;
import ru.nabokovsg.dataservice.services.DefectsService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/elements/defects",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Дефекты элемента объекта",
        description="API для работы с дефектами элементов объекта")
public class DefectsController {

    private final DefectsService service;

    @Operation(summary = "Добавление новых дефектов объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeDefectDto>> save(
            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
            @RequestBody @Valid @Parameter(description = "Дефекты элемента") List<NewDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, defectsDto));
    }

    @Operation(summary = "Изменение данных дефектов объекта")
    @PatchMapping
    public ResponseEntity<List<DefectDto>> update(
            @RequestBody @Valid @Parameter(description = "Дефекты элемента") List<UpdateDefectDto> defectsDto) {
        return ResponseEntity.ok().body(service.update(defectsDto));
    }
}