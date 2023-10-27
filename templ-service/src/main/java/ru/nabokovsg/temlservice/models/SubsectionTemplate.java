package ru.nabokovsg.temlservice.models;

import lombok.*;
import ru.nabokovsg.temlservice.enums.SubsectionDataType;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsection_templates")
public class SubsectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "subsection_data_type")
    @Enumerated(EnumType.STRING)
    private SubsectionDataType subsectionDataType;
    @Column(name = "sequential_subsection_number")
    private double sequentialSubsectionNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "subsection_text")
    private String subsectionText;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_templates_subsection_data_templates",
            joinColumns = {@JoinColumn(name = "subsection_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "subsection_data_template_id")})
    @ToString.Exclude
    private List<SubsectionDataTemplate> subsectionData;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_templates_table_templates",
            joinColumns = {@JoinColumn(name = "subsection_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "table_template_id")})
    @ToString.Exclude
    private List<TableTemplate> tablesTemplate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_templates_recommendations",
            joinColumns = {@JoinColumn(name = "subsection_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;
    @OneToOne
    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusionsTemplate;
}