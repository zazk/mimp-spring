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
    
    /* ************** Gian Marco (SSP) ********************** */    
    //Usuarios
    @RequestMapping(value = "/home/logininterno", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> logininterno(HttpServletRequest request)
    {                        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        String [] campos = {"usuario", "password"};
        
        List<Map<String,Object>> empleados = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(empleados.isEmpty())
        {
            String sql;
            
            sql = "SELECT * FROM USUARIOS WHERE USUARIO = '{0}' AND PASSWORD = '{1}' AND ROWNUM = 1";
            sql = sql.replace("{0}", usuario);
            sql = sql.replace("{1}", password);
            
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
    
        return empleados;
    }
    
    @RequestMapping(value = "/home/create", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> create(HttpServletRequest request)
    {                        
        String idEmpleado = request.getParameter("idEmpleado");
        String idRolUser = request.getParameter("idRolUser");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String estado = request.getParameter("estado");
        
        String [] campos = {"idEmpleado", "idRolUser", "usuario", "password", "estado"};
        
        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(objects.isEmpty())
        {
            String sql;
            int _idEmpleado = Integer.parseInt(idEmpleado);
            int _idRolUser = Integer.parseInt(idRolUser);
            int _estado = Integer.parseInt(estado);
            
            sql = "INSERT INTO usuarios VALUES (usuarios_seq.NEXTVAL,?,?,?,?,?)";
            
            int rpta = gisService.update(sql, _idEmpleado, _idRolUser, usuario, password, _estado);
                        
            error.put("error", false);
            error.put("mensaje", "Se creó " + rpta + " registro(s).");       
            objects.add(error);
        }
    
        return objects;
    }
    
    @RequestMapping(value = "/home/read")
    public @ResponseBody List<Map<String,Object>> read()
    {   
        String sql =    "SELECT " +
                            "u.IDUSUARIOS as IDUSUARIOS, " +
                            "e.IDEMPLEADO, e.NOMBRE || ', ' || e.APELLIDO_P || ' ' || e.APELLIDO_M AS EMPLEADO, " +
                            "de.descripcion AS DEPENDENCIA, " +
                            "r.DESCRIPCION AS ROL, " +
                            "u.USUARIO, " +
                            "u.PASSWORD, " +
                            "est.ESTADONOMBRE AS ESTADO " +
                        "FROM USUARIOS u " +
                        "INNER JOIN EMPLEADO e ON u.idEmpleado = e.idEmpleado " +
                        "INNER JOIN ROLUSER r ON u.idRoluser = r.idRol INNER JOIN ESTADO est ON u.ESTADO = est.IDESTADO " +
                        "INNER JOIN UORGANICA uor ON u.idEmpleado = uor.idEmpleado " +
                        "INNER JOIN DEPENDENCIACATALOGO de ON uor.UOrganica_catalogo = de.IDCATALOGODEPEND";
        
        System.out.println("--------------------------------------------- READ - SQL: " + sql);
        
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
        String idUsuarios = request.getParameter("idUsuarios");
        String idEmpleado = request.getParameter("idEmpleado");
        String idRolUser = request.getParameter("idRolUser");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String estado = request.getParameter("estado");
        
        String [] campos = {"idUsuarios", "idEmpleado", "idRolUser", "usuario", "password", "estado"};
        
        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(objects.isEmpty())
        {
            String sql;
            int _idUsuarios = Integer.parseInt(idUsuarios);
            int _idEmpleado = Integer.parseInt(idEmpleado);
            int _idRolUser = Integer.parseInt(idRolUser);
            int _estado = Integer.parseInt(estado);
            
            sql =   "UPDATE USUARIOS " +                    
                        "SET IDEMPLEADO = ?, " +
                             "IDROLUSER = ?, " +
                             "USUARIO = ?, " +
                             "PASSWORD = ?, " +
                             "ESTADO = ?" +
                    "WHERE IDUSUARIOS = ?";
                                    
            int rpta = gisService.update(sql, _idEmpleado, _idRolUser, usuario, password, _estado, _idUsuarios);
                        
            error.put("error", false);
            error.put("mensaje", "Se actualizó " + rpta + " registro(s).");
            objects.add(error);
        }

        return objects;
    }
    
    @RequestMapping(value = "/home/delete", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> delete(HttpServletRequest request)
    {
        String idUsuarios = request.getParameter("idUsuarios");
        
        String [] campos = {"idUsuarios"};
        
        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(objects.isEmpty())
        {
            String sql;
            int _idUsuarios = Integer.parseInt(idUsuarios);
            
            sql = "DELETE usuarios WHERE idUsuarios = ?";
                                    
            int rpta = gisService.update(sql, _idUsuarios);
                        
            error.put("error", false);
            error.put("mensaje", "Se eliminó " + rpta + " registro(s).");      
            objects.add(error);
        }
  
        return objects;
    }

    //Candidatos
    @RequestMapping(value = "/home/loginexterno", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> loginexterno(HttpServletRequest request)
    {                        
        String correo = request.getParameter("correo_usuario");
        String clave = request.getParameter("clave_usuario");
        
        String [] campos = {"correo_usuario", "clave_usuario"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql;
            
            sql = "SELECT * FROM candidato WHERE CORREO_USUARIO = '{0}' AND CLAVE_USUARIO = '{1}' AND ROWNUM = 1";
            sql = sql.replace("{0}", correo);
            sql = sql.replace("{1}", clave);
            
            System.out.println("------------------ Consulta sql: " + sql);
            candidatos = gisService.consulta(sql);
            System.out.println("------------------ Tamaño de lista: " + candidatos.size());
            if(candidatos.size() > 0)
            {
                Map<String,Object> candidato = candidatos.get(0);
                request.getSession().setAttribute("candidato", candidato);

                candidatos = new ArrayList<Map<String, Object>>();
                error.put("error", false);
                error.put("mensaje", "Acceso autorizado");
                candidatos.add(error);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "Usuario y/o contraseña incorrectos");
                candidatos.add(error);
            }            
        }
   
        return candidatos;
    }
    
    @RequestMapping(value = "/home/registrarcandidato", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> registrarcandidato(HttpServletRequest request)
    {        
        String idTipoIdentidad = request.getParameter("idTipoIdentidad");
        String nroIdentidad = request.getParameter("nroIdentidad");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo_usuario = request.getParameter("correo_usuario");
        String clave_usuario = request.getParameter("clave_usuario");
     
        String [] campos = {"idTipoIdentidad", "nroIdentidad", "nombres", "apellidos", "correo_usuario", "clave_usuario"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql;
            int _idTipoIdentidad = Integer.parseInt(idTipoIdentidad);
            
            sql = "INSERT INTO CANDIDATO(idCandidato, IDTIPOINDENTIDAD, NROIDENTIIDAD, NOMBRES, AP_PATERNO, CORREO_USUARIO, CLAVE_USUARIO) " +                    
                   "VALUES(candidato_seq.nextval,?,?,?,?,?,?)";
            
            int rpta = gisService.update(sql, _idTipoIdentidad, nroIdentidad, nombres, apellidos, correo_usuario, clave_usuario);
                        
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se creó " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se crearon registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/buscarcandidato")
    public @ResponseBody List<Map<String,Object>> buscarcandidato(HttpServletRequest request)
    {   
        String idCandidato = request.getParameter("idCandidato");        
        
        String [] campos = {"idCandidato"};    
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql =    "SELECT " +
                                "c.*, " +
                                "t.DESCRIPCION, " +
                                "e.NOMBRE as ESTADOCIVIL " +
                            "FROM candidato c " +
                            "LEFT JOIN tipoidentidad t ON c.IDTIPOInDENTIDAD = t.IDTIPOIDENTIDAD " +
                            "LEFT JOIN estadocivil e ON c.idEstadoCivil = e.idEstadoCivil " +
                            "WHERE c.idCandidato = {0} and rownum = 1";

            sql = sql.replace("{0}", idCandidato);

            System.out.println("--------------------------------------------- BUSCAR - SQL: " + sql);        
            candidatos = gisService.consulta(sql);
        
            if(candidatos.size() > 0)
            {
                Map<String,Object> candidato = candidatos.get(0);
                request.getSession().setAttribute("candidato", candidato);

                candidatos = new ArrayList<Map<String, Object>>();
                String mensaje = candidato.get("NOMBRES").toString() + ' ' + candidato.get("AP_PATERNO").toString();
                error.put("error", false);
                error.put("mensaje", mensaje);
                candidatos.add(error);
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "Candidato con id: '" + idCandidato + "' no encontrado.");
                candidatos.add(error);
            }
        }
        return candidatos;
    }
    
    @RequestMapping(value = "/home/actualizarcandidato")
    public @ResponseBody List<Map<String,Object>> actualizarcandidato(HttpServletRequest request)
    {   
        String idCandidato = request.getParameter("idCandidato");    
        
        String [] campos = {"idCandidato"};    
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql =    "SELECT " +
                                "c.*, " +
                                "t.DESCRIPCION, " +
                                "e.NOMBRE as ESTADOCIVIL " +
                            "FROM candidato c " +
                            "LEFT JOIN tipoidentidad t ON c.IDTIPOInDENTIDAD = t.IDTIPOIDENTIDAD " +
                            "LEFT JOIN estadocivil e ON c.idEstadoCivil = e.idEstadoCivil " +
                            "WHERE c.idCandidato = {0} and rownum = 1";

            sql = sql.replace("{0}", idCandidato);

            System.out.println("--------------------------------------------- BUSCAR - SQL: " + sql);        
            candidatos = gisService.consulta(sql);
        
            if(candidatos.size() > 0)
            {
                Map<String,Object> candidato = candidatos.get(0);
                request.getSession().setAttribute("candidato", candidato);

                candidatos = new ArrayList<Map<String, Object>>();
                String mensaje = candidato.get("NOMBRES").toString() + ' ' + candidato.get("AP_PATERNO").toString();
                error.put("error", false);
                error.put("mensaje", mensaje);
                candidatos.add(error);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "idCandidato no está registrado");
                candidatos.add(error);
            }
        }
        
        return candidatos;
    }
    
    //Métodos
    ArrayList<Map<String, Object>> hayCamposVacios(HttpServletRequest request, String... parametros)
    {
        ArrayList<Map<String, Object>> camposvacios = new ArrayList<Map<String, Object>>();
        String mensaje = "";
        String contenido;
        
        for(String x:parametros)
        {
            contenido = request.getParameter(x);
            
            if(contenido.trim().isEmpty())
                mensaje += " Campo " + x + " vacío,";            
        }
        
        if(!mensaje.isEmpty())
        {
            //Se elimina la última coma
            mensaje = mensaje.substring(0, mensaje.lastIndexOf(','));
            
            Map campovacio = new HashMap();
            campovacio.put("error", true);
            campovacio.put("mensaje", mensaje);
            camposvacios.add(campovacio);
        }                        
        
        return camposvacios;
    }
}     
