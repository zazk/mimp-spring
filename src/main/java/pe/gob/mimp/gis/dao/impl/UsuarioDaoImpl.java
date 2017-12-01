package pe.gob.mimp.gis.dao.impl;

import pe.gob.mimp.gis.dao.UsuarioDao;
import pe.gob.mimp.gis.entity.Usuario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gian
 */
@Repository
public class UsuarioDaoImpl extends SimpleJdbcDaoSupport implements UsuarioDao {

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    @Autowired
    public UsuarioDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Usuario usuario) {

        getJdbcTemplate().update(
                "insert into usuarios (NoUsuario,Login,CoTipoPersona,Clave,FlEstado) values (?, ?, ?, ?, ?)",
                usuario.getNoUsuario(), usuario.getLogin(),usuario.getCoTipoPersona(), usuario.getClave(),usuario.getFlEstado());
        return getSimpleJdbcTemplate().queryForInt("call identity()");
    }

    @Override
    public void actualizar(Usuario usuario) {
        getJdbcTemplate().update(
                "update usuarios set nousuario = ?, login = ?, cotipopersona= ?, clave= ?, flestado= ? where cousuario = ?",
                usuario.getNoUsuario(), usuario.getLogin(),usuario.getCoTipoPersona(), usuario.getClave(),usuario.getFlEstado(),usuario.getCoUsuario());
    }

    @Override
    public void eliminar(Usuario usuario) {
        getJdbcTemplate().update(
                "delete from usuarios where CoUsuario = ?", usuario.getCoUsuario());
    }

    @Override
    public Usuario buscar(Integer CoUsuario) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select CoUsuario, NoUsuario, login,CoTipoPersona,clave,FlEstado from usuarios where CoUsuario=?",
                    new BeanPropertyRowMapper<Usuario>(Usuario.class),CoUsuario);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select nombre, login,clave,tipo from usuarios",
                new BeanPropertyRowMapper<Usuario>(Usuario.class));
    }

    @Override
    public Usuario buscar(String login) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select  login,clave,tipo from usuarios where login=?",
                    new BeanPropertyRowMapper<Usuario>(Usuario.class), login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Usuario> buscarPorNombre(String nousuario) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("nombre","%"+nousuario+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from usuarios where NoUsuario like :nombre",
                    new BeanPropertyRowMapper<Usuario>(Usuario.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}