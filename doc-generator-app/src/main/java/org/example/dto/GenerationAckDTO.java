package org.example.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.nio.file.Path;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenerationAckDTO {
    UUID agreementUUID;
    Path path;
}
