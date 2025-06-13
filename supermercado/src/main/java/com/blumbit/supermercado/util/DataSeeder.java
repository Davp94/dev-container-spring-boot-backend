package com.blumbit.supermercado.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blumbit.supermercado.entity.Categoria;
import com.blumbit.supermercado.entity.Permiso;
import com.blumbit.supermercado.entity.PermisoRol;
import com.blumbit.supermercado.entity.Producto;
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
                List<Categoria> categorias = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Categoria categoriaSaved = categoriaRepository.save(Categoria.builder()
                            .descripcion(faker.lorem().sentence(10))
                            .nombre(faker.commerce().department())
                            .build());
                    categorias.add(categoriaSaved);
                }
                Random random = new Random();
                for (int i = 0; i < 1000; i++) {
                    Categoria categoria = categorias.get(random.nextInt(categorias.size()));
                    productoRepository.save(Producto.builder()
                    .activo(faker.bool().bool())
                    .codigoBarra(faker.code().ean13())
                    .descripcion(faker.commerce().productName()+" - " + faker.lorem().sentence(5))
                    .fechaRegistro(LocalDate.now())
                    // .fechaRegistro(faker.date().between(
                    //     Date.from(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant()), 
                    //     Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
                    .imagenUrl("image-product-example.png")
                    .marca(faker.company().name())
                    .nombre(faker.commerce().productName())
                    .precioVentaActual(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000)))
                    .stockMinimo(faker.number().numberBetween(5, 50))
                    .categoria(categoria)
                    .unidadMedida(getRandomUnidadMedida(faker)).build());
                }   
            }
    }
    private String getRandomUnidadMedida(Faker faker){
        String[] unidades = {"kg", "g", "ml", "l", "pack", "unit"};
        return unidades[faker.number().numberBetween(0, unidades.length)];
    }

}
