package org.example.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.ByteBuffer;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public UUID convertToEntityAttribute(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long h = bb.getLong();
        long l = bb.getLong();
        return new UUID(h, l);
    }
}
