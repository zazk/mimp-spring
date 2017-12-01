/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Jose Vasquez
 */
@XmlRootElement
public class ServicioAdicional implements Serializable
{ private int CodServicio;
  private String NoServicio;
  private double StCosto;
  private int CoPersona;
  private int FlServicio;

    /**
     * @return the CodServicio
     */
    public int getCodServicio() {
        return CodServicio;
    }

    /**
     * @param CodServicio the CodServicio to set
     */
    public void setCodServicio(int CodServicio) {
        this.CodServicio = CodServicio;
    }

    /**
     * @return the NoServicio
     */
    public String getNoServicio() {
        return NoServicio;
    }

    /**
     * @param NoServicio the NoServicio to set
     */
    public void setNoServicio(String NoServicio) {
        this.NoServicio = NoServicio;
    }

    /**
     * @return the StCosto
     */
    public double getStCosto() {
        return StCosto;
    }

    /**
     * @param StCosto the StCosto to set
     */
    public void setStCosto(double StCosto) {
        this.StCosto = StCosto;
    }

    /**
     * @return the CoPersona
     */
    public int getCoPersona() {
        return CoPersona;
    }

    /**
     * @param CoPersona the CoPersona to set
     */
    public void setCoPersona(int CoPersona) {
        this.CoPersona = CoPersona;
    }

    /**
     * @return the FlServicio
     */
    public int getFlServicio() {
        return FlServicio;
    }

    /**
     * @param FlServicio the FlServicio to set
     */
    public void setFlServicio(int FlServicio) {
        this.FlServicio = FlServicio;
    }
    
}
