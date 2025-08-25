package co.com.crediya.model.user.gateways;

import co.com.crediya.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigInteger;

public interface UserRepository {

    Flux<User> getAll();
    Mono<User> getById(Long id);

    Mono<User> save(User user);

    Mono<Boolean> existsByCorreoElectronico(String correoElectronico);

    Mono<Void> deleteById (Long id);
}
