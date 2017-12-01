/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.dao;

import java.util.List;
import java.util.Map; 

/**
 *
 * @author zazk
 */
public interface GisDao { 
    public List<Map<String,Object>> buscar();  
    public List<Map<String,Object>> consulta(String sql); 
    public int update(String sql, Object... args); 
}
