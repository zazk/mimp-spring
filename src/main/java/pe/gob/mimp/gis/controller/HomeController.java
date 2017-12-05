package pe.gob.mimp.gis.controller;
 
import pe.gob.mimp.gis.entity.Usuario;
import pe.gob.mimp.gis.entity.Entidad;
import pe.gob.mimp.gis.service.EntidadService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView; 
import pe.gob.mimp.gis.service.GisService;

/**
 *
 * @author gian
 */
@Controller("homeController")
public class HomeController {
 
    
    @Autowired
    private EntidadService entidadService;
    @Autowired
    private GisService gisService;
    
    private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @RequestMapping("/home/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("home/index");  
        return mav;
    }
     
    
    @RequestMapping(value = "/home/info" )
    public @ResponseBody List<Map<String,Object>> consulta( @RequestParam(value = "q") String q) { 
        
        List<Map<String,Object>> objects = gisService.consulta(q);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */
        System.err.println(q);  
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }
   @RequestMapping(value = "/home/consulta" )
    public @ResponseBody List<Map<String,Object>> info() { 
        
        String sql = "select * from entidad";
        List<Map<String,Object>> objects = gisService.consulta(sql);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }

    @RequestMapping(value = "/home/busqueda", method = RequestMethod.GET )
    public @ResponseBody List<Map<String,Object>> busqueda() { 
        
        List<Map<String,Object>> entidades = gisService.buscar();
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(entidades); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return entidades;
    }

    @RequestMapping(value = "/home/entidades", method = RequestMethod.GET )
    public @ResponseBody List<Entidad> entidades() { 
        
        List<Entidad> entidades = entidadService.buscarTodos();
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */
        for(Entidad e : entidades){
            
            System.err.println(e.getCod_entidad());
            System.err.println(e.getNom_entidad()); 
        }
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return entidades;
    }

    @RequestMapping(value = "/home/new", method = RequestMethod.POST)
    public String createUsuario(@ModelAttribute("usuario")Usuario usuario, SessionStatus status)
    {            
        return "redirect:/home/index";        
    }
    
    @RequestMapping(value = "/home/buscar", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> buscaUsuario(Usuario obj)
    {
        String sql = "select * from usuario Where COUSUARIO = " + obj.getCoUsuario();
        
        System.out.println("SQL: " + sql);
        
        List<Map<String,Object>> objects = gisService.consulta(sql);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }
        
    @RequestMapping(value = "/home/usuarios")
    public @ResponseBody List<Map<String,Object>> usuarios()
    {   
        String sql = "select * from usuario";
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
        
    }
    
    //CRUD   
    @RequestMapping(value = "/home/create", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> create(Usuario obj)
    {        
        String sql;

        if(obj.getCoUsuario() != null)
        {
            int codigo = obj.getCoUsuario();
            sql = "insert into usuario values("+codigo+", 0"+codigo+", 'gian marco" + codigo +"', 'gian"+ codigo +"@hotmail.com', 0, 'carrasco', 0, 'usuario')";
            gisService.update(sql);
        }
                            
        sql = "select * from usuario";
                
        List<Map<String,Object>> objects = gisService.consulta(sql);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }
    
    @RequestMapping(value = "/home/read")
    public @ResponseBody List<Map<String,Object>> read()
    {   
        String sql = "select * from usuario";
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
        
    }
    
    @RequestMapping(value = "/home/update", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> update(Usuario obj)
    {
        String sql;
        
        if(obj.getCoUsuario() != null)
        {        
            int codigo = obj.getCoUsuario();
        
            sql = "update usuario set NOMBRE = 'GianMarco"+codigo+" Modificado' Where COUSUARIO = " + codigo;
            System.out.println("En update - sql: " + sql);
            gisService.update(sql);
        }
        
        sql = "select * from usuario";
                
        List<Map<String,Object>> objects = gisService.consulta(sql);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }
    
    @RequestMapping(value = "/home/delete", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> delete(Usuario obj)
    {
        String sql;
        
        if(obj.getCoUsuario() != null)
        {              
            int codigo = obj.getCoUsuario();
        
            sql = "delete usuario Where COUSUARIO = " + codigo;
            System.out.println("En delete - sql: " + sql);
            gisService.update(sql);
        }
        
        sql = "select * from usuario";
                
        List<Map<String,Object>> objects = gisService.consulta(sql);
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
    }
}     
