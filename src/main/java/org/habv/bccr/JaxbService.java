package org.habv.bccr;

import java.io.StringReader;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Herman Barrantes
 */
@ApplicationScoped
public class JaxbService {

    private final JAXBContext context;

    public JaxbService() {
        try {
            this.context = JAXBContext.newInstance(Raiz.class, Indicadores.class, Indicador.class);
        } catch (JAXBException ex) {
            throw new WebApplicationException("Error al crear JAXBContext", ex, Response.Status.BAD_REQUEST);
        }
    }

    public Indicadores parse(String xml) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Raiz raiz = (Raiz) unmarshaller.unmarshal(new StringReader(xml));
            return (Indicadores) unmarshaller.unmarshal(new StringReader(raiz.getString()));
        } catch (JAXBException ex) {
            throw new WebApplicationException("Error al convertir el XML a Objeto", ex, Response.Status.BAD_REQUEST);
        }
    }

}
