/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.dao;

import pe.gob.mimp.gis.entity.ServicioAdicional;
import java.util.List;

/**
 *
 * @author Jose Vasquez
 */
public interface ServicioAdicionalDao {
    public Integer insertar(ServicioAdicional servicioadicional);
    public void actualizar(ServicioAdicional servicioadicional);
    public void eliminar(ServicioAdicional servicioadicional);
    public List<ServicioAdicional> buscarTodos();
    public ServicioAdicional buscar(Integer CoServicio);
    public List<ServicioAdicional> buscarPorNombre(String noservicio);
}