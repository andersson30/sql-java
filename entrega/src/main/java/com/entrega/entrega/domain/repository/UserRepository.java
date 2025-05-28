package com.entrega.entrega.domain.repository;

import com.entrega.entrega.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM USUARIO WHERE UPPER(correo_electronico) = UPPER(:email)", nativeQuery = true)
    Optional<User> findByCorreoElectronico(@Param("email") String correoElectronico);
    
    boolean existsByCorreoElectronico(String correoElectronico);
} 