package org.habv.bccr;

import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Herman Barrantes
 */
@Liveness
@ApplicationScoped
public class BccrWsHealth implements HealthCheck {

    @Inject
    @RestClient
    private IndicadorClient client;

    @Inject
    @ConfigProperty(name = "bccr.name")
    private String name;
    @Inject
    @ConfigProperty(name = "bccr.email")
    private String email;
    @Inject
    @ConfigProperty(name = "bccr.token")
    private String token;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("BCCR-WS");
        try {
            LocalDate today = LocalDate.now(Constantes.ZONE_ID);
            Response response = client.obtenerIndicadoresEconomicosXmlGet(Constantes.DOLAR_COMPRA, today, today, name, SubNivel.N, email, token);
            int responseCode = response.getStatus();
            String responseMessage = response.getStatusInfo().getReasonPhrase();
            boolean status = Response.Status.OK.getStatusCode() == responseCode;
            return responseBuilder
                    .withData("responseCode", responseCode)
                    .withData("responseMessage", responseMessage)
                    .state(status)
                    .build();
        } catch (WebApplicationException ex) {
            Response response = ex.getResponse();
            int responseCode = response.getStatus();
            String responseMessage = response.getStatusInfo().getReasonPhrase();
            return responseBuilder
                    .withData("responseCode", responseCode)
                    .withData("responseMessage", responseMessage)
                    .withData("exceptionMessage", ex.getMessage())
                    .down()
                    .build();
        } catch (Throwable th) {
            return responseBuilder
                    .withData("exceptionMessage", th.getMessage())
                    .down()
                    .build();
        }
    }

}
