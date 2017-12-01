package pe.gob.mimp.gis.service;

import pe.gob.mimp.gis.entity.Usuario;
import java.util.List;

public interface UsuarioService {
    public Integer insertar(Usuario usuario);
    public void actualizar(Usuario usuario);
    public void eliminar(Usuario usuario);
    public List<Usuario> buscarTodos();
    public Usuario buscar(Integer CoUsuario);
    public List<Usuario> buscarPorNombre(String nousuario);
    public Boolean autenticar(String login,String clave);
    public Usuario buscar(String login,String clave);    
}