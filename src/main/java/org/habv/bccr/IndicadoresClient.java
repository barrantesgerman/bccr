package org.habv.bccr;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Herman Barrantes
 */
@Path("ObtenerIndicadoresEconomicosXML")
@RegisterRestClient
public interface IndicadoresClient {

    @GET
    public Response obtenerIndicadoresEconomicosXmlGet(
            @QueryParam("Indicador") Integer indicador,
            @QueryParam("FechaInicio") String fechaInicio,
            @QueryParam("FechaFinal") String fechaFinal,
            @QueryParam("Nombre") String nombre,
            @QueryParam("SubNiveles") SubNivel subNiveles,
            @QueryParam("CorreoElectronico") String correoElectronico,
            @QueryParam("Token") String token
    );

}
