package org.habv.bccr;

import java.time.OffsetDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 *
 * @author Herman Barrantes
 */
@Schema(name = "Indicador", description = "Representa el valor de un Indicador Económico del BCCR")
@XmlRootElement(name = "INGC011_CAT_INDICADORECONOMIC")
@XmlAccessorType(XmlAccessType.FIELD)
public class Indicador {

    @JsonbTransient
    @XmlElement(name = "COD_INDICADORINTERNO")
    private Integer codigo;
    @Schema(description = "Fecha del Indicador Económico", type = SchemaType.STRING, example = "2020-04-10T00:00:00-06:00")
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @XmlElement(name = "DES_FECHA")
    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    private OffsetDateTime fecha;
    @Schema(description = "Valor del Indicador Económico", type = SchemaType.NUMBER, example = "575.12")
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
