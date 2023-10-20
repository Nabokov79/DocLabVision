package ru.nabokovsg.temlservice.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.nabokovsg.temlservice.dto.client.OrganizationDto;

import java.util.Objects;

@Component
@AllArgsConstructor
public class DataServiceClient {

    private final WebClient webClient;

    public OrganizationDto getOrganization(String uri) {
        return Objects.requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(OrganizationDto.class).block()).getBody();
    }
}
