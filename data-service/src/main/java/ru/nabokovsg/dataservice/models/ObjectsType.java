package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "object_types")
public class ObjectsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object_name")
    private String objectName;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "orientation")
    private String orientation;

    @Override
    public String toString() {
        return "ObjectsType{" +
                "id=" + id +
                ", objectName='" + objectName + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectsType that = (ObjectsType) o;
        return id == that.id && Objects.equals(objectName, that.objectName)
                             && Objects.equals(volume, that.volume)
                             && Objects.equals(orientation, that.orientation);
                }

    @Override
    public int hashCode() {
        return Objects.hash(id, objectName, volume, orientation);
    }
}