package ru.nabokovsg.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_object_surveys")
public class SurveyObjectSurveys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date")
    private String date;
    @Column(name = "survey_description", nullable = false)
    private String surveyDescription;
    @Column(name = "survey_number")
    private String surveyNumber;
    @Column(name = "organization")
    private String organization;
    @Column(name = "date_next_survey")
    private String dateNextSurvey;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id",  nullable = false)
    private SurveyObjectPassport passport;
}