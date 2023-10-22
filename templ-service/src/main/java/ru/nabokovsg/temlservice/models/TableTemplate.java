package ru.nabokovsg.temlservice.models;

import lombok.*;
import ru.nabokovsg.temlservice.enums.TableDataType;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_templates")
public class TableTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "table_data_type")
    @Enumerated(EnumType.STRING)
    private TableDataType tableDataType;
    @Column(name = "sequential_table_number")
    private Integer sequentialTableNumber;
    @Column(name = "table_name")
    private String tableName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "table_templates_columns_headers_templates",
            joinColumns = {@JoinColumn(name = "table_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "columns_header_id")})
    @ToString.Exclude
    private List<ColumnHeaderTemplate> columnsHeaders;
}