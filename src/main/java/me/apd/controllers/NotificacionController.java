package me.apd.controllers;

import me.apd.entities.Notificacion;
import me.apd.exceptions.NotificacionNotFoundException;
import me.apd.repositories.NotificacionBase;
import me.apd.services.NotificacionService;
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
        Notificacion notificacion = notificacionService.buscarPorId(id).orElseThrow(NotificacionNotFoundException::new);
        return notificacionService.marcarLeida(notificacion).getId();
    }

}
