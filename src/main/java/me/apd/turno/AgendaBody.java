package me.apd.turno;

import lombok.Value;

@Value
public class AgendaBody {
    public Long medicoId;
    public Long especialidadId;
    public String fechaInicio;
    public String fechaFin;
    public String horaInicio;
    public String horaFin;
}
