package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.services.ReportingDocumentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/applications/document",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Тип документа",
        description="API для работы с данными типа документа")
public class ReportingDocumentController {

    private final ReportingDocumentService service;

    @Operation(summary = "Добавление новых типов отчетных документов")
    @PostMapping
    public ResponseEntity<List<ReportingDocumentDto>> save(
              @RequestBody @Valid
              @Parameter(description = "Типы отчетного документа") List<NewReportingDocumentDto> reportingDocumentDto) {
        return ResponseEntity.ok().body(service.save(reportingDocumentDto));
    }

    @Operation(summary = "Изменение данных типов отчетных документа")
    @PatchMapping
    public ResponseEntity<List<ReportingDocumentDto>> update(
           @RequestBody @Valid
           @Parameter(description = "Типы отчетного документа") List<UpdateReportingDocumentDto> reportingDocumentDto) {
        return ResponseEntity.ok().body(service.update(reportingDocumentDto));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping("/{id}")
    public ResponseEntity<ReportingDocumentDto> get(
                                               @PathVariable @NotNull @Positive
                                               @Parameter(description = "Индентификатор отчетного документа") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение данных типов отчетных документов")
    @GetMapping
    public ResponseEntity<List<ReportingDocumentDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Удаление типа отчетного документа")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive
                                         @Parameter(description = "Индентификатор отчетного документа") Long id) {
        service.delete(id);
        return ResponseEntity.ok("удалено");
    }
}