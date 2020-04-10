package org.habv.bccr;

import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 *
 * @author Herman Barrantes
 */
public class IndicadorExceptionMapper implements ResponseExceptionMapper<IndicadorException> {

    @Override
    public IndicadorException toThrowable(Response response) {
        String mensaje = response.readEntity(String.class);
        return new IndicadorException(
                mensaje,
                Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new Payload(mensaje))
                        .build());
    }

}
