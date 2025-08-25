package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> registrarUsuario(User user) {
        try {
            user.validarReglasDeNegocio();
        } catch (Exception e) {
            return Mono.error(e);
        }
        return userRepository.existsByCorreoElectronico(user.getCorreoElectronico())
                .flatMap(correoYaExiste -> {
                    if(Boolean.TRUE.equals(correoYaExiste)) {
                        return Mono.error(new IllegalArgumentException("El correo electrónico ya existe en el sistema."));
                    }
                    return userRepository.save(user);
                });
    }


    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

}
