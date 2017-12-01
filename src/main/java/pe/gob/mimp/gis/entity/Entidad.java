/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HEspirituM
 */
@XmlRootElement
public class Entidad implements Serializable  {
    
    private String cod_entidad;
    private String nom_entidad;
    private String siglas_entidad;
    private String dependencia;
    
    public Entidad(){}

    public String getCod_entidad() {
        return cod_entidad;
    }

    public void setCod_entidad(String cod_entidad) {
        this.cod_entidad = cod_entidad;
    }

    public String getNom_entidad() {
        return nom_entidad;
    }

    public void setNom_entidad(String nom_entidad) {
        this.nom_entidad = nom_entidad;
    }

    public String getSiglas_entidad() {
        return siglas_entidad;
    }

    public void setSiglas_entidad(String siglas_entidad) {
        this.siglas_entidad = siglas_entidad;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }
     
 
}
