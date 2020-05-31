package me.apd.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaView {
    String especialidadId;
    String duracion;
    String fechaInicio;
    String fechaFin;
    String horaInicio;
    String horaFin;
    String medicoId;
}
