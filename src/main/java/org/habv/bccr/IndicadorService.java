package org.habv.bccr;

import java.time.LocalDate;
import java.util.List;
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

    public List<Indicador> consultarIndicador(Integer indicador, LocalDate fechaInicial, LocalDate fechaFinal, SubNivel subNivel) {
        Response xml = client.obtenerIndicadoresEconomicosXmlGet(indicador, checkNull(fechaInicial), checkNull(fechaFinal), name, subNivel, email, token);
        Indicadores indicadores = jaxb.parse(xml.readEntity(String.class));
        return indicadores.getIndicadores();
    }

    private LocalDate checkNull(LocalDate date) {
        return date == null ? LocalDate.now(Constantes.ZONE_ID) : date;
    }

}
