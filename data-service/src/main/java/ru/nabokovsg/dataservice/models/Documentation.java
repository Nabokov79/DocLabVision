package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documentations")
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "view", nullable = false)
    private String view;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "methodological_document")
    private Boolean methodologicalDocument;
    @Column(name = "regulatory_document")
    private Boolean regulatoryDocument;
}