package ru.nabokovsg.temlservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.temlservice.enums.ColumnDataType;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "columns_headers_templates")
public class ColumnHeaderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_cell_number")
    private Integer sequentialCellNumber;
    @Column(name = "cell_name")
    private String cellName;
    @Column(name = "column_data_type")
    @Enumerated(EnumType.STRING)
    private ColumnDataType columnDataType;
}