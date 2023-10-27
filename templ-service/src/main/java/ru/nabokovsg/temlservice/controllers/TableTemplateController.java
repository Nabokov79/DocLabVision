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
import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;
import ru.nabokovsg.temlservice.services.TableTemplateService;

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
    @PostMapping
    public ResponseEntity<TableTemplateDto> save(
            @RequestBody @Parameter(description = "Данные шаблона таблицы") NewTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}
