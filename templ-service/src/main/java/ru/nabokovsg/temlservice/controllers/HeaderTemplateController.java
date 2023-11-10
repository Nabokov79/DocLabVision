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
import ru.nabokovsg.temlservice.dto.header.NewHeaderTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.services.HeaderTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/header",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон отчета",
        description="API для работы с данными шаблона отчета")
public class HeaderTemplateController {

    private final HeaderTemplateService service;

    @Operation(summary = "Данные заголовка протокола/заключения")
    @PostMapping
    public ResponseEntity<ProtocolTemplateDto> save(@RequestBody @Valid NewHeaderTemplateDto headerDto) {
        return ResponseEntity.ok().body(service.save(headerDto));
    }
}
