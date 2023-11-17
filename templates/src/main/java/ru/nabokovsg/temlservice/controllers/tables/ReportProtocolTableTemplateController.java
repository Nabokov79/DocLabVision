package ru.nabokovsg.temlservice.controllers.tables;

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
import ru.nabokovsg.temlservice.dto.reportProtocol.ReportProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.services.tables.ReportProtocolTableTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/report/protocol/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы протокола отчета",
        description="API для работы с данными шаблона таблицы протокола отчета")
public class ReportProtocolTableTemplateController {

    private final ReportProtocolTableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы в протокол отчета")
    @PostMapping
    public ResponseEntity<ReportProtocolTemplateDto> save(
            @RequestBody
            @Valid @Parameter(description = "Данные шаблона таблицы") NewProtocolTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}