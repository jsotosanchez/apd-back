package me.apd.notificacion;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping("/usuario/{id}")
    public List<NotificacionBase> buscarPorUsuario(@PathVariable Long id) {
        return notificacionService.buscarPorUsuario(id);
    }

    @PatchMapping("/{id}/marcarLeida")
    public Long marcarLeida(@PathVariable Long id) {
        return notificacionService.marcarLeida(id);
    }

}
