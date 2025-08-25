package co.com.crediya.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigInteger;

@Getter
@Setter
@ConfigurationProperties(prefix = "routes.paths")
public class UserPath {

    private String users;
    private Long userById;

}
