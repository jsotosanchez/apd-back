package me.apd.coladeespera;

import me.apd.especialidad.Especialidad;

public interface ColaDeEsperaService {
    ColaDeEspera agregar(Long especialidadId, Long pacineteId, Long medicoId);

    ColaDeEspera buscarPorEspecialidad(Especialidad especialidad);
}
