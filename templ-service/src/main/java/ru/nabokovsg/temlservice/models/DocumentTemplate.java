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
@Table(name = "document_templates")
public class DocumentTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "objectsType_id")
    private Long objectsTypeId;
    @Column(name = "reportingDocument_id")
    private Long reportingDocumentId;
    @OneToOne
    @JoinColumn(name = "report_template_id", referencedColumnName = "id")
    private ReportTemplate reportTemplate;
    @OneToOne
    @JoinColumn(name = "protocol_template_id", referencedColumnName = "id")
    private ProtocolTemplate protocolTemplate;
}
