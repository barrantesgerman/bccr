package org.habv.bccr;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
public class IndicadoresService {

    private final DateTimeFormatter formatter;
    private final ZoneId zoneId;

    @Inject
    private JaxbService jaxb;
    @Inject
    @RestClient
    private IndicadoresClient client;

    @Inject
    @ConfigProperty(name = "bccr.name")
    private String name;
    @Inject
    @ConfigProperty(name = "bccr.email")
    private String email;
    @Inject
    @ConfigProperty(name = "bccr.token")
    private String token;

    public IndicadoresService() {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");//BCCR
        this.zoneId = ZoneId.of("-06:00");//CR
    }

    public Response consultarIndicador(Integer indicador, LocalDate fechaInicial, LocalDate fechaFinal, SubNivel subNivel) throws Exception {
        Response xml = client.obtenerIndicadoresEconomicosXmlGet(indicador, toString(fechaInicial), toString(fechaFinal), name, subNivel, email, token);
        Indicadores indicadores = jaxb.parse(xml.readEntity(String.class));
        return Response.ok(indicadores.getIndicadores()).build();
    }

    public Response consultarIndicador(Integer indicador) throws Exception {
        String date = formatter.format(LocalDate.now(zoneId));
        Response xml = client.obtenerIndicadoresEconomicosXmlGet(indicador, date, date, name, SubNivel.N, email, token);
        Indicadores indicadores = jaxb.parse(xml.readEntity(String.class));
        return Response.ok(indicadores.getIndicadores().get(0)).build();
    }

    private String toString(LocalDate date) {
        if (date == null) {
            date = LocalDate.now(zoneId);
        }
        return formatter.format(date);
    }

}
