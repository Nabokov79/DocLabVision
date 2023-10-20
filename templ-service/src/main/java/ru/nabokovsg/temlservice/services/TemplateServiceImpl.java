package ru.nabokovsg.temlservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.templates.NewTemplateDto;
import ru.nabokovsg.temlservice.dto.templates.DocumentTemplateDto;
import ru.nabokovsg.temlservice.enums.DocumentType;
import ru.nabokovsg.temlservice.exceptions.BadRequestException;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final ReportTemplateService reportTemplateService;
    private final ProtocolTemplateService protocolTemplateService;

    @Override
    public DocumentTemplateDto save(NewTemplateDto reportTemplateDto) {
        DocumentType documentType = DocumentType.from(reportTemplateDto.getDocumentType())
                .orElseThrow(() -> new BadRequestException(String.format("Unknown documentType: %s",
                                                                          reportTemplateDto.getDocumentType())));
        switch (documentType) {
            case REPORT -> {
                return reportTemplateService.save(reportTemplateDto);
            }
            case PROTOCOL -> {
                return protocolTemplateService.save(reportTemplateDto);
            }
            default ->
               throw new BadRequestException(
                       String.format("Document=%s type is not supported", reportTemplateDto.getDocumentType()));
        }
    }
}