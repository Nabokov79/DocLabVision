package ru.nabokovsg.temlservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.OrganizationDto;

@Service
@RequiredArgsConstructor
public class TemplateClient {

    private final DataServiceClient client;

    public OrganizationDto getOrganization(Long id) {
        return client.getOrganization("/data/organizations/" + id);
    }
}
