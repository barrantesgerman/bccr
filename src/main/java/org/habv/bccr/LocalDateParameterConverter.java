package org.habv.bccr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ParamConverter;

/**
 *
 * @author Herman Barrantes
 */
public class LocalDateParameterConverter implements ParamConverter<LocalDate> {

    private static final String MENSAJE = "Formato de fecha no válido";
    private final DateTimeFormatter formatter;

    public LocalDateParameterConverter(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception ex) {
            throw new WebApplicationException(MENSAJE, ex, Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public String toString(LocalDate value) {
        if (value == null) {
            return null;
        }
        try {
            return formatter.format(value);
        } catch (Exception ex) {
            throw new WebApplicationException(MENSAJE, ex, Response.Status.BAD_REQUEST);
        }
    }

}
