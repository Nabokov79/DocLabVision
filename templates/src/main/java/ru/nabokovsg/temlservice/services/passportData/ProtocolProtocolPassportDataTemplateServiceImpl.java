package ru.nabokovsg.temlservice.services.passportData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.passportData.NewProtocolPassportDataTemplateDto;
import ru.nabokovsg.temlservice.dto.protocol.ProtocolTemplateDto;
import ru.nabokovsg.temlservice.mappers.ProtocolTemplateMapper;
import ru.nabokovsg.temlservice.models.ProtocolTemplate;
import ru.nabokovsg.temlservice.models.SubsectionTemplate;
import ru.nabokovsg.temlservice.services.PassportDataTemplateService;
import ru.nabokovsg.temlservice.services.ProtocolTemplateService;
import ru.nabokovsg.temlservice.services.subsections.SubsectionTemplateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProtocolProtocolPassportDataTemplateServiceImpl implements ProtocolPassportDataTemplateService {

    private final ProtocolTemplateService protocolService;
    private final ProtocolTemplateMapper protocolMapper;
    private final SubsectionTemplateService subsectionService;
    private final PassportDataTemplateService passportDataService;

    @Override
    public ProtocolTemplateDto save(NewProtocolPassportDataTemplateDto passportDataDto) {
        ProtocolTemplate protocol = protocolService.getProtocolById(passportDataDto.getProtocolId());
        if (protocol != null) {
            SubsectionTemplate subsection = protocol.getSubsections().stream().collect(Collectors.toMap(SubsectionTemplate::getId, s -> s)).get(passportDataDto.getSubsectionId());
            if (subsection != null) {
                subsection.getPassportData().addAll(passportDataService.save(passportDataDto.getObjectsTypeId(), subsection.getPassportData()));
                protocol.getSubsections().addAll(subsectionService.saveAll(List.of(subsection)));
            }
        }
        return protocolMapper.mapToProtocolTemplateDto(protocol);
    }
}
