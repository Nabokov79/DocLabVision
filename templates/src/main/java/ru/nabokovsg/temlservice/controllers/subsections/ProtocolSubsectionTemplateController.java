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
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.dto.subsection.NewProtocolSubsectionTemplate;
import ru.nabokovsg.temlservice.services.subsections.protocols.ProtocolSubsectionTemplateService;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/template/protocol/subsection",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон подраздела протокола, заключения",
        description="API для работы с данными шаблона подраздела протокола, заключения")
public class ProtocolSubsectionTemplateController {

    private final ProtocolSubsectionTemplateService service;

    @Operation(summary = "Добавление нового шаблона подраздела")
    @PostMapping
    public ResponseEntity<ProtocolTemplateDto> save(
            @RequestBody @Valid
            @Parameter(description = "Данные шаблона подразделов") NewProtocolSubsectionTemplate subsectionsDto) {
        return ResponseEntity.ok().body(service.save(subsectionsDto));
    }
}
