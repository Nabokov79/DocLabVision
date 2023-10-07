package ru.nabokovsg.dataservice.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.models.DataPassportOfObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DataPassportOfObjectMapper {

    DataPassportOfObject mapToNewDataPassportOfObject(NewDataPassportOfObjectDto dataPassportDto);

    DataPassportOfObject mapToUpdateDataPassportOfObject(UpdateDataPassportOfObjectDto dataPassportDto);

    List<DataPassportOfObjectDto> mapToDataPassportOfObjectDto(List<DataPassportOfObject> dataPassport);
}