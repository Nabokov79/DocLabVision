package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.DocumentationMapper;
import ru.nabokovsg.dataservice.models.Documentation;
import ru.nabokovsg.dataservice.models.ObjectsType;
import ru.nabokovsg.dataservice.repository.DocumentationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository repository;
    private final DocumentationMapper mapper;
    private final ObjectsTypeService objectsTypeService;

    @Override
    public List<DocumentationDto> save(List<Long> objectsTypeId
                                                    , List<NewDocumentationDto> documentationsDto) {
        List<Documentation> documentations = mapper.mapToNewDocumentations(documentationsDto);
        List<Documentation> documentationsDb = new ArrayList<>();
        List<ObjectsType> objectsTypes = objectsTypeService.getAll(objectsTypeId);
        for (ObjectsType type : objectsTypes) {
            documentationsDb.addAll(documentations.stream().peek(d -> d.setObjectsType(type)).toList());
        }
        return mapper.mapToDocumentationDto(repository.saveAll(documentationsDb));
    }

    @Override
    public List<DocumentationDto> update(List<UpdateDocumentationDto> documentationsDto) {
        validateIds(documentationsDto.stream().map(UpdateDocumentationDto::getId).toList());
        return mapper.mapToDocumentationDto(repository.saveAll(mapper.mapToUpdateDocumentation(documentationsDto)));
    }

    private void validateIds(List<Long> ids) {
        Map<Long, Documentation> documentations = repository.findAllById((ids))
                .stream().collect(Collectors.toMap(Documentation::getId, subheading -> subheading));
        if (documentations.size() != ids.size() || documentations.isEmpty()) {
            List<Long> idsDb = new ArrayList<>(documentations.keySet());
            ids = ids.stream().filter(e -> !idsDb.contains(e)).collect(Collectors.toList());
            throw new NotFoundException(String.format("documentations with ids= %s not found", ids));
        }
    }
}