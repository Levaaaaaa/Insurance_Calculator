package com.example.insurance_calculator.persistence.entities.calculate.cancellation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "travel_cancellation_country_safety_rating_coefficient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TCCountrySafetyRatingCoefficientDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country", nullable = false, unique = true)
    private String country;

    @Column(name = "rating", nullable = false)
    private Integer rating;
}
