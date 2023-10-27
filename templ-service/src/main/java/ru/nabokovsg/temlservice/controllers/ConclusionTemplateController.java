package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.temlservice.services.ConclusionTemplateService;

@RestController
@RequestMapping(
        value = "/template/conclusion",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон заключения",
        description="API для работы с данными шаблона рекомендации")
public class ConclusionTemplateController {

    private final ConclusionTemplateService service;
}
