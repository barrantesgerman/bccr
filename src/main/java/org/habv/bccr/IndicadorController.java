package org.habv.bccr;

import java.time.LocalDate;
import java.util.List;
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
@Path("indicadores")
@Produces({MediaType.APPLICATION_JSON})
@Tag(name = "Servicio de Indicadores Económicos", description = "Obtiene la información de los Indicadores Económicos del BCCR")
public class IndicadorController {

    @Inject
    private IndicadorService indicadoresService;

    @Operation(description = "Obtiene el valor del Indicador Económico indicado")
    @APIResponse(
            responseCode = "200",
            description = "Exito al obtener el valor del Indicador Económico indicado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = Indicador.class)))
    @APIResponse(
            responseCode = "400",
            description = "Error al obtener el valor del Indicador Económico indicado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = Payload.class)))
    @GET
    @Path("{indicador}")
    public Response indicador(
            @Parameter(description = "Numero de Indicador Económico", required = true, schema = @Schema(type = SchemaType.INTEGER))
            @PathParam("indicador") Integer indicador,
            @Parameter(description = "Fecha inicial de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaInicial") LocalDate fechaInicial,
            @Parameter(description = "Fecha final de la consulta", schema = @Schema(format = "date", type = SchemaType.STRING))
            @QueryParam("fechaFinal") LocalDate fechaFinal,
            @Parameter(description = "Subnivel de la consulta", schema = @Schema(enumeration = {"S", "N"}, type = SchemaType.STRING))
            @QueryParam("subNiveles") @DefaultValue("N") SubNivel subNiveles
    ) {
        List<Indicador> indicadores = indicadoresService.consultarIndicador(indicador, fechaInicial, fechaFinal, subNiveles);
        return Response.ok(indicadores).build();
    }

}
