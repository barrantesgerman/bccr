package org.habv.bccr;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Herman Barrantes
 */
public class IndicadorException extends BadRequestException {

    public IndicadorException(String message, Response response) {
        super(message, response);
    }
    
}
