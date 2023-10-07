package ru.nabokovsg.dataservice.services;

import ru.nabokovsg.dataservice.dto.dataPassportOfObject.DataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.NewDataPassportOfObjectDto;
import ru.nabokovsg.dataservice.dto.dataPassportOfObject.UpdateDataPassportOfObjectDto;

import java.util.List;

public interface DataPassportOfObjectService {

    List<DataPassportOfObjectDto> save(Long surveyObjectId, List<NewDataPassportOfObjectDto> dataDto);

    List<DataPassportOfObjectDto> update(Long surveyObjectId, List<UpdateDataPassportOfObjectDto> dataDto);
}