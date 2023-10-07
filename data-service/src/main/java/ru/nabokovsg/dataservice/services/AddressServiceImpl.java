package ru.nabokovsg.dataservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.address.NewAddressDto;
import ru.nabokovsg.dataservice.dto.address.UpdateAddressDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.AddressMapper;
import ru.nabokovsg.dataservice.models.Address;
import ru.nabokovsg.dataservice.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    public AddressDto save(NewAddressDto addressDto) {
        return mapper.mapToAddressDto(repository.save(mapper.mapToNewAddress(addressDto)));
    }

    @Override
    public AddressDto update(UpdateAddressDto addressDto) {
        if (!repository.existsById(addressDto.getId())) {
            throw new NotFoundException(String.format("address with id=%s not found for update.", addressDto.getId()));
        }
        return mapper.mapToAddressDto(repository.save(mapper.mapToUpdateAddress(addressDto)));
    }

    @Override
    public Address get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Address with id=%s not found", id)));
    }

    @Override
    public List<AddressDto> getAll(String city, int from, int size) {
        Pageable pageable = PageRequest.of(from / size,size, Sort.by("city"));
        if(city != null) {
            return mapper.mapToAddressDto(new ArrayList<>(repository.findByCity(city)));
        }
        return mapper.mapToAddressDto(repository.findAll(pageable).getContent());
    }

    @Override
    public String delete(Long id) {
        Address address = repository.findById(id).orElseThrow(() -> new NotFoundException(
                        String.format("address with id=%s not found for delete.", id)));
        repository.deleteById(id);
        return String.join(" ", address.getCity(),
                                                 address.getStreet(),
                                                 address.getHouseNumber().toString());
    }
}