package ru.nabokovsg.temlservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_templates")
public class ProtocolTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PageTitle leftTitle;
    private List<SubsectionTemplate> subsectionsTemplate;
    private List<TableHeaderTemplate> tablesHeaderTemplate;
    private List<Recommendation> recommendations;
    private ConclusionTemplate conclusionsTemplate;
    private List<Appendices> appendices;
}