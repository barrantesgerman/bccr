package org.habv.bccr.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.habv.bccr.Constantes;
import org.habv.bccr.IndicadorService;
import org.habv.bccr.SubNivel;

/**
 *
 * @author Herman Barrantes
 */
@Liveness
@ApplicationScoped
public class BccrWsHealth implements HealthCheck {

    @Inject
    private IndicadorService indicadoresService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("BCCR-WS");
        try {
            indicadoresService.consultarIndicador(Constantes.DOLAR_COMPRA, null, null, SubNivel.N);
            return responseBuilder
                    .withData("responseCode", Response.Status.OK.getStatusCode())
                    .withData("responseMessage", Response.Status.OK.getReasonPhrase())
                    .up()
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
