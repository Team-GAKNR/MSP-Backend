package de.gaknr.mspbackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private final ServletContext context;

    public OpenAPIConfig(ServletContext context) {
        this.context = context;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
            .addServersItem(new Server().url(this.context.getContextPath()))
            .info(new Info()
                .title("MSP project")
                .description("""

                    ## Auth

                    ## Authentication

                    This project uses JWTs to authenticate requests. You will receive a bearer token by making a POST-Request in IntelliJ on:

                    ```
                    POST http://localhost:8081/realms/msp/protocol/openid-connect/token
                    Content-Type: application/x-www-form-urlencoded
                    grant_type=password&client_id=msp&username=test&password=test
                    ```

                    or by CURL
                    ```
                    curl -X POST 'http://localhost:8081/realms/msp/protocol/openid-connect/token'
                    --header 'Content-Type: application/x-www-form-urlencoded'
                    --data-urlencode 'grant_type=password'
                    --data-urlencode 'client_id=msp'
                    --data-urlencode 'username=test'
                    --data-urlencode 'password=test'
                    ```

                    To get a bearer-token in Postman, you have to follow the instructions in\s
                     [Postman-Documentation](https://documenter.getpostman.com/view/7294517/SzmfZHnd).""")
                .version("0.1"))
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(
                new Components()
                    .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                            .name(securitySchemeName)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            );
    }

}
