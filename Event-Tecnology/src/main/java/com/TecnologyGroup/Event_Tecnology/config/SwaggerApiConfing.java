package com.TecnologyGroup.Event_Tecnology.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApiConfing {
    @Value("${proyectevent.openapi.dev-url}")
    private String defaulturl;
    public OpenAPI openAPI(){
        Server devServer = new Server();
        devServer.setUrl(defaulturl);
        devServer.setDescription("Development Server");
        //Informacion de contacto
        Contact contact = new Contact();
        contact.setName("proyectevent");
        contact.setUrl("https://github.com/Evn-Tecnology/API"); //landingpage

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");
        Info info = new Info()
                .title("API Plataforma de Eventos Tecnologicos")
                .version("1.0")
                .contact(contact)
                .description("API Restful de eventos")
                .termsOfService("terms of service")
                .license(mitLicense);
        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
