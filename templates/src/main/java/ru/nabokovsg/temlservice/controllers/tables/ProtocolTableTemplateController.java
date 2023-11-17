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
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewProtocolTableTemplateDto;
import ru.nabokovsg.temlservice.services.tables.ProtocolTableTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/protocol/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы протокола/заключения",
        description="API для работы с данными шаблона таблицы протокола/заключения")
public class ProtocolTableTemplateController {

    private final ProtocolTableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы в протокол/заключение")
    @PostMapping
    public ResponseEntity<ProtocolTemplateDto> save(
            @RequestBody
            @Valid @Parameter(description = "Данные шаблона таблицы") NewProtocolTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}
