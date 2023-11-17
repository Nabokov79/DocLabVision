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
@Table(name = "subsection_data_templates")
public class SubsectionDataTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "subsection_data")
    private String subsectionData;

    @Override
    public String toString() {
        return "SubsectionDataTemplate{" +
                "id=" + id +
                ", dataType='" + dataType + '\'' +
                ", subsectionData='" + subsectionData + '\'' +
                '}';
    }
}