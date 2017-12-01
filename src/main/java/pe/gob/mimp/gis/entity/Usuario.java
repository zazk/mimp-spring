package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gian
 */
@XmlRootElement
public class Usuario implements Serializable {
    
    private Integer CoUsuario;
    private String nousuario;
    private String nombre;
    private String login;
    private Integer CoTipoPersona;
    private String clave;
    private Integer FlEstado;
    private String tipo;

    public Usuario() {}
    
    public Usuario(Integer CoUsuario, String nousuario, String login,Integer CoTipoPersona,
                   String clave, Integer FlEstado, String tipo  ) {
        this.CoUsuario=CoUsuario;
        this.nousuario = nousuario;
        this.login = login;
        this.CoTipoPersona =CoTipoPersona;
        this.clave = clave;
        this.FlEstado = FlEstado;
        this.tipo = tipo;
    }

    public Usuario(String nousuario, String login,Integer CoTipoPersona,String clave,Integer FlEstado) {
        this.nousuario = nousuario;
        this.login = login;
        this.CoTipoPersona = CoTipoPersona;
        this.clave = clave;
        this.FlEstado = FlEstado;
    }
    
    public Usuario(String nousuario,String login) {
        this.nousuario = nousuario;
        this.login = login;
    }

    /**
     * @return the CoUsuario
     */
    public Integer getCoUsuario() {
        return CoUsuario;
    }

    /**
     * @param CoUsuario the CoUsuario to set
     */
    public void setCoUsuario(Integer CoUsuario) {
        this.CoUsuario = CoUsuario;
    }

    /**
     * @return the NoUsuario
     */
    public String getNoUsuario() {
        return nousuario;
    }

    /**
     * @param NoUsuario the NoUsuario to set
     */
    public void setNoUsuario(String nousuario) {
        this.nousuario = nousuario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the CoTipoPersona
     */
    public Integer getCoTipoPersona() {
        return CoTipoPersona;
    }

    /**
     * @param CoTipoPersona the CoTipoPersona to set
     */
    public void setCoTipoPersona(Integer CoTipoPersona) {
        this.CoTipoPersona = CoTipoPersona;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
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

    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
}