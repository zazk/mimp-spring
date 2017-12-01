/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.dao.impl;
import pe.gob.mimp.gis.dao.ServicioAdicionalDao;
import pe.gob.mimp.gis.entity.ServicioAdicional;
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
 * @author Jose Vasquez
 */
@Repository
public class ServicioAdicionalDaoImpl extends SimpleJdbcDaoSupport implements ServicioAdicionalDao 
{
  
 private static Logger log = LoggerFactory.getLogger(ServicioAdicionalDaoImpl.class);

    @Autowired
    public ServicioAdicionalDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public Integer insertar(ServicioAdicional servicioadicional) {

        getJdbcTemplate().update(
                "insert into servicioadicional (CoServicio,NoServicio,StCosto,CoPersona,FlServicio) values (?, ?, ?, ?, ?)",
                servicioadicional.getCodServicio(), servicioadicional.getNoServicio(),servicioadicional.getStCosto(), servicioadicional.getCoPersona(),servicioadicional.getFlServicio());
        return getSimpleJdbcTemplate().queryForInt("call identity()");
    }

    @Override
    public void actualizar(ServicioAdicional servicioadicional) {
        getJdbcTemplate().update(
                "update servicioadiciona set NoServicio = ?, StCosto = ?, CoPersona= ?, FlServicio= ?  where CoServicio = ?",
                servicioadicional.getNoServicio(), servicioadicional.getStCosto(),servicioadicional.getCoPersona(), servicioadicional.getFlServicio(),servicioadicional.getCodServicio());
    }

    @Override
    public void eliminar(ServicioAdicional servicioadicional) {
        getJdbcTemplate().update(
                "delete from servicioadicional where CoServicio = ?", servicioadicional.getCodServicio());
    }

    @Override
    public ServicioAdicional buscar(Integer CoServicio) {
        try {
            return getSimpleJdbcTemplate().queryForObject(
                    "select CoServicio, NoServicio, StCosto,CoPersonal,FlServicio from servicioadicional where CoServicio=?",
                    new BeanPropertyRowMapper<ServicioAdicional>(ServicioAdicional.class),CoServicio);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ServicioAdicional> buscarTodos() {
        return getSimpleJdbcTemplate().query(
                "select CoServicio, NoServicio, StCosto,CoPersonal,FlServicio from servicioadicional",
                new BeanPropertyRowMapper<ServicioAdicional>(ServicioAdicional.class));
    }

    @Override
    public List<ServicioAdicional> buscarPorNombre(String noservicio) {
        try {
            Map<String,String> parametros = new HashMap<String,String>();
            parametros.put("NoServicio","%"+noservicio+"%");
            return getSimpleJdbcTemplate().query(
                    "select * from servicioadicional where NoServicio like :noservicio",
                    new BeanPropertyRowMapper<ServicioAdicional>(ServicioAdicional.class),parametros);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }    
    
    
}
