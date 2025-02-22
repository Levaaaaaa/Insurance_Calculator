package com.example.insurance_calculator.core.entities.agreement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "person_risks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonsRiskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "risk_ic", nullable = false)
    private String riskIc;

    @Column(name = "premium", nullable = false, precision = 10, scale = 2)
    private BigDecimal premium;

    @ManyToOne
    @JoinColumn(name = "person", nullable = false)
    private PersonInAgreementEntity person;
}
