package me.apd.security;

import me.apd.usuario.Usuario;
import me.apd.usuario.UsuarioNotFoundException;
import me.apd.usuario.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RolesAllowed({"MEDICO", "PACIENTE"})
public class LogInController {
    private final UsuarioService usuarioService;

    public LogInController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public UsuarioView login() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        @SuppressWarnings("Unchecked")
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String documento = principal.getUsername();
        Set<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(r -> r.substring(5)).collect(Collectors.toSet());

        Usuario usuario = usuarioService.buscarPorDocumento(documento).orElseThrow(UsuarioNotFoundException::new);

        return new UsuarioView(documento, usuario.getId(), roles, usuario.getPagoAlDia());
    }
}
