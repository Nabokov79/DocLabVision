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
@Table(name = "appendices")
public class Appendices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sequential_appendices_number")
    private Integer sequentialAppendicesNumber;
    @Column(name = "appendices_name")
    private String appendicesName;
}
