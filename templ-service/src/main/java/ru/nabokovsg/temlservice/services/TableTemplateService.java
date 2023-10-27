package ru.nabokovsg.temlservice.services;

import ru.nabokovsg.temlservice.dto.tableTemlate.NewTableTemplateDto;
import ru.nabokovsg.temlservice.dto.tableTemlate.TableTemplateDto;

public interface TableTemplateService {

    TableTemplateDto save(NewTableTemplateDto templateDto);
}
