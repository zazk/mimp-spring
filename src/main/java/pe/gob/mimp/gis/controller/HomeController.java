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
    @RequestMapping(value = "/home/logininterno", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> logininterno(HttpServletRequest request)
    {                        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        
        String [] campos = {"usuario", "password"};
        
        List<Map<String,Object>> empleados = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(empleados.get(0).get("error").toString());        
        
        if(!hayError)
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
                error.put("error", true);
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
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            objects = new ArrayList<Map<String,Object>>();
            String sql;
            int _idEmpleado = Integer.parseInt(idEmpleado);
            int _idRolUser = Integer.parseInt(idRolUser);
            int _estado = Integer.parseInt(estado);
            
            sql = "INSERT INTO usuarios VALUES (usuarios_seq.NEXTVAL,?,?,?,?,?)";
            
            int rpta = gisService.update(sql, _idEmpleado, _idRolUser, usuario, password, _estado);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se creó " + rpta + " registro(s).");       
                objects.add(error);
            }   
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se crearon registros.");       
                objects.add(error);
            }
        }
    
        return objects;
    }
    
    @RequestMapping(value = "/home/read", method = RequestMethod.GET)
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
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            objects = new ArrayList<Map<String,Object>>();
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
                        
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizó " + rpta + " registro(s).");
                objects.add(error);
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El _idUsuario "+ _idUsuarios +" no existe. No se actualizaron registros.");
                objects.add(error);
            }
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
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            objects = new ArrayList<Map<String,Object>>();
            String sql;
            int _idUsuarios = Integer.parseInt(idUsuarios);
            
            sql = "DELETE usuarios WHERE idUsuarios = ?";
                                    
            int rpta = gisService.update(sql, _idUsuarios);
                        
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminó " + rpta + " registro(s).");      
                objects.add(error);
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idUsuario "+ idUsuarios +" no existe. No se eliminaron registros.");
                objects.add(error);
            }            
        }
  
        return objects;
    }

    //Candidatos
    @RequestMapping(value = "/home/loginexterno", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> loginexterno(HttpServletRequest request)
    {                        
        String correo = request.getParameter("correo_usuario");
        String clave = request.getParameter("clave_usuario");
        
        String [] campos = {"correo_usuario", "clave_usuario"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
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
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;
            int _idTipoIdentidad = Integer.parseInt(idTipoIdentidad);
            
            sql = "INSERT INTO CANDIDATO(idCandidato, IDTIPOIDENTIDAD, NROIDENTIDAD, NOMBRES, AP_PATERNO, CORREO_USUARIO, CLAVE_USUARIO) " +                    
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
    
    @RequestMapping(value = "/home/buscarcandidato", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> buscarcandidato(HttpServletRequest request)
    {   
        String idCandidato = request.getParameter("idCandidato");        
        
        String [] campos = {"idCandidato"};    
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            String sql =    "SELECT " +
                                "c.*, " +
                                "t.DESCRIPCION, " +
                                "e.NOMBRE as ESTADOCIVIL " +
                            "FROM candidato c " +
                            "LEFT JOIN tipoidentidad t ON c.IDTIPOIDENTIDAD = t.IDTIPOIDENTIDAD " +
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
    
    @RequestMapping(value = "/home/actualizarcandidato", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> actualizarcandidato(HttpServletRequest request)
    {           
        String idCandidato = request.getParameter("idCandidato");
        String idTipoIdentidad = request.getParameter("idTipoIdentidad");
        String nroIdentidad = request.getParameter("nroIdentidad");
        String ap_paterno = request.getParameter("ap_paterno");
        String ap_materno = request.getParameter("ap_materno");
        String nombres = request.getParameter("nombres");
        String idEstadoCivil = request.getParameter("idEstadoCivil");
        String sexo = request.getParameter("sexo");
        String fe_nacimiento = request.getParameter("fe_nacimiento");
        String direccion_ca = request.getParameter("direccion_ca");
        //String direccion_nro = request.getParameter("direccion_nro");
        String idDistrito = request.getParameter("idDistrito");
        String ruc = request.getParameter("ruc");
        //String brevete_nro = request.getParameter("brevete_nro");
        //String brevete_cat = request.getParameter("brevete_cat");
        String correo_usuario = request.getParameter("correo_usuario");
        //String clave_usuario = request.getParameter("clave_usuario");
        String tel_fijo = request.getParameter("tel_fijo");
        String tel_celu = request.getParameter("tel_celu");
        String lic_FFAA = request.getParameter("lic_FFAA");
        String discapac = request.getParameter("discapac");
        //String idNacionalidad = request.getParameter("idNacionalidad");
        //String fotoRuta = request.getParameter("fotoRuta");
        
        String [] campos = {"idCandidato","idTipoIdentidad","nroIdentidad","ap_paterno","ap_materno","nombres","idEstadoCivil",
                            "sexo","fe_nacimiento","direccion_ca","idDistrito","ruc","correo_usuario","tel_fijo","tel_celu",
                            "lic_FFAA","discapac"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String fecha[] = fe_nacimiento.split("-");
            fe_nacimiento = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
            
            String sql;
       
            sql =   "UPDATE candidato " +            
                     "SET    idTipoIdentidad = {0}, nroIdentidad = '{1}', ap_paterno = '{2}', ap_materno = '{3}', "+
                            "nombres = '{4}', idEstadoCivil = {5}, sexo = '{6}', fe_nacimiento = '{7}', "+
                            "direccion_ca = '{8}', idDistrito = {9}, ruc = '{10}', "+
                            "correo_usuario = '{11}', tel_fijo = '{12}', " +
                            "tel_celu = '{13}', lic_FFAA = '{14}', discapac = '{15}' "+
                    "WHERE idCandidato = {16}";
                                    
            sql = sql.replace("{0}", idTipoIdentidad);
            sql = sql.replace("{1}", nroIdentidad);
            sql = sql.replace("{2}", ap_paterno);
            sql = sql.replace("{3}", ap_materno);
            sql = sql.replace("{4}", nombres);
            sql = sql.replace("{5}", idEstadoCivil);
            sql = sql.replace("{6}", sexo);
            sql = sql.replace("{7}", fe_nacimiento);
            sql = sql.replace("{8}", direccion_ca);
            sql = sql.replace("{9}", idDistrito);
            sql = sql.replace("{10}", ruc);
            sql = sql.replace("{11}", correo_usuario);
            sql = sql.replace("{12}", tel_fijo);
            sql = sql.replace("{13}", tel_celu);
            sql = sql.replace("{14}", lic_FFAA);
            sql = sql.replace("{15}", discapac);
            sql = sql.replace("{16}", idCandidato);                        

            System.out.println("----------------------------------------------------- UPDATE sql: " + sql);
            
            int rpta = gisService.update(sql);
                        
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizó " + rpta + " registro(s).");
                candidatos.add(error);
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idCandidato "+ idCandidato +" no existe. No se actualizaron registros.");
                candidatos.add(error);
            }            
        }

        return candidatos;
    }
    
    //Reg_perfil
    @RequestMapping(value = "/home/readOrgano", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readOrgano()
    {   
        String sql =    "SELECT * FROM ORGANO";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readUOrganica", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readUOrganica()
    {   
        String sql =    "SELECT u.*, d.DESCRIPCION " +
                        "FROM uorganica u " +
                        "INNER JOIN dependenciacatalogo d ON u.UORGANICA_CATALOGO = d.IDCATALOGODEPEND";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/createRequisionPuesto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createRequisionPuesto(HttpServletRequest request)
    {        
        String idUOrganica = request.getParameter("idUOrganica");
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String montoMes = request.getParameter("montoMes");
        String nroPuestos = request.getParameter("nroPuestos");
        String fe_inicio = request.getParameter("fe_inicio");
        String fe_termino = request.getParameter("fe_termino");
        String nacPeruana = request.getParameter("nacPeruana");
        String sustentoNac = request.getParameter("sustentoNac");
        String usu_crea = request.getParameter("usu_crea");        
        String idThorarios = request.getParameter("idThorarios");
        String tipoRequerimiento = request.getParameter("tipoRequerimiento");
             
        String [] campos = {"idUOrganica","idPerfilPuesto","montoMes","nroPuestos","fe_inicio","fe_termino","nacPeruana","usu_crea","idThorarios", "tipoRequerimiento"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;
            int _idUOrganica = Integer.parseInt(idUOrganica);
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            double _montoMes = Double.parseDouble(montoMes);
            int _nroPuestos = Integer.parseInt(nroPuestos);
            int _usu_crea = Integer.parseInt(usu_crea);
            int _idThorarios = Integer.parseInt(idThorarios);            
            
            String fecha[];
            fecha = fe_inicio.split("-");
            fe_inicio = fecha[2] + "-" + fecha[1] + "-" + fecha[0];            
            
            fecha = fe_termino.split("-");
            fe_termino = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
            
            sql = "INSERT INTO REQUISIONPUESTO VALUES(requisionPuesto_seq.nextval, ?, ?, ?, ?, 6, ?, ?, ?, ?, sysdate, ?, ?, ?)";
            
            int rpta = gisService.update(sql, _idUOrganica, _idPerfilPuesto, _montoMes, _nroPuestos, fe_inicio, fe_termino, nacPeruana, sustentoNac, _usu_crea, _idThorarios, tipoRequerimiento);
                        
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
    
    @RequestMapping(value = "/home/createPuesto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createPuesto(HttpServletRequest request)
    {   
        String nomPuesto = request.getParameter("nomPuesto");
        String misionPuesto = request.getParameter("misionPuesto");
        String coord_Externa = request.getParameter("coord_Externa");
        String coord_Interna = request.getParameter("coord_Interna");        
             
        String [] campos = {"nomPuesto","misionPuesto","coord_Externa","coord_Interna"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;

            sql = "INSERT INTO PERFILPUESTO VALUES(perfilPuesto_seq.nextval,?,?,?,?,?)";
            
            int rpta = gisService.update(sql, nomPuesto, misionPuesto, coord_Externa, coord_Interna, 22);            
            
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
    
    @RequestMapping(value = "/home/readPuesto", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readPuesto()
    {   
        String sql =    "SELECT * FROM PERFILPUESTO";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readPuestoId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readPuestoId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            sql = "SELECT * FROM PERFILPUESTO Where idPerfilPuesto = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró Perfil con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");
                
            }
            
            objects.add(error);
            
            return objects;        
        }

        return objects;
    }
    
    @RequestMapping(value = "/home/createFuncionPuesto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createfuncionpuesto(HttpServletRequest request)
    {   
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String descripcionPuesto = request.getParameter("descripcionPuesto");        
        String valFrecuencia_F = request.getParameter("valFrecuencia_F");
        String valConErrado_CE = request.getParameter("valConErrado_CE");
        String valComplejidad_CM = request.getParameter("valComplejidad_CM");
        
        String [] campos = {"idPerfilPuesto","descripcionPuesto","valConErrado_CE","valComplejidad_CM"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _valFrecuencia_F = Integer.parseInt(valFrecuencia_F);
            int _valConErrado_CE = Integer.parseInt(valConErrado_CE);
            int _valComplejidad_CM = Integer.parseInt(valComplejidad_CM);           
            
            sql = "INSERT INTO funcionPuesto VALUES(funcionPuesto_seq.nextval,?,?,?,?,?)";
            
            int rpta = gisService.update(sql, descripcionPuesto, _idPerfilPuesto, _valFrecuencia_F, _valConErrado_CE, _valComplejidad_CM);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readFuncionPuesto", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readFuncionPuesto()
    {   
        String sql = "SELECT * FROM funcionPuesto";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readFuncionPuestoId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readFuncionPuestoId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
     
        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql = "SELECT * FROM funcionPuesto WHERE idPerfilPuesto = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros funcionPuesto con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
            
    @RequestMapping(value = "/home/updateFuncionPuesto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateFuncionPuesto(HttpServletRequest request)
    {   
        String idFuncionPuesto = request.getParameter("idFuncionPuesto");
        String descripcionPuesto = request.getParameter("descripcionPuesto");        
        String valFrecuencia_F = request.getParameter("valFrecuencia_F");
        String valConErrado_CE = request.getParameter("valConErrado_CE");
        String valComplejidad_CM = request.getParameter("valComplejidad_CM");
        
             
        String [] campos = {"idFuncionPuesto", "descripcionPuesto","valConErrado_CE","valComplejidad_CM"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idFuncionPuesto = Integer.parseInt(idFuncionPuesto);
            int _valFrecuencia_F = Integer.parseInt(valFrecuencia_F);
            int _valConErrado_CE = Integer.parseInt(valConErrado_CE);
            int _valComplejidad_CM = Integer.parseInt(valComplejidad_CM);           
            
            sql =   "UPDATE funcionPuesto " +
                    "SET descripcionPuesto = ?, valFrecuencia_F = ?, valConErrado_CE = ?, valComplejidad_CM = ? " +
                    "WHERE idFuncionPuesto = ?";
            
            int rpta = gisService.update(sql, descripcionPuesto, _valFrecuencia_F, _valConErrado_CE, _valComplejidad_CM, _idFuncionPuesto);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idFuncionPuesto "+ idFuncionPuesto +" no existe. No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteFuncionPuesto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteFuncionPuesto(HttpServletRequest request)
    {   
        String idFuncionPuesto = request.getParameter("idFuncionPuesto");       
             
        String [] campos = {"idFuncionPuesto"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idFuncionPuesto = Integer.parseInt(idFuncionPuesto);     
            
            sql =   "DELETE funcionPuesto " +
                    "WHERE idFuncionPuesto = ?";
            
            int rpta = gisService.update(sql, _idFuncionPuesto);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idFuncionPuesto "+ idFuncionPuesto +" no existe. No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/readNivelEducativo", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readNivelEducativo()
    {   
        String sql =    "SELECT * FROM M_NIVELEDUCATIVO";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readGradoEstudios", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readGradoEstudios()
    {   
        String sql =    "SELECT * FROM M_GRADOESTUDIOS";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readCarrera", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readCarrera()
    {   
        String sql =    "SELECT c.*, e.descripcion as especialidad " +
                        "FROM M_CARRERA c " +
                        "INNER JOIN ESPECIALIDAD e ON c.idEspeciaidad = e.idEspecialidad";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/createFormAcademica", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createFormAcademica(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idNivelEdu = request.getParameter("idNivelEdu");
        String nivelCompleto = request.getParameter("nivelCompleto");
        String idGradoEstu = request.getParameter("idGradoEstu");
        String idCarreras = request.getParameter("idCarreras");
        String idMaestria = request.getParameter("idMaestria");
        String nivMaestria = request.getParameter("nivMaestria");
        String idDoctorado = request.getParameter("idDoctorado");
        String nivDoctorado = request.getParameter("nivDoctorado");
        String colegiatura = request.getParameter("colegiatura");
        String habilitacion = request.getParameter("habilitacion");

        String [] campos = {"idPerfilPuesto","idNivelEdu","nivelCompleto","idGradoEstu", "idCarreras", "idMaestria",
                            "nivMaestria","idDoctorado", "nivDoctorado", "colegiatura", "habilitacion"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request,campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _idNivelEdu = Integer.parseInt(idNivelEdu);
            int _idGradoEstu = Integer.parseInt(idGradoEstu);
            int _idMaestria = Integer.parseInt(idMaestria);
            int _idDoctorado = Integer.parseInt(idDoctorado);
            
            sql = "INSERT INTO form_academica VALUES(form_academica_seq.nextval,?,?,?,?,?,?,?,?,?,?,?, 22)";
            int rpta = gisService.update(sql, _idPerfilPuesto, _idNivelEdu, nivelCompleto, _idGradoEstu, idCarreras, _idMaestria, nivMaestria, _idDoctorado, nivDoctorado, colegiatura, habilitacion);
            
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
    
    @RequestMapping(value = "/home/readFormAcademica", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readFormAcademica()
    {   
        String sql =    "SELECT a.*, m.descripcion as niveleducativo, g.descripcion as gradoestudios " +
                        "FROM form_academica a " +
                        "LEFT JOIN M_NIVELEDUCATIVO m ON a.idNiveledu = m.IDNIVELEDUCATIVO " +
                        "LEFT JOIN M_GRADOESTUDIOS g ON a.idGradoEstu = g.IDGRADOESTUDIOS";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readFormAcademicaId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readFormAcademicaId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT a.*, m.descripcion as niveleducativo, g.descripcion as gradoestudios " +
                            "FROM form_academica a " +
                            "INNER JOIN M_NIVELEDUCATIVO m ON a.idNiveledu = m.IDNIVELEDUCATIVO " +
                            "INNER JOIN M_GRADOESTUDIOS g ON a.idGradoEstu = g.IDGRADOESTUDIOS " +
                            "WHERE a.idPerfilPuestoA = " + _idPerfilPuesto;
            
            System.err.println("Consulta PerfilPorId: " + sql);
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros form_academica con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/readListaCursos", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readListaCursos()
    {   
        String sql =    "SELECT * FROM LISTACURSOS";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readNivelConoc", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readNivelConoc()
    {   
        String sql =    "SELECT * FROM NIVELCONOC";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/createConocimientOtros", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createConocimientOtros(HttpServletRequest request)
    {   
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idListaCursoso = request.getParameter("idListaCursoso");        
        String idNivelConoc = request.getParameter("idNivelConoc");
             
        String [] campos = {"idPerfilPuesto","idListaCursoso","idNivelConoc"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _idListaCursoso = Integer.parseInt(idListaCursoso);
            int _idNivelConoc = Integer.parseInt(idNivelConoc);
            
            sql = "INSERT INTO conocimientOtros VALUES(conocimientOtros_seq.nextval, ?, ?, ?, 22)";
            
            int rpta = gisService.update(sql, _idPerfilPuesto, _idListaCursoso, _idNivelConoc);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readConocimientOtros", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientOtros()
    {   
        String sql =    "SELECT c.*, l.descripcion as curso, n.descripcion as nivel " +
                        "FROM conocimientOtros c " +
                        "INNER JOIN listaCursos l ON c.idListaCursoso = l.idListaCursos " +
                        "INNER JOIN nivelConoc n ON c.idNivelConoc = n.idNivelConoc";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readConocimientOtrosId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientOtrosId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT c.*, l.descripcion as curso, n.descripcion as nivel " +
                            "FROM conocimientOtros c " +
                            "INNER JOIN listaCursos l ON c.idListaCursoso = l.idListaCursos " +
                            "INNER JOIN nivelConoc n ON c.idNivelConoc = n.idNivelConoc " +
                            "WHERE c.IDPERFILPUESTOO = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientOtros con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateConocimientOtros", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateConocimientOtros(HttpServletRequest request)
    {   
        String idConocOtros = request.getParameter("idConocOtros");
        String idListaCursoso = request.getParameter("idListaCursoso");        
        String idNivelConoc = request.getParameter("idNivelConoc");
        
        String [] campos = {"idConocOtros", "idListaCursoso","idNivelConoc"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocOtros = Integer.parseInt(idConocOtros);
            int _idListaCursoso = Integer.parseInt(idListaCursoso);
            int _idNivelConoc = Integer.parseInt(idNivelConoc);
            
            sql =   "UPDATE conocimientOtros " +
                    "SET idListaCursoso = ?, idNivelConoc = ? " +
                    "WHERE idConocotros = ?";
            
            int rpta = gisService.update(sql, _idListaCursoso, _idNivelConoc, _idConocOtros);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idConocotros "+ idConocOtros +" no existe. No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteConocimientOtros", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteConocimientOtros(HttpServletRequest request)
    {   
        String idConocOtros = request.getParameter("idConocOtros");       
             
        String [] campos = {"idConocOtros"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocotros = Integer.parseInt(idConocOtros);     
            
            sql =   "DELETE conocimientOtros " +
                    "WHERE idConocotros = ?";
            
            int rpta = gisService.update(sql, _idConocotros);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idConocotros "+ idConocOtros +" no existe. No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/createConocimientoTec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createConocimientoTec(HttpServletRequest request)
    {   
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idListaCursosT = request.getParameter("idListaCursosT");        
             
        String [] campos = {"idPerfilPuesto","idListaCursosT"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _idListaCursosT = Integer.parseInt(idListaCursosT);
            
            sql = "INSERT INTO conocimientotec VALUES(conocimientotec_seq.nextval,?,?, 22, null, null, null, null)";
            
            int rpta = gisService.update(sql, _idPerfilPuesto, _idListaCursosT);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readConocimientoTec", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientoTec()
    {   
        String sql =    "SELECT c.*, l.descripcion AS curso " +
                        "FROM conocimientotec C " +
                        "INNER JOIN listaCursos l ON c.idListaCursosT = l.idListaCursos";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readConocimientoTecId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientoTecId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT c.*, l.descripcion AS curso " +
                            "FROM conocimientotec C " +
                            "INNER JOIN listaCursos l ON c.idListaCursosT = l.idListaCursos " +
                            "WHERE c.IDPERFILPUESTOT = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientotec con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateConocimientoTec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateConocimientoTec(HttpServletRequest request)
    {   
        String idConocTec = request.getParameter("idConocTec");
        String idListaCursosT = request.getParameter("idListaCursosT");       
        
        String [] campos = {"idConocTec", "idListaCursosT"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocTec = Integer.parseInt(idConocTec);
            int _idListaCursosT = Integer.parseInt(idListaCursosT);
            
            sql =   "UPDATE conocimientotec " +
                    "SET idListaCursosT = ? " +
                    "WHERE IDCONOCTEC = ?";
            
            int rpta = gisService.update(sql, _idListaCursosT, _idConocTec);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idConocTec "+ idConocTec +" no existe. No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteConocimientoTec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteConocimientoTec(HttpServletRequest request)
    {   
        String idConocTec = request.getParameter("idConocTec");       
             
        String [] campos = {"idConocTec"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocTec = Integer.parseInt(idConocTec);     
            
            sql =   "DELETE conocimientotec WHERE IDCONOCTEC = ?";
            
            int rpta = gisService.update(sql, _idConocTec);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "El idConocTec "+ idConocTec +" no existe. No se actualizaron registros.");;
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/createConocimientoEspec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createConocimientoEspec(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String tipoConocim = request.getParameter("tipoConocim");        
        String idListaCursose = request.getParameter("idListaCursose");        
        String nroHoras = request.getParameter("nroHoras");
        String sustentar = request.getParameter("sustentar");        
             
        String [] campos = {"idPerfilPuesto","tipoConocim", "idListaCursose", "nroHoras", "sustentar"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();        
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idListaCursose = Integer.parseInt(idListaCursose);
            int _nroHoras = Integer.parseInt(nroHoras);
            int _sustentar = Integer.parseInt(sustentar);
            
            sql = "INSERT INTO conocimientoespec VALUES(conocimientoespec_seq.nextval, ?, ?, ?, ?, 22, ?, null, null,null, null)";
            
            int rpta = gisService.update(sql, idPerfilPuesto, tipoConocim, _idListaCursose, _nroHoras, _sustentar);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readConocimientoEspec", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientoEspec()
    {   
        String sql =    "SELECT c.*, l.descripcion as curso " +
                        "FROM conocimientoespec c " +
                        "INNER JOIN listaCursos l ON c.idListaCursosE = l.idListaCursos";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readConocimientoEspecId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readConocimientoEspecId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT c.*, l.descripcion as curso " +
                            "FROM conocimientoespec c " +
                            "INNER JOIN listaCursos l ON c.idListaCursosE = l.idListaCursos " +
                            "WHERE c.IDPERFILPUESTOE = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientoespec con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateConocimientoEspec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateConocimientoEspec(HttpServletRequest request)
    {   
        String idConocesP = request.getParameter("idConocesP");
        String tipoConocim = request.getParameter("tipoConocim");
        String idListaCursose = request.getParameter("idListaCursose");
        String nroHoras = request.getParameter("nroHoras");
        String sustentar = request.getParameter("sustentar");         
        
        String [] campos = {"idConocesP", "tipoConocim", "idListaCursose", "nroHoras", "sustentar"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocesP = Integer.parseInt(idConocesP);
            int _idListaCursose = Integer.parseInt(idListaCursose);
            int _nroHoras = Integer.parseInt(nroHoras);
            int _sustentar = Integer.parseInt(sustentar);
            
            sql =   "UPDATE conocimientoespec " +
                    "SET idListaCursosE = ?, tipoConocim = ?, sustentar = ?, nroHoras = ? " +
                    "WHERE IDCONOCESP = ?";
            
            int rpta = gisService.update(sql, _idListaCursose, tipoConocim, _sustentar, _nroHoras, _idConocesP);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteConocimientoEspec", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteConocimientoEspec(HttpServletRequest request)
    {   
        String idConocesP = request.getParameter("idConocesP");       
             
        String [] campos = {"idConocesP"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idConocesP = Integer.parseInt(idConocesP);     
            
            sql =   "DELETE conocimientoespec WHERE idConocesP = ?";
            
            int rpta = gisService.update(sql, _idConocesP);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/createExpeRequerida", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createExpeRequerida(HttpServletRequest request)
    {   
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String expeGral = request.getParameter("expeGral");
        String expesPublico = request.getParameter("expesPublico");
        String expeEspecifica = request.getParameter("expeEspecifica");
        String observac = request.getParameter("observac");                
             
        String [] campos = {"idPerfilPuesto","expeGral", "expesPublico", "expeEspecifica", "observac"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _expeGral = Integer.parseInt(expeGral);
            int _expesPublico = Integer.parseInt(expesPublico);
            int _expeEspecifica = Integer.parseInt(expeEspecifica);
            
            sql = "INSERT INTO experequerida VALUES(expeRequerida_seq.nextval,?,?,?,?,?, 22, null, null, null, null)";
            
            int rpta = gisService.update(sql, _idPerfilPuesto, _expeGral, _expesPublico, _expeEspecifica, observac);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readExpeRequerida", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readExpeRequerida()
    {   
        String sql =    "SELECT * FROM experequerida";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readExpeRequeridaId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readExpeRequeridaId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {            
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT * FROM experequerida " +
                            "WHERE idPerfilPuestox = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientotec con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateExpeRequerida", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateExpeRequerida(HttpServletRequest request)
    {   
        String idExpeRequerida = request.getParameter("idExpeRequerida");
        String expeGral = request.getParameter("expeGral");
        String expesPublico = request.getParameter("expesPublico");
        String expeEspecifica = request.getParameter("expeEspecifica");
        String observac = request.getParameter("observac");                
             
        String [] campos = {"idExpeRequerida", "expeGral", "expesPublico", "expeEspecifica", "observac"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;
            
            int _idExpeRequerida = Integer.parseInt(idExpeRequerida);
            int _expeGral = Integer.parseInt(expeGral);
            int _expesPublico = Integer.parseInt(expesPublico);
            int _expeEspecifica = Integer.parseInt(expeEspecifica);
            
            sql =   "UPDATE experequerida " +
                    "SET expegral = ?, expespublico = ?, expeespecifica = ?, observac = ? " +
                    "WHERE idexperequerida = ?";
            
            int rpta = gisService.update(sql, _expeGral, _expesPublico, _expeEspecifica, observac, _idExpeRequerida);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteExpeRequerida", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteExpeRequerida(HttpServletRequest request)
    {   
        String idExpeRequerida = request.getParameter("idExpeRequerida");       
             
        String [] campos = {"idExpeRequerida"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idExpeRequerida = Integer.parseInt(idExpeRequerida);     
            
            sql =   "DELETE experequerida WHERE idExpeRequerida = ?";
            
            int rpta = gisService.update(sql, _idExpeRequerida);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/createExpNivelReq", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createExpNivelReq(HttpServletRequest request)
    {   
        String idExpeRequerida = request.getParameter("idExpeRequerida");
        String idNivelLaboral = request.getParameter("idNivelLaboral");        
             
        String [] campos = {"idExpeRequerida", "idNivelLaboral"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idExpeRequerida = Integer.parseInt(idExpeRequerida);
            int _idNivelLaboral = Integer.parseInt(idNivelLaboral);
            
            sql = "INSERT INTO exp_nivelReq VALUES(exp_nivelReq_seq.nextval, ?, ?, null, null)";
            
            int rpta = gisService.update(sql, _idExpeRequerida, _idNivelLaboral);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readExpNivelReq", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readExpNivelReq()
    {   
        String sql =    "SELECT e.*, n.descripcion as nivelLaboral " +
                        "FROM exp_nivelReq e " +
                        "INNER JOIN nivellaboral n ON e.idNivelLaboral = n.idNivelLab";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        /*
                */ 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readExpNivelReqId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readExpNivelReqId(HttpServletRequest request)
    {
        String idExpeRequerida = request.getParameter("idExpeRequerida");

        String [] campos = {"idExpeRequerida"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idExpeRequerida = Integer.parseInt(idExpeRequerida);  
            
            String sql =    "SELECT e.*, n.descripcion as nivelLaboral " +
                            "FROM exp_nivelReq e " +
                            "INNER JOIN nivellaboral n ON e.idNivelLaboral = n.idNivelLab " +
                            "WHERE e.idExpeRequerida = " + _idExpeRequerida;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientotec con id " + idExpeRequerida);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateExpNivelReq", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateExpNivelReq(HttpServletRequest request)
    {           
        String idNivelReque = request.getParameter("idNivelReque");
        String idNivelLaboral = request.getParameter("idNivelLaboral");        
             
        String [] campos = {"idNivelReque", "idNivelLaboral"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idNivelReque = Integer.parseInt(idNivelReque);
            int _idNivelLaboral = Integer.parseInt(idNivelLaboral);
            
            sql =   "UPDATE exp_nivelReq SET idNivelLaboral = ? WHERE idNivelReque = ?";
            
            int rpta = gisService.update(sql, _idNivelLaboral, _idNivelReque);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteExpNivelReq", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteExpNivelReq(HttpServletRequest request)
    {   
        String idNivelReque = request.getParameter("idNivelReque");
             
        String [] campos = {"idNivelReque"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idNivelReque = Integer.parseInt(idNivelReque);     
            
            sql =   "DELETE exp_nivelReq WHERE idNivelReque = ?";
            
            int rpta = gisService.update(sql, _idNivelReque);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/readDiccompetencias", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readDiccompetencias()
    {   
        String sql =    "SELECT * FROM DICCOMPETENCIAS";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/createCompetencias", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createCompetencias(HttpServletRequest request)
    {   
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idDiccompetenc = request.getParameter("idDiccompetenc");
             
        String [] campos = {"idPerfilPuesto","idDiccompetenc"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);
            int _idDiccompetenc = Integer.parseInt(idDiccompetenc);
            
            sql = "INSERT INTO competencias VALUES(competencias_seq.nextval,?,?, 22)";
            
            int rpta = gisService.update(sql, _idPerfilPuesto, _idDiccompetenc);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readCompetencias", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readCompetencias()
    {   
        String sql =    "SELECT c.*, d.descripcion, d.sumario as sumario " +
                        "FROM competencias c " +
                        "INNER JOIN DICCOMPETENCIAS d ON c.idDiccompetenc = d.idDicCompetencias";
                
        List<Map<String, Object>> objects = gisService.consulta(sql);
        
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        System.err.println(objects); 
        System.err.println("+++++++++++++++++++++++++++++++++"); 
        return objects;        
    }
    
    @RequestMapping(value = "/home/readCompetenciasId", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readCompetenciasId(HttpServletRequest request)
    {
        String idPerfilPuesto = request.getParameter("idPerfilPuesto");

        String [] campos = {"idPerfilPuesto"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(objects.get(0).get("error").toString());        
        
        if(!hayError)
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql =    "SELECT c.*, d.descripcion, d.sumario as sumario " +
                            "FROM competencias c " +
                            "INNER JOIN DICCOMPETENCIAS d ON c.idDiccompetenc = d.idDicCompetencias " +
                            "WHERE c.idPerfilPuestoC = " + _idPerfilPuesto;
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros conocimientotec con id " + idPerfilPuesto);
            }
            else
            {
                error.put("error", false);
                error.put("mensaje", "OK");                
            }            
        }   
        
        objects.add(error);
        return objects;        
    }
    
    @RequestMapping(value = "/home/updateCompetencias", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateCompetencias(HttpServletRequest request)
    {   
        String idCompetencias = request.getParameter("idCompetencias");
        String idDiccompetenc = request.getParameter("idDiccompetenc");
             
        String [] campos = {"idCompetencias", "idDiccompetenc"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idCompetencias = Integer.parseInt(idCompetencias);
            int _idDiccompetenc = Integer.parseInt(idDiccompetenc);
            
            sql =   "UPDATE competencias SET idDiccompetenc = ? WHERE idCompetencias = ?";
            
            int rpta = gisService.update(sql, _idDiccompetenc, _idCompetencias);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/deleteCompetencias", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> deleteCompetencias(HttpServletRequest request)
    {   
        String idCompetencias = request.getParameter("idCompetencias");
             
        String [] campos = {"idCompetencias"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idCompetencias = Integer.parseInt(idCompetencias);     
            
            sql =   "DELETE competencias WHERE idCompetencias = ?";
            
            int rpta = gisService.update(sql, _idCompetencias);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se eliminaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    //Aprobaciones    
    @RequestMapping(value = "/home/readAappRequisicion", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readAappRequisicion()
    {   
        String sql =    "SELECT r.IDREQUISICIONP, r.IDUORGANICA, r.IDPERFILPUESTO, r.MONTOMES, r.NROPUESTOS, r.ESTADO, r.FE_INICIO, r.FE_TERMINO, " +
                        "case WHEN r.NACPERUANA is null THEN 'N' WHEN r.NACPERUANA = 'S' THEN 'S' ELSE 'N' END as NACPERUANA, " +
                        "case WHEN r.SUSTENTONAC is null THEN 'Ninguno' ELSE r.SUSTENTONAC END as SUSTENTONAC, " +
                        "r.FE_CREA, r.USU_CREA, r.IDTHORARIOS, " +
                        "case WHEN r.TIPOREQUERIMIENTO = 'N' THEN 'NUEVO' ELSE 'REEMPLAZO' END as TIPOREQUERIMIENTO, " +
                        "d.descripcion as dependencia, p.nomPuesto as puesto, " +
                        "case WHEN e1.estadonombre is null THEN 'Pendiente' ELSE e1.estadonombre END as JEFEORGANO, " +
                        "case WHEN e2.estadonombre is null THEN 'Pendiente' ELSE e2.estadonombre END as DIRECTOREJECUTIVO, " +
                        "case WHEN e3.estadonombre is null THEN 'Pendiente' ELSE e3.estadonombre END as CERTIFICACION, " +
                        "case WHEN a1.estado is null then 6 ELSE a1.estado END as ESTADOJEFEORGANO, " +
                        "case WHEN a2.estado is null then 6 ELSE a1.estado END as ESTADODIRECTOREJECUTIVO, " +
                        "case WHEN c.estadoProceso is null then 6 ELSE a1.estado END as ESTADOCERTIFICACION, " +
                        "case WHEN a1.FEC_APROBA is null and a2.FEC_APROBA is null THEN NULL WHEN a1.FEC_APROBA >= a2.FEC_APROBA THEN TO_CHAR(a1.FEC_APROBA , 'YYYY-MM-DD') WHEN a1.FEC_APROBA < a2.FEC_APROBA THEN TO_CHAR(a2.FEC_APROBA , 'YYYY-MM-DD') " +
                        "     WHEN a1.FEC_APROBA is null THEN TO_CHAR(a2.FEC_APROBA , 'YYYY-MM-DD') WHEN a2.FEC_APROBA is null THEN TO_CHAR(a1.FEC_APROBA , 'YYYY-MM-DD') ELSE NULL END as FEC_APROBA " +
                        "FROM REQUISIONPUESTO r " +
                        "LEFT JOIN CERTIFICAPPTO c ON r.IDREQUISICIONP = c.idRequisicion " +
                        "LEFT JOIN AAPPREQUISICION a1 ON r.IDREQUISICIONP = a1.IDREQUISICIONP AND a1.IDAAPPREQUISICION = 1 " +
                        "LEFT JOIN AAPPREQUISICION a2 ON r.IDREQUISICIONP = a2.IDREQUISICIONP AND a2.IDAAPPREQUISICION = 2 " +
                        "LEFT JOIN ESTADO e1 ON a1.estado = e1.idEstado " +
                        "LEFT JOIN ESTADO e2 ON a2.estado = e2.idEstado " +
                        "LEFT JOIN ESTADO e3 ON c.estadoProceso = e3.idEstado " +
                        "INNER JOIN PERFILPUESTO p ON r.idPerfilPuesto = p.idPerfilPuesto " +
                        "INNER JOIN UORGANICA u ON r.idUorganica = u.IDUORGANICA " +
                        "INNER JOIN DEPENDENCIACATALOGO d ON u.UORGANICA_CATALOGO = d.IDCATALOGODEPEND " +
                        "ORDER BY r.idRequisicionP ASC";

        List<Map<String, Object>> objects = gisService.consulta(sql);        

        return objects;        
    }
    
    @RequestMapping(value = "/home/updateAappRequisicion", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateAappRequisicion(HttpServletRequest request)
    {
        String idAappRequisicion = request.getParameter("idAappRequisicion");
        String idRequisicionP = request.getParameter("idRequisicionP");
        String estado = request.getParameter("estado");
        String idUserJefe = request.getParameter("idUserJefe");
             
        String [] campos = {"idAappRequisicion", "idRequisicionP", "estado", "idUserJefe"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            
            int _idAappRequisicion = Integer.parseInt(idAappRequisicion);
            int _idRequisicionP = Integer.parseInt(idRequisicionP);
            int _estado = Integer.parseInt(estado);
            int _idUserJefe = Integer.parseInt(idUserJefe);
            
            String sql;          
              
            sql =   "MERGE " +
                    "INTO AAPPREQUISICION a " +
                    "USING (select ? idAappRequisicion, ? idRequisicionP, ? estado, ? idUserJefe from dual) d " +
                    "ON (a.idAappRequisicion = d.idAappRequisicion and a.idRequisicionP = d.idRequisicionP) " +
                    "WHEN MATCHED THEN " +
                    "UPDATE SET a.estado = d.estado, a.fec_aproba = sysdate " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES(d.idAappRequisicion, d.idRequisicionP, null, d.estado, d.idUserJefe, sysdate)";
                        
            int rpta = gisService.update(sql, _idAappRequisicion, _idRequisicionP, _estado, _idUserJefe);             
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    //Listado Perfiles (Certificación Presupuestal)
    @RequestMapping(value = "/home/createCertificaPpto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createCertificaPpto(HttpServletRequest request)
    {           
        String idRequisicion = request.getParameter("idRequisicion");
        String nroCertificacion = request.getParameter("nroCertificacion");
        String fteFto = request.getParameter("fteFto");
        String idMetaPpto = request.getParameter("idMetaPpto");
        String creditoPptal = request.getParameter("creditoPptal");
        String observac = request.getParameter("observac");
        String estadoProceso = request.getParameter("estadoProceso");
        String usu_crea = request.getParameter("usu_crea");
             
        String [] campos = {"idRequisicion", "nroCertificacion", "fteFto", "idMetaPpto",
                            "creditoPptal", "observac", "estadoProceso", "usu_crea"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idRequisicion = Integer.parseInt(idRequisicion);
            int _idMetaPpto = Integer.parseInt(idMetaPpto);
            double _creditoPptal = Double.parseDouble(creditoPptal);
            int _estadoProceso = Integer.parseInt(estadoProceso);
            int _usu_crea = Integer.parseInt(usu_crea);
                                
            sql = "INSERT INTO CERTIFICAPPTO VALUES(certificappto_seq.nextval, ?, ?, ?, ?, ?, ?, null, null, ?, null, sysdate, ?, null)";
            
            int rpta = gisService.update(sql, _idRequisicion, nroCertificacion, fteFto, _idMetaPpto, _creditoPptal, observac, _estadoProceso, _usu_crea);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se crearon " + rpta + " registro(s).");
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
    
    @RequestMapping(value = "/home/readCertificaPpto", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readCertificaPpto()
    {   
        String sql =    "SELECT c.IDREQUISICION, d.descripcion as dependencia, p.nomPuesto as puesto, r.NROPUESTOS, r.MONTOMES, c.IDCODPPTO, " +
                        "        c.NROCERTIFICACION, c.FTEFTO, m.descripcion as META, c.CREDITOPPTAL, c.OBSERVAC, TO_CHAR(c.FE_CREA, 'YYYY-MM-DD') as FE_CREA, " +
                        "        CASE WHEN e.ESTADONOMBRE is null THEN 'Pendiente' ELSE e.ESTADONOMBRE END as estado, " +
                        "        CASE WHEN c.ESTADOPROCESO is null THEN 6 ELSE c.ESTADOPROCESO END as ESTADOPROCESO " +
                        "FROM REQUISIONPUESTO r " +
                        "LEFT JOIN CERTIFICAPPTO c ON r.IDREQUISICIONP = c.idRequisicion " +
                        "LEFT JOIN ESTADO e ON c.estadoproceso = e.idEstado " +
                        "INNER JOIN METAPPTAL m ON c.IDMETAPPTO = m.IDMETAPPTO " +
                        "INNER JOIN PERFILPUESTO p ON r.idPerfilPuesto = p.idPerfilPuesto " +
                        "INNER JOIN UORGANICA u ON r.idUorganica = u.IDUORGANICA " +
                        "INNER JOIN DEPENDENCIACATALOGO d ON u.UORGANICA_CATALOGO = d.IDCATALOGODEPEND " +
                        "ORDER BY r.idRequisicionP ASC";

        List<Map<String, Object>> objects = gisService.consulta(sql);        

        return objects;        
    }
    
    @RequestMapping(value = "/home/updateCertificaPpto", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> updateCertificaPpto(HttpServletRequest request)
    {   
        String idCodPpto = request.getParameter("idCodPpto");
        String estadoProceso = request.getParameter("estadoProceso");        
             
        String [] campos = {"idCodPpto", "estadoProceso"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        boolean hayError = Boolean.parseBoolean(candidatos.get(0).get("error").toString());        
        
        if(!hayError)
        {
            candidatos = new ArrayList<Map<String,Object>>();
            String sql;          
            int _idCodPpto = Integer.parseInt(idCodPpto);
            int _estadoProceso = Integer.parseInt(estadoProceso);
            
            sql =   "UPDATE CERTIFICAPPTO SET estadoProceso = ? WHERE idCodPpto = ?";
            
            int rpta = gisService.update(sql, _estadoProceso, _idCodPpto);
            
            if(rpta > 0)
            {
                error.put("error", false);
                error.put("mensaje", "Se actualizaron " + rpta + " registro(s).");
            }
            else
            {
                error.put("error", true);
                error.put("mensaje", "No se actualizaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    //Cronograma
    @RequestMapping(value = "/home/readCronograma", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> readCronograma()
    {   
        String sql =    "SELECT r.*, f.DESCRIPCIONPUESTO puesto, a1.estado estadoJefe1, a2.estado estadoJefe2, c.estadoProceso estadoCertificacion " +
                        "FROM REQUISIONPUESTO r " +
                        "INNER JOIN FUNCIONPUESTO f ON r.idRequisicionP = f.idPerfilPuesto " +
                        "INNER JOIN AAPPREQUISICION a1 ON r.idRequisicionP = a1.idRequisicionP and a1.IDAAPPREQUISICION = 1 and a1.estado = 4 " +
                        "INNER JOIN AAPPREQUISICION a2 ON r.idRequisicionP = a2.idRequisicionP and a2.IDAAPPREQUISICION = 2 and a2.estado = 4 " +
                        "INNER JOIN CERTIFICAPPTO c ON r.idRequisicionP = c.idRequisicion and c.estadoProceso = 4 " +
                        "ORDER BY r.idRequisicionP ASC";

        List<Map<String, Object>> objects = gisService.consulta(sql);        

        return objects;        
    }
    
    //Validaciones
    ArrayList<Map<String, Object>> hayCamposVacios(HttpServletRequest request, String... parametros)
    {
        ArrayList<Map<String, Object>> camposvacios = new ArrayList<Map<String, Object>>();
        String mensaje = "";
        String contenido;
        Map campovacio = new HashMap();
        
        try
        {
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

                campovacio.put("error", true);
                campovacio.put("mensaje", mensaje);            
            }
            else
            {
                campovacio.put("error", false);
                campovacio.put("mensaje", "OK");
            }
        }
        catch(NullPointerException e)
        {
            campovacio.put("error", true);
            campovacio.put("mensaje", "Se enviaron valores nulos");
        }
        catch(Exception e)
        {
            campovacio.put("error", true);
            campovacio.put("mensaje", e.getMessage());
        }
                
        camposvacios.add(campovacio);
        
        return camposvacios;
    }
}     
