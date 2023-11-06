package ru.nabokovsg.temlservice.services.converters;

import ru.nabokovsg.temlservice.dto.division.Division;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

public interface ConvertDivisionDataService {

    SubsectionDataTemplate convert(Division division);
}