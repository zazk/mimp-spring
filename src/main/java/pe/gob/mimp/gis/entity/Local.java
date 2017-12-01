/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;


import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Vasquez
 */
@XmlRootElement
public class Local {
    private int CoLocal;
    private String NoLocal;
    private String NoDireccion;
    private int CoEncargado;
    private int CoUsuario;
    private int FlEstado;

    /**
     * @return the CoLocal
     */
    public int getCoLocal() {
        return CoLocal;
    }

    /**
     * @param CoLocal the CoLocal to set
     */
    public void setCoLocal(int CoLocal) {
        this.CoLocal = CoLocal;
    }

    /**
     * @return the NoLocal
     */
    public String getNoLocal() {
        return NoLocal;
    }

    /**
     * @param NoLocal the NoLocal to set
     */
    public void setNoLocal(String NoLocal) {
        this.NoLocal = NoLocal;
    }

    /**
     * @return the CoEncargado
     */
    public int getCoEncargado() {
        return CoEncargado;
    }

    /**
     * @param CoEncargado the CoEncargado to set
     */
    public void setCoEncargado(int CoEncargado) {
        this.CoEncargado = CoEncargado;
    }

    /**
     * @return the CoUsuario
     */
    public int getCoUsuario() {
        return CoUsuario;
    }

    /**
     * @param CoUsuario the CoUsuario to set
     */
    public void setCoUsuario(int CoUsuario) {
        this.CoUsuario = CoUsuario;
    }

    /**
     * @return the FlEstado
     */
    public int getFlEstado() {
        return FlEstado;
    }

    /**
     * @param FlEstado the FlEstado to set
     */
    public void setFlEstado(int FlEstado) {
        this.FlEstado = FlEstado;
    }

    /**
     * @return the NoDireccion
     */
    public String getNoDireccion() {
        return NoDireccion;
    }

    /**
     * @param NoDireccion the NoDireccion to set
     */
    public void setNoDireccion(String NoDireccion) {
        this.NoDireccion = NoDireccion;
    }
    
}
