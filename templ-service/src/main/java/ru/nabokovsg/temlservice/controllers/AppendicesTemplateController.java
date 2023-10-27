package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.temlservice.services.AppendicesTemplateService;

@RestController
@RequestMapping(
        value = "/template/appendices",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон приложения к документу",
        description="API для работы с данными шаблона приложения к документу")
public class AppendicesTemplateController {

    private final AppendicesTemplateService service;
}
