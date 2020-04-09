package org.habv.bccr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Herman Barrantes
 */
@XmlRootElement(name = "string", namespace = "http://ws.sdde.bccr.fi.cr")
@XmlAccessorType(XmlAccessType.FIELD)
public class Raiz {

    @XmlValue
    @XmlJavaTypeAdapter(EscapeStringXmlAdapter.class)
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
