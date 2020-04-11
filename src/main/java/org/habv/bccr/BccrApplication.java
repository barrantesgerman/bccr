package org.habv.bccr;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 * @author Herman Barrantes
 */
@OpenAPIDefinition(
        info = @Info(
                title = "BCCR",
                version = "1.0.0",
                contact = @Contact(
                        name = "Herman Barrantes",
                        email = "barrantesgerman@gmail.com",
                        url = "https://habv.org")
        )
)
@ApplicationPath("/api")
@ApplicationScoped
public class BccrApplication extends Application {
}
