package ru.nabokovsg.temlservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nabokovsg.temlservice.services.RecommendationTemplateService;

@RestController
@RequestMapping(
        value = "/template/recommendation",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Шаблон рекомендации",
        description="API для работы с данными шаблона рекомендации")
public class RecommendationTemplateController {

    private final RecommendationTemplateService service;
}
