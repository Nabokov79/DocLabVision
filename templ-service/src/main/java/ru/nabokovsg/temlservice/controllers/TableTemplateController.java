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
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;
import ru.nabokovsg.temlservice.services.TableTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы документа",
        description="API для работы с данными шаблона таблицы документа")
public class TableTemplateController {

    private final TableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/report")
    public ResponseEntity<SectionTemplateDto> addToSubsectionTemplate(
            @RequestBody @Valid @Parameter(description = "Данные шаблона таблицы") NewSubsectionTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.addToSubsectionTemplate(templateDto));
    }

    @Operation(summary = "Добавление нового шаблона таблицы")
    @PostMapping("/protocol")
    public ResponseEntity<ProtocolTemplateDto> addToProtocolTemplate(
            @RequestBody @Valid @Parameter(description = "Данные шаблона таблицы") NewProtocolTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.addToProtocolTemplate(templateDto));
    }
}
