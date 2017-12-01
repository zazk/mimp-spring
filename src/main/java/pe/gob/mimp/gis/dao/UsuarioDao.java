package pe.gob.mimp.gis.dao;

import pe.gob.mimp.gis.entity.Usuario;
import java.util.List;

/**
 *
 * @author gian
 */
public interface UsuarioDao {
    public Integer insertar(Usuario usuario);
    public void actualizar(Usuario usuario);
    public void eliminar(Usuario usuario);
    public List<Usuario> buscarTodos();
    public Usuario buscar(Integer CoUsuario);
    public Usuario buscar(String login);
    public List<Usuario> buscarPorNombre(String nousuario);
}
