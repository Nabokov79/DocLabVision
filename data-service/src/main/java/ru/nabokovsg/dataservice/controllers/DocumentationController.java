package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDocumentationDataDto;
import ru.nabokovsg.dataservice.services.DocumentationService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/object/type/data/documentations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Нормативная документация",
     description="API для работы с данными нормативной документации")
public class DocumentationController {

    private final DocumentationService service;

    @Operation(summary = "Добавление нового нормативного документа")
    @PostMapping
    public ResponseEntity<List<ObjectsTypeDocumentationDataDto>> save(
                                            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
                                            @RequestBody @Parameter(description = "Нормативный документ")
                                            @Valid List<NewDocumentationDto> documentationsDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId,documentationsDto));
    }

    @Operation(summary = "Изменение данных нормативного документа")
    @PatchMapping
    public ResponseEntity<List<DocumentationDto>> update(
                                            @RequestBody @Parameter(description = "Нормативный документ")
                                            @Valid List<UpdateDocumentationDto> documentationsDto) {
        return ResponseEntity.ok().body(service.update(documentationsDto));
    }
}