package me.apd.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoNuevoView {
    @Nullable
    private String id;
    private String horario;
    private String pacienteId;
    private String medicoId;
    private String especialidadId;
    private Boolean confirmado;
}
