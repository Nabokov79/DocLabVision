package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_elements")
public class SubElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sub_element_name")
    private String subElementName;
    @Column(name = "ordinal_number_sub_element")
    private Integer ordinalNumberSubElement;

    @Override
    public String toString() {
        return "SubElement{" +
                "id=" + id +
                ", subElementName='" + subElementName + '\'' +
                ", ordinalNumberSubElement=" + ordinalNumberSubElement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubElement that = (SubElement) o;
        return id == that.id && Objects.equals(subElementName, that.subElementName) && Objects.equals(ordinalNumberSubElement, that.ordinalNumberSubElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subElementName, ordinalNumberSubElement);
    }
}