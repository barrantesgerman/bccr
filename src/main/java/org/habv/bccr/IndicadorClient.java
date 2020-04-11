package org.habv.bccr;

import java.time.LocalDate;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Herman Barrantes
 */
@Path("ObtenerIndicadoresEconomicosXML")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RegisterRestClient
@RegisterProvider(IndicadorExceptionMapper.class)
@RegisterProvider(LocalDateParameterConverterProvider.class)
public interface IndicadorClient {

    @GET
    public Response obtenerIndicadoresEconomicosXmlGet(
            @QueryParam("Indicador") Integer indicador,
            @QueryParam("FechaInicio") @LocalDateFormat("dd/MM/yyyy") LocalDate fechaInicio,
            @QueryParam("FechaFinal") @LocalDateFormat("dd/MM/yyyy") LocalDate fechaFinal,
            @QueryParam("Nombre") String nombre,
            @QueryParam("SubNiveles") SubNivel subNiveles,
            @QueryParam("CorreoElectronico") String correoElectronico,
            @QueryParam("Token") String token
    ) throws IndicadorException;

}
