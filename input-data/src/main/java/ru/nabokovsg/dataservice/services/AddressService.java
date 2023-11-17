package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.address.NewAddressDto;
import ru.nabokovsg.dataservice.dto.address.UpdateAddressDto;
import ru.nabokovsg.dataservice.models.Address;

import java.util.List;

public interface AddressService {

    AddressDto save(NewAddressDto addressDto);

    AddressDto update(UpdateAddressDto addressDto);

    Address get(Long id);

    List<AddressDto> getAll(String city, int from, int size);

    String delete(Long id);
}