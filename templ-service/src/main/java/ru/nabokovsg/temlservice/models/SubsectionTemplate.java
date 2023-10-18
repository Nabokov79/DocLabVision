package ru.nabokovsg.temlservice.models;

import lombok.*;

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
    @Column(name = "data_type_subsection")
    @Enumerated(EnumType.STRING)
    private DataTypeSubsection dataTypeSubsection;
    @Column(name = "sequential_subsection_number")
    private double sequentialSubsectionNumber;
    @Column(name = "subsection_name")
    private String subsectionName;
    @Column(name = "subsection_text")
    private String subsectionText;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_templates_recommendations",
            joinColumns = {@JoinColumn(name = "subsection_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<Recommendation> recommendations;
    @OneToOne
    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusionsTemplate;
}