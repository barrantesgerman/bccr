package org.habv.bccr;

import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 *
 * @author Herman Barrantes
 */
@RequestScoped
@Path("/indicadores")
@Produces({MediaType.APPLICATION_JSON})
@Tag(name = "Servicio de Indicadores Economicos", description = "Obtiene la informacion de los Indicadores Economicos del BCCR")
public class IndicadoresController {

    private static final int DOLAR_COMPRA = 317;
    private static final int DOLAR_VENTA = 318;

    @Inject
    private IndicadoresService indicadoresService;

    @Operation(description = "Obtiene el tipo de cambio del dolar para la compra para el dia de hoy")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el dia de hoy",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @GET
    @Path("/dolar/compra")
    public Response dolarCompra() throws Exception {
        return indicadoresService.consultarIndicador(DOLAR_COMPRA);
    }

    @Operation(description = "Obtiene el tipo de cambio del dolar para la venta para el dia de hoy")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el dia de hoy",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @GET
    @Path("/dolar/venta")
    public Response dolarVenta() throws Exception {
        return indicadoresService.consultarIndicador(DOLAR_VENTA);
    }

    @Operation(description = "Obtiene el valor del Indicador Economico indicado")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el valor del Indicador Economico indicado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @GET
    @Path("/{indicador}")
    public Response indicador(
            @Parameter(description = "Numero de Indicador Economico", required = true)
            @PathParam("indicador") Integer indicador,
            @Parameter(description = "Fecha inicial de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaInicial") LocalDate fechaInicial,
            @Parameter(description = "Fecha final de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaFinal") LocalDate fechaFinal,
            @Parameter(description = "Subnivel de la consulta", schema = @Schema(enumeration = {"S", "N"}, type = SchemaType.STRING))
            @QueryParam("subNiveles") @DefaultValue("N") SubNivel subNiveles
    ) throws Exception {
        return indicadoresService.consultarIndicador(indicador, fechaInicial, fechaFinal, subNiveles);
    }

}
