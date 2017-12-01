package pe.gob.mimp.gis.entity;

import java.util.Date;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author cesar soriano
 */

@XmlRootElement
public class Alquiler implements Serializable{
    private Integer coAlquiler;
    private Date feAlquiler;
    private Integer coCancha;
    private Integer coUsuarioSolicitante;
    private String hrInicio;
    private String hrFin;
    private Integer coPersona;
    private Date feRegistro;
    private String flEstado;

    public Alquiler() {}
    
    public Alquiler(Integer coAlquiler, Date feAlquiler, Integer coCancha, Integer coUsuarioSolicitante, String hrInicio, String hrFin, Integer coPersona, Date feRegistro, String flEstado) {
        this.coAlquiler = coAlquiler;
        this.feAlquiler = feAlquiler;
        this.coCancha = coCancha;
        this.coUsuarioSolicitante = coUsuarioSolicitante;
        this.hrInicio = hrInicio;
        this.hrFin = hrFin;
        this.feRegistro = feRegistro;
        this.flEstado = flEstado;
    }

    public Integer getCoAlquiler() {
        return coAlquiler;
    }

    public void setCoAlquiler(Integer coAlquiler) {
        this.coAlquiler = coAlquiler;
    }

    public Date getFeAlquiler() {
        return feAlquiler;
    }

    public void setFeAlquiler(Date feAlquiler) {
        this.feAlquiler = feAlquiler;
    }

    public Integer getCoCancha() {
        return coCancha;
    }

    public void setCoCancha(Integer coCancha) {
        this.coCancha = coCancha;
    }
    
    public Integer getCoUsuarioSolicitante() {
        return coUsuarioSolicitante;
    }

    public void setCoUsuarioSolicitante(Integer coUsuarioSolicitante) {
        this.coUsuarioSolicitante = coUsuarioSolicitante;
    }

    public String getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(String hrInicio) {
        this.hrInicio = hrInicio;
    }

    public String getHrFin() {
        return hrFin;
    }

    public void setHrFin(String hrFin) {
        this.hrFin = hrFin;
    }

    public Integer getCoPersona() {
        return coPersona;
    }

    public void setCoPersona(Integer coPersona) {
        this.coPersona = coPersona;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public String getFlEstado() {
        return flEstado;
    }

    public void setFlEstado(String flEstado) {
        this.flEstado = flEstado;
    }
}