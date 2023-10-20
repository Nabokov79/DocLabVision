package ru.nabokovsg.temlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.temlservice.models.PageTitle;

public interface PageTitleRepository extends JpaRepository<PageTitle, Long> {
}