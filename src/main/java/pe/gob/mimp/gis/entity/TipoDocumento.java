package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Luis Carrillo
 */
@XmlRootElement
public class TipoDocumento
{
    private Integer coTipoDocumento;
    private String noTipoDocumento;
    private String flEstado;

    /**
     * @return the coTipoDocumento
     */
    public Integer getCoTipoDocumento() {
        return coTipoDocumento;
    }

    /**
     * @param coTipoDocumento the coTipoDocumento to set
     */
    public void setCoTipoDocumento(Integer coTipoDocumento) {
        this.coTipoDocumento = coTipoDocumento;
    }

    /**
     * @return the noTipoDocumento
     */
    public String getNoTipoDocumento() {
        return noTipoDocumento;
    }

    /**
     * @param noTipoDocumento the noTipoDocumento to set
     */
    public void setNoTipoDocumento(String noTipoDocumento) {
        this.noTipoDocumento = noTipoDocumento;
    }

    /**
     * @return the flEstado
     */
    public String getFlEstado() {
        return flEstado;
    }

    /**
     * @param flEstado the flEstado to set
     */
    public void setFlEstado(String flEstado) {
        this.flEstado = flEstado;
    }


}
