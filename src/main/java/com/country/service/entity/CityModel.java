package com.country.service.entity;

import com.country.service.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * StateEntity holds state-level information and belongs to a country.
 */
@Entity
@Table(name = "cities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private Long cityId;

    @Column(name = "cityCode",nullable = false, length = 10)
    private String cityCode;

    @Column(name = "cityName",nullable = false)
    private String cityName;

    @Column(name = "cityType")
    private String cityType;

    @Column(name = "cityPopulation")
    private Long cityPopulation;

    @Column(name = "cityAreaSqKm")
    private Double cityAreaSqKm;

    @Column(name = "pinCode")
    private Integer pinCode;

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
    @JoinColumn(name = "state_id")
    private StateModel state;
}
