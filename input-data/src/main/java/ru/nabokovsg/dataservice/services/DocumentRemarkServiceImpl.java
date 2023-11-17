package ru.nabokovsg.dataservice.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.dataservice.dto.documentRemark.DocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.NewDocumentRemarkDto;
import ru.nabokovsg.dataservice.dto.documentRemark.UpdateDocumentRemarkDto;
import ru.nabokovsg.dataservice.exceptions.NotFoundException;
import ru.nabokovsg.dataservice.mappers.DocumentRemarkMapper;
import ru.nabokovsg.dataservice.models.DocumentRemark;
import ru.nabokovsg.dataservice.models.Employee;
import ru.nabokovsg.dataservice.models.QDocumentRemark;
import ru.nabokovsg.dataservice.repository.DocumentRemarkRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentRemarkServiceImpl implements DocumentRemarkService {

    private final DocumentRemarkRepository repository;
    private final DocumentRemarkMapper mapper;
    private final ReportingDocumentDataService documentDataService;
    private final EmployeeService employeeService;
    private final EntityManager entityManager;

    @Override
    public DocumentRemarkDto save(NewDocumentRemarkDto remarkDto) {
        DocumentRemark remark = set(mapper.mapToNewDocumentRemark(remarkDto), remarkDto.getEmployeeId());
        remark.setReportingDocumentData(documentDataService.getById(remarkDto.getReportingDocumentDataId()));
        return mapper.mapToDocumentRemarkDto(repository.save(remark));
    }

    @Override
    public DocumentRemarkDto update(UpdateDocumentRemarkDto remarkDto) {
        if (repository.existsById(remarkDto.getId())) {
            DocumentRemark remark = set(mapper.mapToUpdateDocumentRemark(remarkDto), remarkDto.getEmployeeId());
            remark.setReportingDocumentData(documentDataService.getById(remarkDto.getReportingDocumentDataId()));
            return mapper.mapToDocumentRemarkDto(repository.save(remark));
        }
       throw new NotFoundException(String.format("Remark with id=%s not found for update", remarkDto.getId()));
    }

    @Override
    public List<DocumentRemarkDto> getAll(Long employeeId, Long employeeDocumentId, Long employeeDrawingId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (employeeId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.employeeDocument.id.eq(employeeId));
        }
        if (employeeDocumentId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.reportingDocumentData.employeeDocument.id.eq(employeeDocumentId));
        }
        if (employeeDrawingId != null) {
            booleanBuilder.and(
                    QDocumentRemark.documentRemark.reportingDocumentData.employeeDrawing.id.eq(employeeDrawingId));
        }
        QDocumentRemark remark = QDocumentRemark.documentRemark;
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        return mapper.mapToAllDocumentRemarkDto(qf.from(remark)
                .select(remark)
                .where(booleanBuilder)
                .fetch());
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Remark with id=%s not found for delete", id));
    }

    private DocumentRemark set(DocumentRemark remark, Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        if (remark.getDocumentRemarkText() != null) {
            remark.setEmployeeDocument(employee);
        }
        if (remark.getDrawingRemarkText() != null) {
            remark.setEmployeeDrawing(employee);
        }
        return remark;
    }
}