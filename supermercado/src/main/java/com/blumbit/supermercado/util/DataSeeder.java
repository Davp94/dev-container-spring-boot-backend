package com.blumbit.supermercado.util;

import java.time.Instant;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blumbit.supermercado.entity.Categoria;
import com.blumbit.supermercado.entity.Permiso;
import com.blumbit.supermercado.entity.PermisoRol;
import com.blumbit.supermercado.entity.Rol;
import com.blumbit.supermercado.entity.Usuario;
import com.blumbit.supermercado.repository.CategoriaRepository;
import com.blumbit.supermercado.repository.PermisoRepository;
import com.blumbit.supermercado.repository.PermisoRolRepository;
import com.blumbit.supermercado.repository.ProductoRepository;
import com.blumbit.supermercado.repository.RolRepository;
import com.blumbit.supermercado.repository.UsuarioRepository;
import com.github.javafaker.Faker;

@Component
public class DataSeeder implements ApplicationRunner{

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final PermisoRolRepository permisoRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    //TODO add categirias repository

    public DataSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, PermisoRepository permisoRepository, PermisoRolRepository permisoRolRepository, RolRepository rolRepository, ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.permisoRepository = permisoRepository;
        this.permisoRolRepository = permisoRolRepository;
        this.passwordEncoder = passwordEncoder;
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(usuarioRepository.count() == 0){
            Rol rolAdmin = rolRepository.save(Rol.builder()
                        .nombre("ADMIN")
                        .descripcion("Rol administrador del sistema")
                        .build()); 
            Usuario usuarioSaved = usuarioRepository.save(Usuario.builder()
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("Admin1!"))
                        .nombres("admin")
                        .roles(List.of(rolAdmin))
                        .build());
            Permiso permiso1 = Permiso.builder()
                                .descripcion("administracion de usuarios")
                                .nombre("USUARIOS")
                                .action("ADMIN")
                                .build();
            Permiso permiso2 = Permiso.builder()
                                .descripcion("administracion de productos")
                                .nombre("PRODUCTOS")
                                .action("ADMIN")
                                .build();
            List<Permiso> permisosSaved = permisoRepository.saveAll(List.of(permiso1, permiso2));
            PermisoRol permisoRol1 = PermisoRol.builder()
                                .permiso(permisosSaved.getFirst())
                                .rol(rolAdmin)
                                .active(true)
                                .fechaCreacion(Instant.now())
                                .build();
            PermisoRol permisoRol2 = PermisoRol.builder()
                                .permiso(permisosSaved.getLast())
                                .rol(rolAdmin)
                                .active(true)
                                .fechaCreacion(Instant.now())
                                .build();
            permisoRolRepository.saveAll(List.of(permisoRol1, permisoRol2));
        }
         //TODO ADD DATA PRODUCTOS & CATEGORIAS
            if (categoriaRepository.count() == 0) {
                Faker faker = new Faker();
                for (int i = 0; i < 10; i++) {
                    categoriaRepository.save(Categoria.builder()
                            .descripcion(faker.animal().name())
                            .nombre(faker.company().name())
                            .build());
                }
            }
    }

}
