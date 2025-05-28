package com.entrega.entrega.infrastructure.security;

import com.entrega.entrega.domain.entity.User;
import com.entrega.entrega.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Intentando cargar usuario con email: {}", email);
        
        User user = userRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> {
                    logger.error("Usuario no encontrado con email: {}", email);
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                });

        logger.debug("Usuario encontrado: {}", user);
        logger.debug("Rol del usuario: {}", user.getRol());
        logger.debug("Contraseña hasheada: {}", user.getContrasena());

        return new org.springframework.security.core.userdetails.User(
                user.getCorreoElectronico(),
                user.getContrasena(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRol().name()))
        );
    }
} 