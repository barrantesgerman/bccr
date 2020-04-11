package org.habv.bccr;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

/**
 *
 * @author Herman Barrantes
 */
public class BadRequestResponseExceptionMapper implements ResponseExceptionMapper<BadRequestException> {

    @Override
    public BadRequestException toThrowable(Response response) {
        String mensaje = response.readEntity(String.class);
        return new BadRequestException(
                mensaje,
                Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new Payload(mensaje))
                        .build());
    }

}
