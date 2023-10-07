package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.application.*;
import ru.nabokovsg.dataservice.services.ApplicationService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/applications",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Заявка",
        description="API для работы с данными заявки")
public class ApplicationController {

    private final ApplicationService service;

    @Operation(summary = "Добавление данных заявки")
    @PostMapping
    public ResponseEntity<List<ApplicationDto>> save(
            @RequestBody @Parameter(description = "Заявка") List<NewApplicationDto> applicationsDto) {
        return ResponseEntity.ok().body(service.save(applicationsDto));
    }

    @Operation(summary = "Изменение данных заявки")
    @PatchMapping
    public ResponseEntity<List<ApplicationDto>> update(
            @RequestBody @Parameter(description = "Заявка") List<UpdateApplicationDto> applicationsDto) {
        return ResponseEntity.ok().body(service.update(applicationsDto));
    }

    @Operation(summary = "Получение данных заявки")
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> get(@PathVariable @Parameter(description = "Индентификатор") Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @Operation(summary = "Получение полных данных о заявках")
    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAll(
               @RequestParam(value = "addressId", required = false)
               @Parameter(description = "Индентификатор адреса") Long addressId,
               @RequestParam(value = "startDate", required = false)
               @Parameter(description = "Дата начала периода, за который требуется выдать заявки") LocalDate startDatePeriod,
               @RequestParam(value = "endDate", required = false)
               @Parameter(description = "Дата окончания периода, за который требуется выдать заявки") LocalDate endDatePeriod,
               @RequestParam(value = "surveyObject", required = false)
               @Parameter(description = "Индентификатор объекта оследования") Long surveyObject,
               @RequestParam(value = "objectTypeId", required = false)
               @Parameter(description = "Индентификатор объекта оследования") Long objectTypeId,
               @RequestParam(value = "employeeId", required = false)
               @NotNull @Positive @Parameter(description = "Индентификатор сотрудника") Long employeeId,
               @RequestParam(value = "documentNumber", required = false)
               @NotNull @Positive @Parameter(description = "Номер документа") Integer documentNumber,
               @RequestParam(value = "status", required = false)
               @NotNull @Parameter(description = "Статус документа") String documentStatus,
               @RequestParam(value = "status", required = false)
               @NotNull @Parameter(description = "Статус заявки") String applicationStatus) {
              ApplicationSearchParametersDto parameters = new ApplicationSearchParametersDto(addressId, startDatePeriod
                                                                                     , endDatePeriod, surveyObject
                                                                                     , objectTypeId, employeeId
                                                                                     , documentNumber, applicationStatus
                                                                                     , documentStatus);
        return ResponseEntity.ok().body(service.getAll(parameters));
    }
}