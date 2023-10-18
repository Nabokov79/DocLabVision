package ru.nabokovsg.temlservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_protocol_templates")
public class ReportProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_protocol_number")
    private Integer sequentialProtocolNumber;
    @Column(name = "protocol_name")
    private String protocolName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_templates_subsection_templates",
            joinColumns = {@JoinColumn(name = "report_protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsections_template_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsectionsTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "report_protocol_templates_table_header_templates",
            joinColumns = {@JoinColumn(name = "report_protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_header_template_id")})
    @ToString.Exclude
    private List<TableHeaderTemplate> tablesHeaderTemplate;
    @OneToOne
    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusionsTemplate;
}
