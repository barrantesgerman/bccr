package org.habv.bccr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Herman Barrantes
 */
@XmlRootElement(name = "Datos_de_INGC011_CAT_INDICADORECONOMIC")
@XmlAccessorType(XmlAccessType.FIELD)
public class Indicadores {

    @XmlElement(name = "INGC011_CAT_INDICADORECONOMIC")
    private List<Indicador> indicadores;

    public List<Indicador> getIndicadores() {
        if (indicadores == null) {
            indicadores = new ArrayList<>();
        }
        return indicadores;
    }

}
