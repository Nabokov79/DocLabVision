package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defects")
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ObjectsType objectsType;
    @Column(name = "defect_name")
    private String defectName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "defects_size_parameters",
            joinColumns = {@JoinColumn(name = "defect_id")},
            inverseJoinColumns = {@JoinColumn(name = "size_parameter_id")})
    @ToString.Exclude
    private List<SizeParameters> parameters;

    @Override
    public String toString() {
        return "Defect{" +
                "id=" + id +
                ", defectName='" + defectName + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}