package ru.nabokovsg.temlservice.services;

import org.springframework.stereotype.Service;
import ru.nabokovsg.temlservice.dto.client.AddressDto;
import ru.nabokovsg.temlservice.dto.client.LicenseDto;
import ru.nabokovsg.temlservice.dto.client.RequisitesDto;

@Service
public class StringBuilderServiceImpl implements StringBuilderService {

    @Override
    public String licenseToString(LicenseDto license) {
        return String.join(" ", license.getDocumentType(),
                                                  "№",
                                                  license.getLicenseNumber(),
                                                  "от",
                                                  license.getStartData().toString());
    }

    @Override
    public String requisitesToString(RequisitesDto requisites) {
        return String.join("//"
        , String.join(" ", String.valueOf(requisites.getIndex()), addressToString(requisites.getAddress()))
        , String.join(" ","тел./факс", requisites.getPhone(),"/", requisites.getFax())
        , String.join(" ", "E-mail:", requisites.getEmail()));
    }

    @Override
    public String addressToString(AddressDto address) {
        String string = String.join(", ", address.getCity()
                                                , address.getStreet()
                                                , String.valueOf(address.getHouseNumber()));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", "корп.", String.valueOf(address.getBuildingNumber()));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", "лит.", address.getLetter());
        }
        return string;
    }
}