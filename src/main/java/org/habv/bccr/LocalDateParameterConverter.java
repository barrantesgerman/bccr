package org.habv.bccr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.ext.ParamConverter;

/**
 *
 * @author Herman Barrantes
 */
public class LocalDateParameterConverter implements ParamConverter<LocalDate> {

    @Override
    public LocalDate fromString(String value) {
        if (value == null) {
            return null;
        }
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString(LocalDate value) {
        if (value == null) {
            return null;
        }
        return DateTimeFormatter.ISO_LOCAL_DATE.format(value);
    }

}
