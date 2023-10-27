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
@Table(name = "page_title_templates")
public class PageTitleTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "page_header_template_id", referencedColumnName = "id")
    private PageHeaderTemplate pageHeaderTemplate;
    @Column(name = "object_string")
    private String objectString;
    @Column(name = "installation_location_string")
    private String installationLocationString;
    @Column(name = "address_string")
    private String addressString;
    @Column(name = "employee")
    private String employee;
    @Column(name = "city")
    private String city;
    @Column(name = "year")
    private String year;
}
