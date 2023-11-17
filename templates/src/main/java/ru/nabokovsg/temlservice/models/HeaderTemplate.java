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
@Table(name = "header_templates")
public class HeaderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "organization")
    private String organization;
    @Column(name = "organization_license")
    private String organizationLicense;
    @Column(name = "requisites_organization")
    private String organizationRequisites;
    @Column(name = "branch")
    private String branch;
    @Column(name = "branch_requisites")
    private String branchRequisites;
    @Column(name = "license_branch")
    private String licenseBranch;
    @Column(name = "department")
    private String department;
    @Column(name = "department_requisites")
    private String departmentRequisites;
    @Column(name = "department_license")
    private String departmentLicense;
}