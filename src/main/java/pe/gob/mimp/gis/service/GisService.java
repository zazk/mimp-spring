package pe.gob.mimp.gis.service;
 
import java.util.List;
import java.util.Map;

public interface GisService { 
    public List<Map<String,Object>> buscar();    
    public List<Map<String,Object>> consulta( String sql);  
    public int update(String sql, Object... args); 
}