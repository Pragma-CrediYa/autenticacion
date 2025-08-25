package co.com.crediya.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table("usuarios")
public class UserEntity {

    @Id
    private Long id;
    private String nombres;
    private String apellidos;
    @Column("fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    @Column("correo_electronico")
    private String correoElectronico;
    @Column("salario_base")
    private Double salarioBase;
}
