package com.example.insurance_calculator.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "doc_generating_ack")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocGeneratorAckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "agreement_uuid", columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID agreementUUID;

    @Column(name = "path", nullable = false)
    private String path;
}
