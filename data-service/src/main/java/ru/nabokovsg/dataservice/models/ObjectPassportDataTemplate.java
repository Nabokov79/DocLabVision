package ru.nabokovsg.dataservice.models;

import lombok.*;

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ObjectsType objectsType;
    @Column(name = "sequential_subsection_number")
    private Double sequentialSubsectionNumber;
    @Column(name = "data_name")
    private String dataName;
    @Column(name = "apply_protocol")
    private Boolean applyProtocol;
}