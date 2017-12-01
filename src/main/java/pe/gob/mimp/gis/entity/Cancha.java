/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hariki
 */
public class Cancha implements Serializable {
    private Integer CoCancha;
    private String NoCancha;
    private Integer CoLocal;
    private Integer CoTipoCancha;
    private Integer Qtlargo;
    private Integer Qtancho;
    private Integer CoPersona;
    private Integer FlEstado;

    /**
     * @return the CoCancha
     */
    public Cancha() {}
    
    public Cancha(Integer CoCancha, String NoCancha, Integer CoLocal,Integer CoTipoCancha,
                   Integer Qtlargo, Integer Qtancho, Integer CoPersona, Integer FlEstado  ) {
        this.CoCancha=CoCancha;
        this.NoCancha = NoCancha;
        this.CoLocal = CoLocal;
        this.CoTipoCancha =CoTipoCancha;
        this.Qtlargo = Qtlargo;
        this.Qtancho = Qtancho;
        this.CoPersona = CoPersona;
        this.FlEstado = FlEstado;
    }

    public Cancha(String NoCancha, Integer CoLocal,Integer CoTipoCancha,Integer Qtlargo,Integer Qtancho,
                  Integer CoPersona,Integer FlEstado) {
        this.NoCancha = NoCancha;
        this.CoLocal = CoLocal;
        this.CoTipoCancha = CoTipoCancha;
        this.Qtlargo = Qtlargo;
        this.Qtancho = Qtancho;
        this.CoPersona = CoPersona;
        this.FlEstado = FlEstado;
    }
    
    public Cancha(String NoCancha,Integer CoLocal) {
        this.NoCancha = NoCancha;
        this.CoLocal = CoLocal;
    }
    
    
    public Integer getCoCancha() {
        return CoCancha;
    }

    /**
     * @param CoCancha the CoCancha to set
     */
    public void setCoCancha(Integer CoCancha) {
        this.CoCancha = CoCancha;
    }

    /**
     * @return the NoCancha
     */
    public String getNoCancha() {
        return NoCancha;
    }

    /**
     * @param NoCancha the NoCancha to set
     */
    public void setNoCancha(String NoCancha) {
        this.NoCancha = NoCancha;
    }

    /**
     * @return the CoLocal
     */
    public Integer getCoLocal() {
        return CoLocal;
    }

    /**
     * @param CoLocal the CoLocal to set
     */
    public void setCoLocal(Integer CoLocal) {
        this.CoLocal = CoLocal;
    }

    /**
     * @return the CoTipoCancha
     */
    public Integer getCoTipoCancha() {
        return CoTipoCancha;
    }

    /**
     * @param CoTipoCancha the CoTipoCancha to set
     */
    public void setCoTipoCancha(Integer CoTipoCancha) {
        this.CoTipoCancha = CoTipoCancha;
    }

    /**
     * @return the Qtlargo
     */
    public Integer getQtlargo() {
        return Qtlargo;
    }

    /**
     * @param Qtlargo the Qtlargo to set
     */
    public void setQtlargo(Integer Qtlargo) {
        this.Qtlargo = Qtlargo;
    }

    /**
     * @return the QtAncho
     */
    public Integer getQtAncho() {
        return Qtancho;
    }

    /**
     * @param QtAncho the QtAncho to set
     */
    public void setQtAncho(Integer Qtancho) {
        this.Qtancho = Qtancho;
    }

    /**
     * @return the CoPersona
     */
    public Integer getCoPersona() {
        return CoPersona;
    }

    /**
     * @param CoPersona the CoPersona to set
     */
    public void setCoPersona(Integer CoPersona) {
        this.CoPersona = CoPersona;
    }

    /**
     * @return the FlEstado
     */
    public Integer getFlEstado() {
        return FlEstado;
    }

    /**
     * @param FlEstado the FlEstado to set
     */
    public void setFlEstado(Integer FlEstado) {
        this.FlEstado = FlEstado;
    }
    
}
