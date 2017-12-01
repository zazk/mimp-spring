package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Luis Carrillo
 */
@XmlRootElement
public class TipoPersona {

    private Integer coTipoPersona;
    private String noTipoPersona;
    private String flEstado;

    /**
     * @return the coTipoPersona
     */
    public Integer getCoTipoPersona() {
        return coTipoPersona;
    }

    /**
     * @param coTipoPersona the coTipoPersona to set
     */
    public void setCoTipoPersona(Integer coTipoPersona) {
        this.coTipoPersona = coTipoPersona;
    }

    /**
     * @return the noTipoPersona
     */
    public String getNoTipoPersona() {
        return noTipoPersona;
    }

    /**
     * @param noTipoPersona the noTipoPersona to set
     */
    public void setNoTipoPersona(String noTipoPersona) {
        this.noTipoPersona = noTipoPersona;
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
