package pe.gob.mimp.gis.service;

import pe.gob.mimp.gis.entity.Entidad;
import java.util.List;

public interface EntidadService {
    public Integer insertar(Entidad entidad);
    public void actualizar(Entidad entidad);
    public void eliminar(Entidad entidad);
    public List<Entidad> buscarTodos();
    public Entidad buscar(String cod_entidad);
    public List<Entidad> buscarPorNombre(String nom_entidad);      
}