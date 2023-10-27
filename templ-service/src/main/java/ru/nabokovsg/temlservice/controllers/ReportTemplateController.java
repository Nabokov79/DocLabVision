package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.temlservice.dto.report.NewReportTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
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

    @Operation(summary = "Данные титульного листа отчета")
    @PostMapping
    public ResponseEntity<ReportTemplateDto> save(@RequestBody NewReportTemplateDto reportTemplateDto) {
        return ResponseEntity.ok().body(service.save(reportTemplateDto));
    }

    @Operation(summary = "Получить шаблон отчета")
    @GetMapping
    public ResponseEntity<ReportTemplateDto> get(
            @RequestParam(required = false)
            @Parameter(description = "Индентификатор") Long id,
            @RequestParam(required = false)
            @Parameter(description = "Индентификатор типа объекта") Long objectsTypeId,
            @RequestParam(required = false)
            @Parameter(description = "Индентификатор отчетного документа") Long reportingDocumentId) {
        return ResponseEntity.ok().body(service.get(id, objectsTypeId, reportingDocumentId));
    }
}