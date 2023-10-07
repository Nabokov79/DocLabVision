package ru.nabokovsg.dataservice.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objects_types")
public class ObjectsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "object")
    private String object;
    @Column(name = "orientation")
    private String orientation;
    @Column(name = "volume")
    private Integer volume;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "objects_types_plot_of_objects",
            joinColumns = {@JoinColumn(name = "object_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "plot_of_object_id")})
    @ToString.Exclude
    private List<PlotOfObject> plotOfObjects;

    @Override
    public String toString() {
        return "ObjectsType{" +
                "id=" + id +
                ", object='" + object + '\'' +
                ", orientation='" + orientation + '\'' +
                ", volume=" + volume +
                ", plotOfObjects=" + plotOfObjects +
                '}';
    }
}