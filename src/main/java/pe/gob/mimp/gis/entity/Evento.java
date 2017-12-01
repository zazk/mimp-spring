/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;

/**
 *
 * @author HEspirituM
 */
public class Evento {
    
    private Integer CodigoEvento;
    private String NombreEvento;
    private String FechaEvento;
    private Integer CodigoPersona;
    private String FechaRegistro;
    private Integer FlagEstado;
    
    public Evento(){}
    
    public Evento(Integer codigoEvento, String nombreEvento, String fechaEvento,Integer codigoPersona, String fechaRegistro, Integer flagEstado) {
        this.CodigoEvento = codigoEvento;
        this.NombreEvento = nombreEvento;
        this.FechaEvento = fechaEvento;
        this.CodigoPersona = codigoPersona;
        this.FechaRegistro = fechaRegistro;
        this.FlagEstado = flagEstado;
    }

    /**
     * @return the CodigoEvento
     */
    public Integer getCodigoEvento() {
        return CodigoEvento;
    }

    /**
     * @param CodigoEvento the CodigoEvento to set
     */
    public void setCodigoEvento(Integer CodigoEvento) {
        this.CodigoEvento = CodigoEvento;
    }

    /**
     * @return the NombreEvento
     */
    public String getNombreEvento() {
        return NombreEvento;
    }

    /**
     * @return the FechaEvento
     */
    public String getFechaEvento() {
        return FechaEvento;
    }

    /**
     * @return the CodigoPersona
     */
    public Integer getCodigoPersona() {
        return CodigoPersona;
    }

    /**
     * @return the FechaRegistro
     */
    public String getFechaRegistro() {
        return FechaRegistro;
    }

    /**
     * @return the FlagEstado
     */
    public Integer getFlagEstado() {
        return FlagEstado;
    }
}
