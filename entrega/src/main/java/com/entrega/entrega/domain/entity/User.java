package com.entrega.entrega.domain.entity;

import com.entrega.entrega.infrastructure.config.UserRoleConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
    private String correoElectronico;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    @Column(name = "rol", nullable = false, length = 20)
    @Convert(converter = UserRoleConverter.class)
    private UserRole rol;

    public enum UserRole {
        ADMINISTRADOR("Administrador"),
        AUXILIAR("Auxiliar de Registro");

        private final String displayName;

        UserRole(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static UserRole fromString(String role) {
            try {
                return UserRole.valueOf(role.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("No enum constant com.entrega.entrega.domain.entity.User.UserRole." + role);
            }
        }

        public static UserRole fromDisplayName(String displayName) {
            for (UserRole role : values()) {
                if (role.displayName.equals(displayName)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("No enum constant with display name: " + displayName);
        }
    }
} 