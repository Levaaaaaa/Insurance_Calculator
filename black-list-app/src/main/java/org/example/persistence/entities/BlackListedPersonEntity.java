package org.example.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.util.UUIDConverter;

import java.util.UUID;

@Entity
@Table(name = "black_listed_persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Convert(converter = UUIDConverter.class)
    @Column(name = "person_uuid", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID personCode;
}
