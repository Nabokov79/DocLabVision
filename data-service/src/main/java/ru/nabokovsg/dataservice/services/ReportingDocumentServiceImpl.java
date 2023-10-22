package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.dataservice.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.dataservice.exceptions.BadRequestException;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.ReportingDocumentMapper;
import ru.nabokovsg.dataservice.models.DocumentType;
import ru.nabokovsg.dataservice.models.ReportingDocument;
import ru.nabokovsg.dataservice.repository.ReportingDocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingDocumentServiceImpl implements ReportingDocumentService {

    private final ReportingDocumentRepository repository;
    private final ReportingDocumentMapper mapper;

    @Override
    public List<ReportingDocumentDto> save(List<NewReportingDocumentDto> reportingDocumentDto) {
        return repository.saveAll(reportingDocumentDto.stream()
                        .map(d -> {
                                    ReportingDocument document = mapper.mapFromNewReportingDocument(d);
                                    document.setDocumentType(
                                    DocumentType.from(d.getDocumentType()).orElseThrow(
                                            () -> new BadRequestException("Unknown documentType: " + d.getDocumentType()
                                            )
                                         )
                                    );
                                    return document;
                                })
                        .toList()
        ).stream().map(mapper::mapToReportingDocumentDto).toList();
    }

    @Override
    public List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> reportingDocumentDto) {
        validateIds(reportingDocumentDto.stream().map(UpdateReportingDocumentDto::getId).toList());
        return repository.saveAll(reportingDocumentDto.stream()
                        .map(d -> {
                            ReportingDocument document = mapper.mapFromUpdateReportingDocument(d);
                            document.setDocumentType(
                                    DocumentType.from(d.getDocumentType()).orElseThrow(
                                            () -> new BadRequestException("Unknown documentType: " + d.getDocumentType()
                                            )
                                    )
                            );
                            return document;
                        })
                        .toList()
        ).stream().map(mapper::mapToReportingDocumentDto).toList();
    }

    @Override
    public ReportingDocumentDto get(Long id) {
        return mapper.mapToReportingDocumentDto(
                repository.findById(id)
                          .orElseThrow(() -> new RuntimeException(String.format("ReportingDocument with id=%s", id))));
    }

    @Override
    public List<ReportingDocumentDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToReportingDocumentDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("reporting document with id=%s not found for delete", id));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, ReportingDocument> documents = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(ReportingDocument::getId, m -> m));
        if (documents.size() != ids.size() || documents.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(documents.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("reporting document with ids= %s not found", ids));
        }
    }
}