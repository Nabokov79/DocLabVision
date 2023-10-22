package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.template.NewTemplateDataDto;
import ru.nabokovsg.temlservice.services.ReportTemplateService;

@RestController
@RequestMapping(
        value = "/template/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class ReportTemplateController {

    private final ReportTemplateService service;

    @Operation(summary = "Добавление нового шаблона документа")
    @PostMapping
    public ResponseEntity<ReportTemplateDto> save(
                  @RequestBody
                  @Parameter(description = "Данные шаблона отчета, протокола, заключения") NewTemplateDataDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}