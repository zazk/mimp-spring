package pe.gob.mimp.gis.dao.impl;
 
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
import pe.gob.mimp.gis.dao.EntidadDao;
import pe.gob.mimp.gis.entity.Entidad;

/**
 *
 * @author gian
 */
@Repository
public class EntidadDaoImpl extends SimpleJdbcDaoSupport implements EntidadDao {

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    @Autowired
    public EntidadDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(Entidad entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Entidad entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Entidad entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entidad> buscarTodos() {

        return getSimpleJdbcTemplate().query(
                "select cod_entidad, nom_entidad,siglas_entidad,dependencia from entidad",
                new BeanPropertyRowMapper<Entidad>(Entidad.class));
    }

    @Override
    public Entidad buscar(String cod_entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entidad> buscarPorNombre(String nom_entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}