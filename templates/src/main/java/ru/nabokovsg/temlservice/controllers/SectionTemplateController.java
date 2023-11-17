package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.dto.section.NewReportSectionTemplateDto;
import ru.nabokovsg.temlservice.services.reports.SectionTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/report/section",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон раздела отчета",
        description="API для работы с данными шаблона раздела отчета")
public class SectionTemplateController {

    private final SectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона шаблона")
    @PostMapping
    public ResponseEntity<ReportTemplateDto> save(
                                            @RequestBody @Valid
                                            @Parameter(description = "Данные нового шаблона раздела отчета")
                                            NewReportSectionTemplateDto sectionTemplateDto) {
        return ResponseEntity.ok().body(service.save(sectionTemplateDto));
    }
}