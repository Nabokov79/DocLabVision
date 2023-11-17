package ru.nabokovsg.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.dataservice.models.Application;
import ru.nabokovsg.dataservice.models.ReportingDocumentData;

import java.util.List;

public interface ReportingDocumentDataRepository extends JpaRepository<ReportingDocumentData, Long>
                                                       , QuerydslPredicateExecutor<ReportingDocumentData> {

    @Query("select r from ReportingDocumentData r where r.application in :applications")
    List<ReportingDocumentData> findAllByApplication(@Param("applications") List<Application> applications);
}