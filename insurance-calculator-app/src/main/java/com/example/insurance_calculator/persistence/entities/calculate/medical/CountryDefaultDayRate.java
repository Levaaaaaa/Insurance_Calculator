package com.example.insurance_calculator.persistence.entities.calculate.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "country_default_day_rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDefaultDayRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_ic", nullable = false)
    private String countryIc;

    @Column(name = "default_day_rate", precision = 10, scale = 2, nullable = false)
    private BigDecimal defaultDayRate;

}
