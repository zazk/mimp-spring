/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.dao;

import java.util.List;
import pe.gob.mimp.gis.entity.Entidad; 

/**
 *
 * @author zazk
 */
public interface EntidadDao {
    public Integer insertar(Entidad entidad);
    public void actualizar(Entidad entidad);
    public void eliminar(Entidad entidad);
    public List<Entidad> buscarTodos();
    public Entidad buscar(String cod_entidad); 
    public List<Entidad> buscarPorNombre(String nom_entidad);
    
}
