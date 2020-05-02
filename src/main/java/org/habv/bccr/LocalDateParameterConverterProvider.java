package org.habv.bccr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Herman Barrantes
 */
@Provider
public class LocalDateParameterConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (LocalDate.class.equals(rawType)) {
            String pattern = Constantes.PATRON_FECHA;
            for (Annotation annotation : annotations) {
                if (LocalDateFormat.class.equals(annotation.annotationType())) {
                    pattern = ((LocalDateFormat) annotation).value();
                }
            }
            LocalDateParameterConverter ldpc = new LocalDateParameterConverter(pattern);
            return (ParamConverter<T>) ldpc;
        }
        return null;
    }

}
