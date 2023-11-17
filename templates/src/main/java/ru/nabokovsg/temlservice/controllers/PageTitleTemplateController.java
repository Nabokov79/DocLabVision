package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.report.ReportTemplateDto;
import ru.nabokovsg.temlservice.services.reports.PageTitleTemplateService;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(
        value = "/template/report/title/page",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name="Шаблон титульного листа",
        description="API для работы с данными шаблона титульного листа")
public class PageTitleTemplateController {
    private final PageTitleTemplateService service;

    @Operation(summary = "Данные титульного листа отчета")
    @PostMapping
    public ResponseEntity<ReportTemplateDto> save(@RequestBody @Valid NewPageTitleTemplateDto pageTitleDto) {
        return ResponseEntity.ok().body(service.save(pageTitleDto));
    }
}