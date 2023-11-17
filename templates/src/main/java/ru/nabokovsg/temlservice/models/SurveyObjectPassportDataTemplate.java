package ru.nabokovsg.temlservice.models;

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
@Table(name = "survey_object_passport_data_templates")
public class SurveyObjectPassportDataTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_type_id")
    private Long objectTypeId;
    @Column(name = "sequential_number")
    private Double sequentialNumber;
    @Column(name = "data_name")
    private String dataName;
    @Column(name = "apply_report")
    private Boolean applyReport;
    @Column(name = "apply_protocol")
    private Boolean applyProtocol;
}