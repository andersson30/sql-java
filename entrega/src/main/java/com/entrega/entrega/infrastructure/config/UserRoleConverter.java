package com.entrega.entrega.infrastructure.config;

import com.entrega.entrega.domain.entity.User.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return UserRole.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No enum constant com.entrega.entrega.domain.entity.User.UserRole." + dbData);
        }
    }
} 