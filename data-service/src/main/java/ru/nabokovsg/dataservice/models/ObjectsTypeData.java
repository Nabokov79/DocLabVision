package ru.nabokovsg.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "objects_type_data")
public class ObjectsTypeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objects_type_id", referencedColumnName = "id")
    private ObjectsType objectsType;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_elements",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "element_id")})
    private List<Element> elements;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_norms",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "norm_id")})
    private List<Norm> norms;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_defects",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "defect_id")})
    private List<Defect> defects;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_documentations",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "documentation_id")})
    private List<Documentation> documentations;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_object_passport_data_templates",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "template_id")})
    private List<ObjectPassportDataTemplate> templates;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "objects_type_data_repair_methods",
            joinColumns = {@JoinColumn(name = "objects_type_data_id")},
            inverseJoinColumns = {@JoinColumn(name = "repair_method_id")})
    private List<RepairMethod> repairMethods;

    @Override
    public String toString() {
        return "ObjectsTypeData{" +
                "id=" + id +
                ", objectsType=" + objectsType +
                ", elements=" + elements +
                ", norms=" + norms +
                ", defects=" + defects +
                ", documentations=" + documentations +
                ", templates=" + templates +
                ", repairMethods=" + repairMethods +
                '}';
    }
}