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
@Table(name = "object_passport_data_templates")
public class ObjectPassportDataTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_subsection_number")
    private String sequentialSubsectionNumber;
    @Column(name = "section_number")
    private Integer sectionNumber;
    @Column(name = "subsection_number")
    private Integer subsectionNumber;
    @Column(name = "data_name")
    private String dataName;
    @Column(name = "apply_protocol")
    private Boolean applyProtocol;
}