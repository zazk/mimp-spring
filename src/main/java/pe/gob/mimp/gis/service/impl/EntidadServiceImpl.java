/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.mimp.gis.dao.EntidadDao;
import pe.gob.mimp.gis.dao.UsuarioDao;
import pe.gob.mimp.gis.dao.impl.UsuarioDaoImpl;
import pe.gob.mimp.gis.entity.Entidad;
import pe.gob.mimp.gis.service.EntidadService;

/**
 *
 * @author zazk
 */
@Service
public class EntidadServiceImpl implements EntidadService{

    private static Logger log = LoggerFactory.getLogger(UsuarioDaoImpl.class);    
    
    @Autowired
    private EntidadDao entidadDao;

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
        
        log.info("Buscando todas las entidades");
        return entidadDao.buscarTodos();
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
