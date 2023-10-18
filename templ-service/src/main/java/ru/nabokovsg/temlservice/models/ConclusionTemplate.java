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
@Table(name =  "conclusion_templates")
public class ConclusionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "approaching_defect")
    private String approachingDefect;
    @Column(name = "equal_defect")
    private String equalDefect;
    @Column(name = "is_defect")
    private String isDefect;
    @Column(name = "is_not_defect")
    private String isNotDefect;
    @Column(name = "no_norm_defect")
    private String noNormDefect;
}