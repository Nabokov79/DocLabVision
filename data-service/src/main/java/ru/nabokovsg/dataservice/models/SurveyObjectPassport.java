package ru.nabokovsg.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "object_passports")
public class SurveyObjectPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_object_id", referencedColumnName = "id")
    private SurveyObject object;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<SurveyObjectSurveys> surveys;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<SurveyObjectRepair> repairs;
    @OneToMany(mappedBy = "passport", fetch = FetchType.LAZY)
    private Set<DataPassportOfObject> dataPassport;
}