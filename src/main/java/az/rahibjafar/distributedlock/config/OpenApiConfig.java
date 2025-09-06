package az.rahibjafar.distributedlock.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI projectOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Distributed Lock Demo API")
                        .description("Redis + Spring Boot distributed lock example")
                        .version("v1.0.0"));
    }
}
