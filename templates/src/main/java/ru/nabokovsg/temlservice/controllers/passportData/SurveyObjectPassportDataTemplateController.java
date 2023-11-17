package ru.nabokovsg.temlservice.controllers.passportData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.temlservice.dto.passportData.NewSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.SurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.passportData.UpdateSurveyObjectPassportDataTemplateDto;
import ru.nabokovsg.temlservice.services.SurveyObjectPassportDataTemplateService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(
        value = "templates/passport/data",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон паспортных данных объекта",
        description="API для работы с данными шаблона паспортных данных объекта")
public class SurveyObjectPassportDataTemplateController {

    private final SurveyObjectPassportDataTemplateService service;

    @Operation(summary = "Добавление новоых элементов объекта")
    @PostMapping
    public ResponseEntity<List<SurveyObjectPassportDataTemplateDto>> save(
            @RequestParam("objectsTypeId") @NotNull @NotEmpty List<Long> objectsTypeId,
            @RequestBody @Valid
            @Parameter(description = "Шаблон паспортных данных объекта")
            List<NewSurveyObjectPassportDataTemplateDto> passportDataDto) {
        return ResponseEntity.ok().body(service.save(objectsTypeId, passportDataDto));
    }

    @Operation(summary = "Изменение данных элементов объекта")
    @PatchMapping
    public ResponseEntity<List<SurveyObjectPassportDataTemplateDto>> update(
            @RequestBody @Valid
            @Parameter(description = "Шаблон паспортных данных объекта")
            List<UpdateSurveyObjectPassportDataTemplateDto> passportDataDto) {
        return ResponseEntity.ok().body(service.update(passportDataDto));
    }
}
