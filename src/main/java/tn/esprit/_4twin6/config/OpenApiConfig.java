package tn.esprit._4twin6.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI coffeeShopOpenAPI() {
        final String securitySchemeName = "nouhaAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token (use 'Nouha 123' for mock)")))
                .info(new Info()
                        .title("Nouha Attafi API COFFEESHOP")
                        .description("""
                            <div style='font-family:Arial;'>
                            <h3>My CoffeeShop Management</h3>
                            <p>Built by <strong>Nouha Attafi</strong> — ESPRIT Student</p>
                            <p>Comprehensive CRUD API covering loyalty cards, orders, promotions, and address management.</p>
                            <p><strong><a href="https://github.com/nouha-attafi" target="_blank" style="color:#6f4e37; text-decoration:underline;">My GitHub Profile</a></strong></p>
                            </div>""")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Nouha Attafi")
                                .email("nouha.attafi@esprit.tn")
                                .url("https://github.com/nouha-attafi")))  // Optional: backup in contact
                .addServersItem(new Server()
                        .url("http://localhost:8088/firstProject")
                        .description("Local Development"));
    }
}