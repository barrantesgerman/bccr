package org.habv.bccr;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import javax.json.bind.annotation.JsonbDateFormat;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 *
 * @author Herman Barrantes
 */
@Schema(name = "Payload", description = "Representa un mensaje de respuesta genérico")
public class Payload implements Serializable {

    @Schema(description = "Detalle de la respuesta", example = "Error la fecha inicial debe ser menor que la fecha final.")
    private final String mensaje;
    @Schema(description = "Fecha de la respuesta", type = SchemaType.STRING, format = "date-time", example = "2020-04-10T13:15:35-06:00")
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private final OffsetDateTime fecha;

    public Payload(String mensaje, Object... parametros) {
        this.mensaje = String.format(mensaje, parametros);
        this.fecha = OffsetDateTime.now(Constantes.ZONE_ID);
    }

    public String getMensaje() {
        return mensaje;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

}
