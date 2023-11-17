package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "branch")
    private String branch;
    @Column(name = "short_name_branch")
    private String shortNameBranch;
    @OneToMany(mappedBy = "branch",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Department> departments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisites_id", referencedColumnName = "id")
    private Requisites requisites;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id",  nullable = false)
    private Organization organization;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "branches_licenses",
            joinColumns = {@JoinColumn(name = "branch_id")},
            inverseJoinColumns = {@JoinColumn(name = "license_id")})
    @ToString.Exclude
    private List<Licenses> licenses;
}