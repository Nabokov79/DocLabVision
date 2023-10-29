package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.NewObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.ObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectPassportDataTemplate.UpdateObjectPassportDataTemplateDto;
import ru.nabokovsg.dataservice.dto.objectsType.ObjectsTypePassportDataTemplateDto;
import ru.nabokovsg.dataservice.services.ObjectPassportDataTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/templates",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон паспортных данных объекта",
        description="API для работы с данными шаблона паспортных данных объекта")
public class ObjectPassportDataTemplateController {

    private final ObjectPassportDataTemplateService service;

    @Operation(summary = "Добавление новоых элементов объекта")
    @PostMapping
    public ResponseEntity<List<ObjectsTypePassportDataTemplateDto>> save(
                                            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
                                            @RequestBody @Valid
                                            @Parameter(description = "Шаблон паспортных данных объекта")
                                                                  List<NewObjectPassportDataTemplateDto> templatesDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, templatesDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<ObjectPassportDataTemplateDto>> update(
                                               @RequestBody @Valid
                                               @Parameter(description = "Шаблон паспортных данных объекта")
                                                               List<UpdateObjectPassportDataTemplateDto> templatesDto) {
        return ResponseEntity.ok().body(service.update(templatesDto));
    }
}