package me.apd.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoView {
    @Nullable
    private Long id;
    private String nombre;
    private String documento;
    private Collection<EspecialidadView> especialidad;
}