package ru.nabokovsg.temlservice.controllers.passportData;

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
import ru.nabokovsg.temlservice.dto.passportData.NewProtocolPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.services.passportData.ProtocolPassportDataTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/passport/protocol",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон данных паспорта объекта обследования(для протокола/заключения)",
        description="API для работы с шаблоном данных паспорта")
public class ProtocolPassportDataTemplateController {

    private final ProtocolPassportDataTemplateService service;

    @Operation(summary = "Добавить шаблон паспортных данных")
    @PostMapping
    public ResponseEntity<ProtocolTemplateDto> save(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона паспортных данных") NewProtocolPassportDataTemplateDto passportDataDto) {
        return ResponseEntity.ok().body(service.save(passportDataDto));
    }
}
