package me.apd.coladeespera;


import me.apd.especialidad.Especialidad;
import me.apd.usuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ColaDeEsperaServiceImpl implements ColaDeEsperaService {

    private final ColaDeEsperaRepository colaDeEsperaRepository;

    public ColaDeEsperaServiceImpl(ColaDeEsperaRepository colaDeEsperaRepository) {
        this.colaDeEsperaRepository = colaDeEsperaRepository;
    }


    @Override
    public ColaDeEspera agregar(Long especialidadId, Long pacineteId, Long medicoId) {

        Especialidad especialidad = Especialidad.builder().id(especialidadId).build();
        Usuario paciente = Usuario.builder().id(pacineteId).build();
        ColaDeEspera nuevaEntrada;

        if (medicoId != null) {
            Usuario medico = Usuario.builder().id(medicoId).build();
            nuevaEntrada = ColaDeEspera.builder().especialidad(especialidad).medico(medico).paciente(paciente).build();
        } else {
            nuevaEntrada = ColaDeEspera.builder().especialidad(especialidad).paciente(paciente).build();
        }
        return colaDeEsperaRepository.save(nuevaEntrada);
    }

    @Override
    public ColaDeEspera buscarPorEspecialidad(Especialidad especialidad) {
        return colaDeEsperaRepository.findByEspecialidad(especialidad);
    }
}
