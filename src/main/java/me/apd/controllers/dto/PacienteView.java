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
public class PacienteView {
    @Nullable
    private Long id;
    private String nombre;
    private String documento;
    private Boolean pagoAlDia;
}
