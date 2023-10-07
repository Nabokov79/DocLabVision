package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.documentation.DocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.NewDocumentationDto;
import ru.nabokovsg.dataservice.dto.documentation.UpdateDocumentationDto;
import ru.nabokovsg.dataservice.dto.objectsTypeData.ObjectsTypeDocumentationDataDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.DocumentationMapper;
import ru.nabokovsg.dataservice.mappers.ObjectsTypeDataMapper;
import ru.nabokovsg.dataservice.models.BuilderType;
import ru.nabokovsg.dataservice.models.DataBuilder;
import ru.nabokovsg.dataservice.models.Documentation;
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
    private final ObjectsTypeDataService dataService;
    private final ObjectsTypeDataMapper dataMapper;

    @Override
    public List<ObjectsTypeDocumentationDataDto> save(List<Long> objectsTypeId
                                                    , List<NewDocumentationDto> documentationsDto) {
        Map<String, Documentation> documentations = repository.findAllByTitle(documentationsDto
                                                                 .stream().map(NewDocumentationDto::getTitle).toList())
                                                            .stream()
                                                            .collect(Collectors.toMap(Documentation::getTitle, d -> d));
        List<Documentation> documentationsDb = mapper.mapToNewDocumentations(documentationsDto);
        if (documentations.isEmpty()) {
            documentationsDb = repository.saveAll(documentationsDb);
        } else {
            documentationsDb =  repository.saveAll(documentationsDb.stream()
                                                                 .filter(d -> !documentations.containsKey(d.getTitle()))
                                                                 .toList());
            documentationsDb.addAll(documentations.values());

        }
        return dataMapper.mapToObjectsTypeDocumentationDataDto(
                dataService.save(new DataBuilder.Data().type(BuilderType.DOCUMENTATION)
                        .ids(objectsTypeId)
                        .documentations(documentationsDb)
                        .build()));
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