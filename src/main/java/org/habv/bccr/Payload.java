package org.habv.bccr;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author Herman Barrantes
 */
public class Payload implements Serializable {

    private final String mensaje;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final OffsetDateTime fecha;

    public Payload(String mensaje, Object... parametros) {
        this.mensaje = String.format(mensaje, parametros);
        this.fecha = OffsetDateTime.now(ZoneId.of("-06:00"));
    }

    public String getMensaje() {
        return mensaje;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

}
