package me.apd.coladeespera;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "cola_de_espera")
public class ColaDeEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private Long especialidadId;

    public ColaDeEspera(Long id, Long pacienteId, Long medicoId, Long especialidadId) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.especialidadId = especialidadId;
    }

    public ColaDeEspera() {
    }
}
