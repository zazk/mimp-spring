package pe.gob.mimp.gis.service.impl;

import pe.gob.mimp.gis.dao.impl.UsuarioDaoImpl;
import pe.gob.mimp.gis.entity.Usuario;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mimp.gis.dao.GisDao;
import pe.gob.mimp.gis.service.GisService;

/**
 *
 * @author gian
 *
 */
@Service
public class GisServiceImpl implements GisService {

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);    
    
    @Autowired
    private GisDao gisDao;
 

    @Override
    public List<Map<String,Object>> buscar() {
        log.info("Buscando todos los registros");
        return gisDao.buscar();
    }
 
    @Override
    public List<Map<String,Object>> consulta( String sql) {
        log.info("Buscando todos los registros");
        return gisDao.consulta(sql);
    }
    
    @Override
    public int update(String sql, Object... args) {
        log.info("Actualizando registros");
        return gisDao.update(sql, args);
    }
    
}
