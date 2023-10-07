package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sub_elements_places",
            joinColumns = {@JoinColumn(name = "sub_element_id")},
            inverseJoinColumns = {@JoinColumn(name = "place_id")})
    @ToString.Exclude
    private List<Place> places;

    @Override
    public String toString() {
        return "SubElement{" +
                "id=" + id +
                ", subElementName='" + subElementName + '\'' +
                ", places=" + places +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubElement that = (SubElement) o;
        return id == that.id && Objects.equals(subElementName, that.subElementName)
                             && Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subElementName, places);
    }
}