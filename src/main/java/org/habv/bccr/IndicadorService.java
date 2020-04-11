package org.habv.bccr;

import java.time.LocalDate;
import java.time.ZoneId;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Herman Barrantes
 */
@ApplicationScoped
public class IndicadorService {

    private final ZoneId zoneId;

    @Inject
    private JaxbService jaxb;
    @Inject
    @RestClient
    private IndicadorClient client;

    @Inject
    @ConfigProperty(name = "bccr.name")
    private String name;
    @Inject
    @ConfigProperty(name = "bccr.email")
    private String email;
    @Inject
    @ConfigProperty(name = "bccr.token")
    private String token;

    public IndicadorService() {
        this.zoneId = ZoneId.of("-06:00");//CR
    }

    public Response consultarIndicador(Integer indicador, LocalDate fechaInicial, LocalDate fechaFinal, SubNivel subNivel) throws Exception {
        Response xml = client.obtenerIndicadoresEconomicosXmlGet(indicador, checkNull(fechaInicial), checkNull(fechaFinal), name, subNivel, email, token);
        Indicadores indicadores = jaxb.parse(xml.readEntity(String.class));
        return Response.ok(indicadores.getIndicadores()).build();
    }

    public Response consultarIndicador(Integer indicador) throws Exception {
        LocalDate today = LocalDate.now(zoneId);
        Response xml = client.obtenerIndicadoresEconomicosXmlGet(indicador, today, today, name, SubNivel.N, email, token);
        Indicadores indicadores = jaxb.parse(xml.readEntity(String.class));
        return Response.ok(indicadores.getIndicadores().get(0)).build();
    }

    private LocalDate checkNull(LocalDate date) {
        return date == null ? LocalDate.now(zoneId) : date;
    }

}
