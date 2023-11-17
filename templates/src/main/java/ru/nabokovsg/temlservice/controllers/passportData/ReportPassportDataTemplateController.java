package ru.nabokovsg.temlservice.controllers.passportData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.temlservice.dto.passportData.NewReportPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.services.passportData.ReportPassportDataTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/passport/report",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон данных паспорта объекта обследования(для отчета)",
        description="API для работы с шаблоном данных паспорта")
public class ReportPassportDataTemplateController {

    private final ReportPassportDataTemplateService service;

    @Operation(summary = "Добавить шаблон паспортных данных")
    @PostMapping
    public ResponseEntity<SectionTemplateDto> save(
        @RequestBody @Valid
        @Parameter(description = "Данные шаблона паспортных данных") NewReportPassportDataTemplateDto passportDataDto) {
        return ResponseEntity.ok().body(service.save(passportDataDto));
    }
}
