package org.habv.bccr;

import java.time.OffsetDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Herman Barrantes
 */
@XmlRootElement(name = "INGC011_CAT_INDICADORECONOMIC")
@XmlAccessorType(XmlAccessType.FIELD)
public class Indicador {

    @XmlElement(name = "COD_INDICADORINTERNO")
    private Integer codigo;
    @XmlElement(name = "DES_FECHA")
    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    private OffsetDateTime fecha;
    @XmlElement(name = "NUM_VALOR")
    private Double valor;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
