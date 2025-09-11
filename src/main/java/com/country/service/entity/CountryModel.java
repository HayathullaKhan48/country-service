package com.country.service.entity;

import com.country.service.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
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
 * CountryEntity represents the country table and contains a list of states.
 */
@Entity
@Table(name = "countries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryId")
    private Long countryId;

    @Column(name = "countryCode",nullable = false, unique = true, length = 10)
    private String countryCode;

    @Column(name = "countryName",nullable = false)
    private String countryName;

    @Column(name = "officialName")
    private String officialName;

    @Column(name = "isdCode")
    private String isdCode;

    @Column(name = "continent")
    private String continent;

    @Column(name = "currencyCode")
    private String currencyCode;

    @Column(name = "currencyName")
    private String currencyName;

    @Column(name = "capital")
    private String capital;

    @Column(name = "population")
    private Long population;

    @Column(name = "areaSqKm")
    private Double areaSqKm;

    @Column(name = "officialLanguage")
    private String officialLanguage;

    @Column(name = "timeZones")
    private String timeZones;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "createdDate")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Column(name = "states")
    private List<StateModel> states = new ArrayList<>();
}
