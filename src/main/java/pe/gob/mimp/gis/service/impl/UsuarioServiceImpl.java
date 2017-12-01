package pe.gob.mimp.gis.service.impl;

import pe.gob.mimp.gis.dao.UsuarioDao;
import pe.gob.mimp.gis.dao.impl.UsuarioDaoImpl;
import pe.gob.mimp.gis.entity.Usuario;
import pe.gob.mimp.gis.service.UsuarioService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gian
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);    
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Integer insertar(Usuario usuario) {
        log.info("Creando usuario");
        return usuarioDao.insertar(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        log.info("Actualizando usuario");
        usuarioDao.actualizar(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        log.info("Eliminando usuario");
        usuarioDao.eliminar(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        log.info("Buscando todos los usuarios");
        return usuarioDao.buscarTodos();
    }

    @Override
    public Usuario buscar(Integer CoUsuario) {
        log.info("Buscar usuario por id");
        return usuarioDao.buscar(CoUsuario);
    }

    @Override
    public Boolean autenticar(String login,String clave) {        
        Usuario user = usuarioDao.buscar(login);
        if (user != null) {
            return user.getClave().equals(clave);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Usuario> buscarPorNombre(String nousuario) {
        return usuarioDao.buscarPorNombre(nousuario);
    } 
    
    @Override
    public Usuario buscar(String login,String clave) {
        
        Usuario user = usuarioDao.buscar(login);
        if (user != null && user.getClave().equals(clave) ) {
            return user;
        }
        return null;
    }
    
}
