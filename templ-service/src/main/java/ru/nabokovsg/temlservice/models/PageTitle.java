package ru.nabokovsg.temlservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.enums.DocumentType;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "page_titles")
public class PageTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    @Column(name = "organization_id")
    private Long organizationId;
    @Column(name = "organization_full_name")
    private boolean organizationFullName;
    @Column(name = "organization_license")
    private boolean organizationLicense;
    @Column(name = "requisites_organization")
    private boolean organizationRequisites;
    @Column(name = "branch_id")
    private Long branchId;
    @Column(name = "branch_full_name")
    private boolean branchFullName;
    @Column(name = "branch_requisites")
    private boolean branchRequisites;
    @Column(name = "license_branch")
    private boolean licenseBranch;
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department_full_name")
    private boolean departmentFullName;
    @Column(name = "department_requisites")
    private boolean departmentRequisites;
    @Column(name = "department_license")
    private boolean departmentLicense;
}