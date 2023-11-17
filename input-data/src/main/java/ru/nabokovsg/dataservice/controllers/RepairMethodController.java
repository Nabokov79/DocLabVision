package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypeRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.NewRepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.RepairMethodDto;
import ru.nabokovsg.dataservice.dto.repairMethod.UpdateRepairMethodDto;
import ru.nabokovsg.dataservice.services.RepairMethodService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/repair/method",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Способы ремонта",
        description="API для работы с данными нормативной документации")
public class RepairMethodController {

    private final RepairMethodService service;

    @Operation(summary = "Добавление новоых элементов объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeRepairMethodDto>> saveRepairMethod(
                              @RequestParam("objectsTypeId") @NotNull @NotEmpty
                              @Parameter(description = "Индентификатор типа объекта") List<Long> objectsTypeId,
                              @RequestBody @Valid
                              @Parameter(description = "Список способов ремонта") List<NewRepairMethodDto> methodsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, methodsDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<RepairMethodDto>> updateRepairMethod(
                           @RequestBody @Valid
                           @Parameter(description = "Список способов ремонта") List<UpdateRepairMethodDto> methodsDto) {
        return ResponseEntity.ok().body(service.update(methodsDto));
    }
}