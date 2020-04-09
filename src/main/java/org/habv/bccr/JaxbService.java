package org.habv.bccr;

import java.io.StringReader;
import javax.enterprise.context.ApplicationScoped;
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

    public JaxbService() throws JAXBException {
        this.context = JAXBContext.newInstance(Raiz.class, Indicadores.class, Indicador.class);
    }

    public Indicadores parse(String xml) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Raiz raiz = (Raiz) unmarshaller.unmarshal(new StringReader(xml));
        return (Indicadores) unmarshaller.unmarshal(new StringReader(raiz.getString()));
    }

}
