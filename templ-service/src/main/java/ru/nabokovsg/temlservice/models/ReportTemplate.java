package ru.nabokovsg.temlservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports_templates")
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "page_title_id", referencedColumnName = "id")
    private PageTitle pageTitle;
    @Column(name = "document_name")
    private String documentName;
    @Column(name = "document_title")
    private String documentTitle;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reports_templates_table_subsection_templates",
            joinColumns = {@JoinColumn(name = "reports_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_template_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsectionTemplates;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_templates_table_appendices",
            joinColumns = {@JoinColumn(name = "report_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<Appendices> appendices;
}