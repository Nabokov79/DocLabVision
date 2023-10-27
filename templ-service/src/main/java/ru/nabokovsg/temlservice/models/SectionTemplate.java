package ru.nabokovsg.temlservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section_templates")
public class SectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_section_number")
    private Integer sequentialSectionNumber;
    @Column(name = "section_name")
    private String sectionName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_templates_subsection_templates",
            joinColumns = {@JoinColumn(name = "section_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsections_template_id")})
    @ToString.Exclude
    private List<SubsectionTemplate> subsectionsTemplates;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_templates_report_protocol_templates",
            joinColumns = {@JoinColumn(name = "section_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "report_protocol_template_id")})
    @ToString.Exclude
    private List<ProtocolTemplate> protocolTemplates;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_templates_recommendations",
            joinColumns = {@JoinColumn(name = "section_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "section_templates_appendices",
            joinColumns = {@JoinColumn(name = "section_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "appendices_id")})
    @ToString.Exclude
    private List<AppendicesTemplates> appendices;
}
