package org.habv.bccr;

import java.time.LocalDate;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("dolar")
@Produces({MediaType.APPLICATION_JSON})
@Tag(name = "Servicio de Tipo de Cambio del Dolar", description = "Obtiene la información del Tipo de Cambio del Dolar del BCCR")
public class DolarController {

    @Inject
    private IndicadorService indicadoresService;

    @Operation(description = "Obtiene el tipo de cambio del dolar para la compra para el día de hoy")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el día de hoy",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Indicador.class)))
    @APIResponse(
            responseCode = "400",
            description = "Error al obtener el tipo de cambio del dolar para el día de hoy",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Payload.class)))
    @GET
    @Path("compra")
    public Response dolarCompra() {
        List<Indicador> indicadores = indicadoresService.consultarIndicador(Constantes.DOLAR_COMPRA, null, null, SubNivel.N);
        return Response.ok(indicadores.get(0)).build();
    }

    @Operation(description = "Obtiene el tipo de cambio del dolar para la venta para el día de hoy")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el día de hoy",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Indicador.class)))
    @APIResponse(
            responseCode = "400",
            description = "Error al obtener el tipo de cambio del dolar para el día de hoy",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Payload.class)))
    @GET
    @Path("venta")
    public Response dolarVenta() {
        List<Indicador> indicadores = indicadoresService.consultarIndicador(Constantes.DOLAR_VENTA, null, null, SubNivel.N);
        return Response.ok(indicadores.get(0)).build();
    }

    @Operation(description = "Obtiene el tipo de cambio del dolar para la compra para un rango de fechas")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el rango de fechas indicado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Indicador.class)))
    @APIResponse(
            responseCode = "400",
            description = "Error al obtener el tipo de cambio del dolar para el rango de fechas",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Payload.class)))
    @GET
    @Path("compra/historico")
    public Response dolarCompraHistorico(
            @Parameter(description = "Fecha inicial de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaInicial") LocalDate fechaInicial,
            @Parameter(description = "Fecha final de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaFinal") LocalDate fechaFinal
    ) {
        List<Indicador> indicadores = indicadoresService.consultarIndicador(Constantes.DOLAR_COMPRA, fechaInicial, fechaFinal, SubNivel.N);
        return Response.ok(indicadores).build();
    }

    @Operation(description = "Obtiene el tipo de cambio del dolar para la venta para un rango de fechas")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el tipo de cambio del dolar para el rango de fechas indicado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Indicador.class)))
    @APIResponse(
            responseCode = "400",
            description = "Error al obtener el tipo de cambio del dolar para el rango de fechas",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Payload.class)))
    @GET
    @Path("venta/historico")
    public Response dolarVentaHistorico(
            @Parameter(description = "Fecha inicial de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaInicial") LocalDate fechaInicial,
            @Parameter(description = "Fecha final de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaFinal") LocalDate fechaFinal
    ) {
        List<Indicador> indicadores = indicadoresService.consultarIndicador(Constantes.DOLAR_VENTA, fechaInicial, fechaFinal, SubNivel.N);
        return Response.ok(indicadores).build();
    }

}
