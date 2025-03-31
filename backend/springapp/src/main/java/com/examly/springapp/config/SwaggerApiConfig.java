package com.examly.springapp.config;
 
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;
 
import java.util.List;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
 
@Configuration
public class SwaggerApiConfig {
 
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Edu Investor")
                        .description("Edu Investor is a digital platform offering diverse, education-focused investment opportunities with detailed insights to help users make informed financial decisions.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Team 6")
                                .email("edu.investor@yopmail.com")
                                .url("")) //after completing our url.
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(new Server().url("https://8080-cbbbeffcbaeedecadbbfbaebabfcfdfcdfdeacdcff.premiumproject.examly.io")))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes(
                                "bearerAuth", new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(HTTP)
                                        .scheme("bearer")
                                        .description("Provide the JWT token.")
                                        .bearerFormat("JWT")));
    }
}