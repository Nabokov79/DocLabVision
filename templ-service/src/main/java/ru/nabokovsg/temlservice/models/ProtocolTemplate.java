package ru.nabokovsg.temlservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "objects_type_id")
    private Long objectsTypeId;
    @Column(name = "reporting_document_id")
    private Long reportingDocumentId;
    @Column(name = "sequential_protocol_number")
    private Integer sequentialProtocolNumber;
    @Column(name = "protocol_name")
    private String protocolName;
    @Column(name = "protocol_title")
    private String protocolTitle;
    @OneToOne
    @JoinColumn(name = "page_header_id", referencedColumnName = "id")
    private PageTitleTemplate pageHeader;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_subsection_templates",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsections_template_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsectionsTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_table_templates",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_template_id")})
    @ToString.Exclude
    private List<TableTemplate> tablesHeaderTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_appendices",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<AppendicesTemplates> appendices;
}