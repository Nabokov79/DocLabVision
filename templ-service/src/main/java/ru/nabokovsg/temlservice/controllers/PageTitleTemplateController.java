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
import ru.nabokovsg.temlservice.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.temlservice.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.temlservice.services.PageTitleTemplateService;

@RestController
@RequestMapping(
        value = "/template/page/title",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон титульного листа отчета и заголовков протоколов, заключений",
        description="API для работы с данными титульного листа отчета и заголовков протоколов, заключений")
public class PageTitleTemplateController {

    private final PageTitleTemplateService service;

    @Operation(summary = "Добавление нового шаблона документа")
    @PostMapping
    public ResponseEntity<PageTitleTemplateDto> save(
            @RequestBody
            @Parameter(description = "Данные шаблона титульного листа, заголовков") NewPageTitleTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}