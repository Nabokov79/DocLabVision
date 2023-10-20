package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.services.TemplateService;

@RestController
@RequestMapping(
        value = "/template",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class TemplateController {

    private final TemplateService service;

    @Operation(summary = "Добавление нового шаблона документа")
    @PostMapping
    public ResponseEntity<DocumentTemplateDto> save(@RequestBody
                                       @Parameter(description = "Данные шаблона отчета, протокола, заключения")
                                       NewTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}