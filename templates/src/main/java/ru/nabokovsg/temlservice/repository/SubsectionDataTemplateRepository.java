package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nabokovsg.temlservice.models.SubsectionDataTemplate;

import java.util.List;
import java.util.Set;

public interface SubsectionDataTemplateRepository extends JpaRepository<SubsectionDataTemplate, Long> {

    @Query("select s from SubsectionDataTemplate s where s.subsectionData in :subsectionsData")
    Set<SubsectionDataTemplate> findAllBySubsectionData(@Param("subsectionsData") List<String> subsectionsData);
}
