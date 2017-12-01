package pe.gob.mimp.gis.dao.impl;
 
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mimp.gis.dao.GisDao;
import pe.gob.mimp.gis.entity.Entidad;

/**
 *
 * @author gian
 */
@Repository
public class GisDaoImpl extends SimpleJdbcDaoSupport implements GisDao {

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    @Autowired
    public GisDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }
 
    
    @Override
    public List<Map<String,Object>> buscar() {

        return getSimpleJdbcTemplate().queryForList(
                "select * from entidad" );
    }
 
    @Override
    public List<Map<String,Object>> consulta(String sql) {

        return getSimpleJdbcTemplate().queryForList(
                sql );
    }
    
    @Override
    public int update(String sql, Object... args) {
        int ret = 0;
        //return ret;
        
        return getSimpleJdbcTemplate().update(sql, args);
        
    }
 

}