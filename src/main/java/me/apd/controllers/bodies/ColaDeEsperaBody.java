package me.apd.controllers.bodies;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColaDeEsperaBody {
    private String id;
    private String pacienteId;
    private String medicoId;
    private String especialidadId;
}
