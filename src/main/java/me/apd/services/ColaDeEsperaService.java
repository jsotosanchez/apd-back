package me.apd.services;

import me.apd.entities.ColaDeEspera;
import me.apd.entities.Especialidad;

public interface ColaDeEsperaService {
    ColaDeEspera agregar(Long especialidadId, Long pacineteId, Long medicoId);

    ColaDeEspera buscarPorEspecialidad(Especialidad especialidad);
}
