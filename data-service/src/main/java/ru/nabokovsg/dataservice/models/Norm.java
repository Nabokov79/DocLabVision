package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "norms")
public class Norm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private Element element;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_element_id", referencedColumnName = "id")
    private SubElement subElement;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "thickness")
    private Float thickness;
    @Column(name = "min_in_percent")
    private Float minInPercent;
    @Column(name = "min")
    private Float min;
    @Column(name = "measurement_error")
    private Float measurementError;

    @Override
    public String toString() {
        return "Norm{" +
                "id=" + id +
                ", element=" + element +
                ", subElement=" + subElement +
                ", diameter=" + diameter +
                ", thickness=" + thickness +
                ", minInPercent=" + minInPercent +
                ", min=" + min +
                ", measurementError=" + measurementError +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Norm norm = (Norm) o;
        return id == norm.id
                && Objects.equals(element, norm.element)
                && Objects.equals(subElement, norm.subElement)
                && Objects.equals(diameter, norm.diameter) &&
                Objects.equals(thickness, norm.thickness) && Objects.equals(minInPercent, norm.minInPercent)
                && Objects.equals(min, norm.min)
                && Objects.equals(measurementError, norm.measurementError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, element, subElement, diameter, thickness, minInPercent, min, measurementError);
    }
}