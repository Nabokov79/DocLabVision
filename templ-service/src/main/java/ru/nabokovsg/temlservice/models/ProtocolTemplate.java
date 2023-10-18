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
    @OneToOne
    @JoinColumn(name = "left_title_id", referencedColumnName = "id")
    private PageTitle leftTitle;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_table_subsections_templates",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsections_template_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsectionsTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_table_header_templates",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "header_template_id")})
    @ToString.Exclude
    private List<TableHeaderTemplate> tablesHeaderTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_templates_table_appendices",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<Appendices> appendices;
}