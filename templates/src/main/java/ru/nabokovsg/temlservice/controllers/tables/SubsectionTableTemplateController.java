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
import ru.nabokovsg.temlservice.dto.section.SectionTemplateDto;
import ru.nabokovsg.temlservice.dto.table.NewSubsectionTableTemplateDto;
import ru.nabokovsg.temlservice.services.tables.SubsectionTableTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/report/section/subsection/table",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон таблицы подраздела раздела отчета",
        description="API для работы с данными шаблона таблицы")
public class SubsectionTableTemplateController {

    private final SubsectionTableTemplateService service;

    @Operation(summary = "Добавление нового шаблона таблицы в подраздел раздела отчета")
    @PostMapping
    public ResponseEntity<SectionTemplateDto> save(
            @RequestBody
            @Valid @Parameter(description = "Данные шаблона таблицы") NewSubsectionTableTemplateDto templateDto) {
        return ResponseEntity.ok().body(service.save(templateDto));
    }
}