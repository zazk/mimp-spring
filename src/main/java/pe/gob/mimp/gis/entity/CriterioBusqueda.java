package pe.gob.mimp.gis.entity;

/**
 *
 * @author gian
 */
public class CriterioBusqueda {
    private String NoUsuario;
    private String NoServicio;
    private String NoLocal;

    /**
     * @return the NoUsuario
     */
    public String getNoUsuario() {
        return NoUsuario;
    }

    /**
     * @param NoUsuario the NoUsuario to set
     */
    public void setNoUsuario(String NoUsuario) {
        this.NoUsuario = NoUsuario;
    }

    public String getNoServicio() {
        return NoServicio;
    }
    
    public void setNoServicio(String NoServicio) {
        this.NoServicio = NoServicio;
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
    
}
