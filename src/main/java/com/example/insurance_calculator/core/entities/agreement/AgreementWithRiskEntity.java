package com.example.insurance_calculator.core.entities.agreement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "selected_risks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementWithRiskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "risk_ic", nullable = false)
    private String riskIc;


    @ManyToOne
    @JoinColumn(name = "agreement", nullable = false)
    private AgreementEntity agreement;
}
