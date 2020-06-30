package me.apd.notificacion;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping("/usuario/{id}")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public List<NotificacionBase> buscarPorUsuario(@PathVariable Long id) {
        return notificacionService.buscarPorUsuario(id);
    }

    @PatchMapping("/{id}/marcarLeida")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public Long marcarLeida(@PathVariable Long id) {
        return notificacionService.marcarLeida(id);
    }

}
