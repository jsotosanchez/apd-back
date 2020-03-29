package me.apd.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurnoAModificarView {
    @Nullable
    private Long id;
    private Date horario;
    private Long pacienteId;
    private Long medicoId;
    private Long especialidadId;
    private Boolean confirmado;
}
