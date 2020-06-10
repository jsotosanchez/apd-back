package me.apd.especialidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "especialidades")
@AllArgsConstructor
@NoArgsConstructor
public class Especialidad {
    @Id
    private Long id;
    private String descripcion;

}
