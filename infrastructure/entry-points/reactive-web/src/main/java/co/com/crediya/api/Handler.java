package co.com.crediya.api;

import co.com.crediya.model.user.User;
import co.com.crediya.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Log4j2
@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;
    private final UserUseCase userUseCase;

    public Mono<ServerResponse> registrarUsuario(ServerRequest serverRequest) {
        log.info("Petición entrante para registrar un nuevo usuario.");

        return serverRequest.bodyToMono(User.class)
                .flatMap(user -> {
                    log.info("Datos recibidos para el correo: {}", user.getCorreoElectronico());
                    return userUseCase.registrarUsuario(user);
                })
                .flatMap(usuarioGuardado -> {

                    log.info("Usuario registrado exitosamente con ID: {}", usuarioGuardado.getId());
                    return ServerResponse
                            .created(URI.create("/api/v1/usuarios/" + usuarioGuardado.getId()))
                            .bodyValue(usuarioGuardado);
                })
                .onErrorResume(IllegalArgumentException.class, e -> {
                    log.warn("Error de validación al intentar registrar usuario: {}", e.getMessage());
                    return ServerResponse.badRequest().bodyValue(e.getMessage());
                })
                .onErrorResume(e -> {
                    log.error("Error inesperado en el flujo de registro de usuario.", e);
                    return ServerResponse.status(500).bodyValue("Ocurrió un error interno en el servidor.");
                });
    }

}
