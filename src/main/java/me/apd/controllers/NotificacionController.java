package me.apd.controllers;

import me.apd.entities.Notificacion;
import me.apd.exceptions.NotificacionNotFoundException;
import me.apd.services.NotificacionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping("/usuario/{id}")
    public List<Notificacion> buscarPorUsuario(@PathVariable Long id) {
        return notificacionService.buscarPorUsuario(id);
    }

    @PatchMapping("/{id}/marcarLeida")
    public Long marcarLeida(@PathVariable Long id) {
        Optional<Notificacion> notificacion = notificacionService.buscarPorId(id);

        if (notificacion.isPresent()) {
            return notificacionService.marcarLeida(notificacion.get()).getId();
        }
        throw new NotificacionNotFoundException();
    }

}
