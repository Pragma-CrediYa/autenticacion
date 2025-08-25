package co.com.crediya.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Long id;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private Double salarioBase;

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"
    );

    public void validarReglasDeNegocio() {
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacio");
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del usuario no puede estar vacio");
        }
        if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo del usuario no puede estar vacio");
        }
        if (!EMAIL_REGEX.matcher(correoElectronico).matches()) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
        }
        if (salarioBase == null) {
            throw new IllegalArgumentException("El salario base no puede ser nulo.");
        }
        if (salarioBase < 0 || salarioBase > 15000000) {
            throw new IllegalArgumentException("El salario base debe estar entre 0 y 15,000,000.");
        }

    }



}
