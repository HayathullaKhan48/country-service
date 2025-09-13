package com.country.service.entity;

import com.country.service.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * StateEntity holds state-level information and belongs to a country.
 */
@Entity
@Table(name = "states")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stateId")
    private Long stateId;

    @Column(name = "stateCode", nullable = false, length = 10)
    private String stateCode;

    @Column(name = "stateName", nullable = false)
    private String stateName;

    @Column(name = "stateType")
    private String stateType;

    @Column(name = "stateCapital")
    private String stateCapital;

    @Column(name = "statePopulation")
    private Long statePopulation;

    @Column(name = "stateAreaSqKm")
    private Double stateAreaSqKm;

    @Column(name = "officialLanguages")
    private String officialLanguages;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "gstCode")
    private String gstCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "createdDate")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryModel country;

    @OneToMany(mappedBy = "state",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityModel> cities = new ArrayList<>();
}
