package pe.gob.mimp.gis.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import pe.gob.mimp.gis.entity.Usuario;
import pe.gob.mimp.gis.entity.Entidad;
import pe.gob.mimp.gis.service.EntidadService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
    
    //Gian Marco (SSP)
    @RequestMapping(value = "/home/logininterno", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> logininterno(HttpServletRequest request)
    {                        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        List<Map<String,Object>> empleados = new ArrayList<Map<String, Object>>();
        Map error = new HashMap();
        
        if(!hayCamposVacios(usuario, password))
        {
            String sql;
            
            sql = "SELECT * FROM USUARIOS WHERE USUARIO = '{0}' AND PASSWORD = '{1}' AND ROWNUM = 1";
            sql = sql.replace("{0}", usuario);
            sql = sql.replace("{1}", password.toString());
            
            System.out.println("------------------ Consulta sql: " + sql);
            empleados = gisService.consulta(sql);
            System.out.println("------------------ Tamaño de lista: " + empleados.size());
            if(empleados.size() > 0)
            {
                Map<String,Object> empleado = empleados.get(0);
                request.getSession().setAttribute("empleado", empleado);

                empleados = new ArrayList<Map<String, Object>>();
                error.put("error", false);
                error.put("mensaje", "Acceso autorizado");
                empleados.add(error);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "Usuario y/o contraseña incorrectos");
                empleados.add(error);
            }            
        }
        else
        {
            error.put("error", true);
            error.put("mensaje", "Datos incompletos");
            empleados.add(error);
        }        
        return empleados;
    }
    
    @RequestMapping(value = "/home/create", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> create(HttpServletRequest request)
    {                        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");
        String idDependencia = request.getParameter("idDependencia");
        String idDocumento = request.getParameter("idDocumento");
        String num_documento = request.getParameter("num_documento");
        String estado = request.getParameter("estado");
        
        List<Map<String,Object>> objects = new ArrayList<Map<String, Object>>();
        Map error = new HashMap();
        
        if(!hayCamposVacios(nombre, apellidos, correo, clave, rol, idDependencia, idDocumento, num_documento, estado))
        {
            String sql;
            int _rol = Integer.parseInt(rol);
            int _idDependencia = Integer.parseInt(idDependencia);
            int _idDocumento = Integer.parseInt(idDocumento);
            
            sql = "INSERT INTO usuario VALUES (usuario_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";            
            
            int rpta = gisService.update(sql, nombre, apellidos, correo, clave, _rol, _idDependencia, _idDocumento, num_documento, estado);
                        
            error.put("error", false);
            error.put("mensaje", "Se creó " + rpta + " registro(s).");       
            objects.add(error);
        }
        else
        {
            error.put("error", true);
            error.put("mensaje", "Datos incompletos");
            objects.add(error);
        }        
        return objects;
    }
    
    @RequestMapping(value = "/home/read")
    public @ResponseBody List<Map<String,Object>> read()
    {   
        String sql =    "SELECT " +
                            "u.IDUSUARIOS as IDUSUARIO, " +
                            "e.IDEMPLEADO, e.NOMBRE || ', ' || e.APELLIDO_P || ' ' || e.APELLIDO_M AS EMPLEADO, " +
                            "r.DESCRIPCION AS ROL, " +
                            "u.USUARIO, " +
                            "u.PASSWORD, " +
                            "est.ESTADONOMBRE AS ESTADO " +
                            "FROM USUARIOS u " +
                            "INNER JOIN EMPLEADO e ON u.idEmpleado = e.idEmpleado " +
                            "INNER JOIN ROLUSER r ON u.idRoluser = r.idRol INNER JOIN ESTADO est ON u.ESTADO = est.IDESTADO";
        
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;
        
    }
    
    @RequestMapping(value = "/home/update", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> update(HttpServletRequest request)
    {
        String idUsuario = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");
        String idDependencia = request.getParameter("idDependencia");
        String idDocumento = request.getParameter("idDocumento");
        String num_documento = request.getParameter("num_documento");
        String estado = request.getParameter("estado");
        
        List<Map<String,Object>> objects = new ArrayList<Map<String, Object>>();
        Map error = new HashMap();
        
        if(!hayCamposVacios(idUsuario, nombre, apellidos, correo, clave, rol, idDependencia, idDocumento, num_documento, estado))
        {
            String sql;
            int _idUsuario = Integer.parseInt(idUsuario);
            int _rol = Integer.parseInt(rol);
            int _idDependencia = Integer.parseInt(idDependencia);
            int _idDocumento = Integer.parseInt(idDocumento);
            
            sql = "UPDATE USUARIO "
                    + "SET nombre = ?, apellidos = ?, correo = ?, clave = ?, rol = ?, idDependencia = ?, idDocumento = ?, num_documento = ?, estado = ? "
                    + "WHERE idUsuario = ?";
                                    
            int rpta = gisService.update(sql, nombre, apellidos, correo, clave, _rol, _idDependencia, _idDocumento, num_documento, estado, _idUsuario);
                        
            error.put("error", false);
            error.put("mensaje", "Se actualizó " + rpta + " registro(s).");
            objects.add(error);
        }
        else
        {
            error.put("error", true);
            error.put("mensaje", "Datos incompletos");
            objects.add(error);
        }        
        return objects;
    }
    
    @RequestMapping(value = "/home/delete", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> delete(HttpServletRequest request)
    {
        String idUsuario = request.getParameter("idUsuario");
        
        List<Map<String,Object>> objects = new ArrayList<Map<String, Object>>();
        Map error = new HashMap();
        
        if(!hayCamposVacios(idUsuario))
        {
            String sql;
            int _idUsuario = Integer.parseInt(idUsuario);
            
            sql = "DELETE usuario WHERE idUsuario = ?";
                                    
            int rpta = gisService.update(sql, _idUsuario);
                        
            error.put("error", false);
            error.put("mensaje", "Se eliminó " + rpta + " registro(s).");      
            objects.add(error);
        }
        else
        {
            error.put("error", true);
            error.put("mensaje", "Datos incompletos");
            objects.add(error);
        }        
        return objects;
    }

    boolean hayCamposVacios(String... campos)
    {
        for(String x:campos)
            if(x.isEmpty())
                return true;
        
        return false;
    }
}     
