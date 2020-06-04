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

    @GetMapping("")
    public List<Notificacion> buscarTodas() {
        return notificacionService.buscarTodas();
    }

    @PatchMapping("/{id}")
    public Long marcarLeida(@PathVariable String id) {
        Optional<Notificacion> notificacion = notificacionService.buscarPorId(Long.parseLong(id));

        if (notificacion.isPresent()) {
            return notificacionService.marcarLeida(notificacion.get()).getId();
        }
        throw new NotificacionNotFoundException();
    }

}
