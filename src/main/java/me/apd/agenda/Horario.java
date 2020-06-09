package me.apd.agenda;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Horario {
    private LocalDateTime horario;
}


