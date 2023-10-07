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
@Table(name = "size_parameters")
public class SizeParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "parameters_name")
    private String parametersName;
    @Column(name = "unit_measurement")
    private String unitMeasurement;

    @Override
    public String toString() {
        return "SizeParameters{" +
                "id=" + id +
                ", parametersName='" + parametersName + '\'' +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                '}';
    }
}