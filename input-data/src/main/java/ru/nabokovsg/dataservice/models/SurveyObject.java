package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_objects")
public class SurveyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "objects_type_id", referencedColumnName = "id")
    private ObjectsType objectsType;
    @Column(name = "stationary_number")
    private Integer stationaryNumber;
    @OneToMany(mappedBy = "objectSurvey", fetch = FetchType.LAZY)
    private List<SurveyObjectElement> elements;
    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}