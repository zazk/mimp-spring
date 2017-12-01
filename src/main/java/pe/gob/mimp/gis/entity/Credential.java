package pe.gob.mimp.gis.entity;

import java.io.Serializable;

/**
 *
 * @author gian
 */
public class Credential implements Serializable {
    private String login;
    private String clave;

    public Credential() {}

    public Credential(String login, String clave) {
        this.login = login;
        this.clave = clave;
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

    
}
