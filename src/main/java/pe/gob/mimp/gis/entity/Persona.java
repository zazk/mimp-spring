package pe.gob.mimp.gis.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Luis Carrillo
 */
@XmlRootElement
public class Persona implements Serializable
{
  private Integer coPersona;
  private String noPersona;
  private String noApellidoPaterno;
  private String noApellidoMaterno;
  private String flSexo;
  private Integer coTipoDocumento;
  private String nuDocumento;
  private Integer coUsuario;
  private Date feNacimiento;
  private String nuTelefono;
  private String flEstado;
   
  private String noUsuario;
  private String login;
  private Integer coTipoPersona;
  private String clave;

    public Integer getCoPersona() {
        return coPersona;
    }

    public void setCoPersona(Integer coPersona) {
        this.coPersona = coPersona;
    }

    public String getNoPersona() {
        return noPersona;
    }

    public void setNoPersona(String noPersona) {
        this.noPersona = noPersona;
    }

    public String getNoApellidoPaterno() {
        return noApellidoPaterno;
    }

    public void setNoApellidoPaterno(String noApellidoPaterno) {
        this.noApellidoPaterno = noApellidoPaterno;
    }

    public String getNoApellidoMaterno() {
        return noApellidoMaterno;
    }

    public void setNoApellidoMaterno(String noApellidoMaterno) {
        this.noApellidoMaterno = noApellidoMaterno;
    }

    public String getFlSexo() {
        return flSexo;
    }

    public void setFlSexo(String flSexo) {
        this.flSexo = flSexo;
    }

    public Integer getCoTipoDocumento() {
        return coTipoDocumento;
    }

    public void setCoTipoDocumento(Integer coTipoDocumento) {
        this.coTipoDocumento = coTipoDocumento;
    }

    public String getNuDocumento() {
        return nuDocumento;
    }

    public void setNuDocumento(String nuDocumento) {
        this.nuDocumento = nuDocumento;
    }

    public Integer getCoUsuario() {
        return coUsuario;
    }

    public void setCoUsuario(Integer coUsuario) {
        this.coUsuario = coUsuario;
    }

    public Date getFeNacimiento() {
        return feNacimiento;
    }

    public void setFeNacimiento(Date feNacimiento) {
        this.feNacimiento = feNacimiento;
    }

    public String getNuTelefono() {
        return nuTelefono;
    }

    public void setNuTelefono(String nuTelefono) {
        this.nuTelefono = nuTelefono;
    }

    public String getFlEstado() {
        return flEstado;
    }

    public void setFlEstado(String flEstado) {
        this.flEstado = flEstado;
    }

    public String getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(String noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getCoTipoPersona() {
        return coTipoPersona;
    }

    public void setCoTipoPersona(Integer coTipoPersona) {
        this.coTipoPersona = coTipoPersona;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
  
    
}
