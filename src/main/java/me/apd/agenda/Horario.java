package me.apd.agenda;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Horario {
    Long id;
    LocalDateTime hora;
}


