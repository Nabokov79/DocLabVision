package ru.nabokovsg.temlservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "objects_type_id")
    private Long objectsTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "report_name")
    private String reportName;
    @Column(name = "report_title")
    private String reportTitle;
    @OneToOne
    @JoinColumn(name = "page_title_id", referencedColumnName = "id")
    private PageTitleTemplate pageTitle;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_templates_section_templates",
            joinColumns = {@JoinColumn(name = "report_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_template_id")})
    @ToString.Exclude
    private List<SectionTemplate> sectionTemplates;
}