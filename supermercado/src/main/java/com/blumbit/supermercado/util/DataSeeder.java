package com.blumbit.supermercado.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blumbit.supermercado.entity.Usuario;
import com.blumbit.supermercado.repository.UsuarioRepository;

@Component
public class DataSeeder implements ApplicationRunner{

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(usuarioRepository.count() == 0){
            usuarioRepository.save(Usuario.builder()
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("Admin1!"))
                        .nombres("admin")
                        .build());
        }
    }

}
