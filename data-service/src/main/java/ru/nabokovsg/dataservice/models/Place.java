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
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "place_name")
    private String placeName;

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placeName='" + placeName + '\'' +
                '}';
    }
}