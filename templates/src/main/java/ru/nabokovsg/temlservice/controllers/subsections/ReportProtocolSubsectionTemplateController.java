package ru.nabokovsg.temlservice.controllers.subsections;

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
import ru.nabokovsg.temlservice.dto.subsection.NewReportProtocolSubsectionTemplateDto;
import ru.nabokovsg.temlservice.services.subsections.reports.ReportProtocolSubsectionTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/report/protocol/subsection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела раздела отчета и протокола, заключения",
        description="API для работы с данными шаблона подраздела раздела отчета и протокола, заключения")
public class ReportProtocolSubsectionTemplateController {

    private final ReportProtocolSubsectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона подраздела раздела отчета")
    @PostMapping
    public ResponseEntity<ReportProtocolTemplateDto> save(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона подразделов") NewReportProtocolSubsectionTemplateDto protocolDto) {
        return ResponseEntity.ok().body(service.save(protocolDto));
    }
}