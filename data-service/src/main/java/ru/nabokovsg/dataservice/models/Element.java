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
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ObjectsType objectsType;
    @Column(name = "element_name")
    private String elementName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "elements_sub_elements",
            joinColumns = {@JoinColumn(name = "element_id")},
            inverseJoinColumns = {@JoinColumn(name = "sub_element_id")})
    @ToString.Exclude
    private List<SubElement> subElements;

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", objectsType=" + objectsType +
                ", elementName='" + elementName + '\'' +
                ", subElements=" + subElements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id && Objects.equals(objectsType, element.objectsType)
                && Objects.equals(elementName, element.elementName)
                && Objects.equals(subElements, element.subElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectsType, elementName, subElements);
    }
}