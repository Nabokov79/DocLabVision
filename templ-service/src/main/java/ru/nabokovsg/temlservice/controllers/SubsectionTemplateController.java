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
import ru.nabokovsg.temlservice.dto.subsection.NewSectionSubsectionTemplateDto;
import ru.nabokovsg.temlservice.services.SubsectionTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/section/sub",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела раздела отчета и протокола, заключения",
        description="API для работы с данными шаблона подраздела раздела отчета и протокола, заключения")
public class SubsectionTemplateController {

    private final SubsectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PostMapping("/report")
    public ResponseEntity<SectionTemplateDto> saveFromReportTemplate(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона титульного листа, заголовков") NewSectionSubsectionTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.saveFromReportTemplate(templateDto));
    }

    @Operation(summary = "Добавление нового шаблона документа")
    @PostMapping("/protocol")
    public ResponseEntity<ProtocolTemplateDto> saveFromProtocolTemplate(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона титульного листа, заголовков") NewSectionSubsectionTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.saveFromProtocolTemplate(templateDto));
    }
}