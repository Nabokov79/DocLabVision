package ru.nabokovsg.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "organization")
    private String organization;
    @Column(name = "short_name_organization")
    private String shortNameOrganization;
    @OneToMany(mappedBy = "organization",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Branch> branches;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;
}