import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(RegistroRequest registroRequest) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(registroRequest.getNombreCompleto());
        usuario.setEmail(registroRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));

        Rol rolUsuario = rolRepository.findByNombre(RolNombre.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRoles(Set.of(rolUsuario));

        return usuarioRepository.save(usuario);
    }
}
