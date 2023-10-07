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
@Table(name = "document_remarks")
public class DocumentRemark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "document_remark_text")
    private String documentRemarkText;
    @Column(name = "drawing_remark_text")
    private String drawingRemarkText;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_document_id", referencedColumnName = "id")
    private Employee employeeDocument;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_drawing_id", referencedColumnName = "id")
    private Employee employeeDrawing;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_document_data_id",  nullable = false)
    private ReportingDocumentData reportingDocumentData;
    @Column(name = "fixed")
    private Boolean fixed;
}