package main.java.com.blumbit.supermercado.service;

import javax.swing.text.html.parser.Entity;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final EntityManager entityManager;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
      
    }

    public Usuario save(Usuario usuario, Rol rol) {
        for() (Rol r : usuario.getRoles()) {
            if (r.getId() == null) {
                throw new IllegalArgumentException("El rol debe tener un ID asignado");
            }
        }
        rolRepository.save(rol);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
