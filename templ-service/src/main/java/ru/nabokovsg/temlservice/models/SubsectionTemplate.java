package ru.nabokovsg.temlservice.models;

import lombok.*;
import ru.nabokovsg.temlservice.models.enums.DataType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    private DataType dataType;
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
    private List<TableTemplate> tables;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subsection_templates_recommendations",
            joinColumns = {@JoinColumn(name = "subsection_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "recommendation_id")})
    @ToString.Exclude
    private List<RecommendationTemplate> recommendations;
    @OneToOne
    @JoinColumn(name = "conclusions_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubsectionTemplate that = (SubsectionTemplate) o;
        return id == that.id && Double.compare(that.sequentialSubsectionNumber, sequentialSubsectionNumber) == 0
                             && dataType == that.dataType
                             && Objects.equals(subsectionName, that.subsectionName)
                             && Objects.equals(subsectionText, that.subsectionText)
                             && Objects.equals(subsectionData, that.subsectionData)
                             && Objects.equals(tables, that.tables)
                             && Objects.equals(recommendations, that.recommendations)
                             && Objects.equals(conclusions, that.conclusions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataType, sequentialSubsectionNumber, subsectionName
                              , subsectionText, subsectionData, tables, recommendations, conclusions);
    }
}