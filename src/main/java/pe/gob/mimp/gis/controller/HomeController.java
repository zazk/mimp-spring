package pe.gob.mimp.gis.controller;
 
import java.math.BigInteger;
import java.text.SimpleDateFormat;
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
        
        if(objects.isEmpty())
        {
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
        
        if(objects.isEmpty())
        {
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
        
        if(candidatos.isEmpty())
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
        
        if(candidatos.isEmpty())
        {
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
    @RequestMapping(value = "/home/listarOrgano", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> listarOrgano()
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
    
    @RequestMapping(value = "/home/listarUOrganica", method = RequestMethod.GET)
    public @ResponseBody List<Map<String,Object>> listarUOrganica()
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
    
    @RequestMapping(value = "/home/crearsolicitud", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> crearsolicitud(HttpServletRequest request)
    {        
        String idUOrganica = request.getParameter("idUOrganica");
        String idperfilPuesto = request.getParameter("idperfilPuesto");
        String montoMes = request.getParameter("montoMes");
        String nroPuestos = request.getParameter("nroPuestos");
        String fe_inicio = request.getParameter("fe_inicio");
        String fe_termino = request.getParameter("fe_termino");
        String nacPeruana = request.getParameter("nacPeruana");
        String sustentoNac = request.getParameter("sustentoNac");
        String usu_crea = request.getParameter("usu_crea");        
        String idThorarios = request.getParameter("idThorarios");        
             
        String [] campos = {"idUOrganica","idperfilPuesto","montoMes","nroPuestos","fe_inicio","fe_termino","nacPeruana","usu_crea","idThorarios"};
        
        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql;
            int _idUOrganica = Integer.parseInt(idUOrganica);
            int _idperfilPuesto = Integer.parseInt(idperfilPuesto);
            double _montoMes = Double.parseDouble(montoMes);
            int _nroPuestos = Integer.parseInt(nroPuestos);
            int _usu_crea = Integer.parseInt(usu_crea);
            int _idThorarios = Integer.parseInt(idThorarios);            
            
            String fecha[];
            fecha = fe_inicio.split("-");
            fe_inicio = fecha[2] + "-" + fecha[1] + "-" + fecha[0];            
            
            fecha = fe_termino.split("-");
            fe_termino = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
            
            sql = "INSERT INTO REQUISIONPUESTO VALUES(requisionPuesto_seq.nextval, ?, ?, ?, ?, 6, ?, ?, ?, ?, sysdate, ?, ?)";
            
            int rpta = gisService.update(sql, _idUOrganica, _idperfilPuesto, _montoMes, _nroPuestos, fe_inicio, fe_termino, nacPeruana, sustentoNac, _usu_crea, _idThorarios);
                        
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
    public @ResponseBody List<Map<String,Object>> crearperfil(HttpServletRequest request)
    {   
        String nomPuesto = request.getParameter("nomPuesto");
        String misionPuesto = request.getParameter("misionPuesto");
        String coord_Externa = request.getParameter("coord_Externa");
        String coord_Interna = request.getParameter("coord_Interna");        
             
        String [] campos = {"nomPuesto","misionPuesto","coord_Externa","coord_Interna"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
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
        
        if(objects.isEmpty())
        {
            String sql;          
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            sql = "SELECT * FROM PERFILPUESTO Where idPerfilPuesto = " + _idPerfilPuesto + " and rownum = 1";
            
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
    public @ResponseBody List<Map<String,Object>> crearfuncionpuesto(HttpServletRequest request)
    {   
        //String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idPerfilPuesto = "1";
        String descripcionPuesto = request.getParameter("descripcionPuesto");        
        String valFrecuencia_F = request.getParameter("valFrecuencia_F");
        String valConErrado_CE = request.getParameter("valConErrado_CE");
        String valComplejidad_CM = request.getParameter("valComplejidad_CM");
        
        //String [] campos = {"idPerfilPuesto", "descripcionPuesto","valConErrado_CE","valComplejidad_CM"};     
        String [] campos = {"descripcionPuesto","valConErrado_CE","valComplejidad_CM"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
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
        
        if (objects.isEmpty())
        {
            int _idPerfilPuesto = Integer.parseInt(idPerfilPuesto);  
            
            String sql = "SELECT * FROM funcionPuesto WHERE idPerfilPuesto = " + _idPerfilPuesto + " and rownum = 1";
            
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
        
        if(candidatos.isEmpty())
        {
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
                error.put("mensaje", "No se actualizaron registros.");
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
        
        if(candidatos.isEmpty())
        {
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
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
        }

        return candidatos;
    }
    
    @RequestMapping(value = "/home/createFormAcademica", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createFormAcademica(HttpServletRequest request)
    {   
        String idPerfilPuestoA = "1";
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
        
        //String [] campos = {"idPerfilPuestoA", "idNivelEdu","nivelCompleto","idGradoEstu", "idCarreras", "idMaestria", "nivMaestria",
                            //"idDoctorado", "nivDoctorado", "colegiatura", "habilitacion"};
        
        String [] campos = {"idNivelEdu","nivelCompleto","idGradoEstu", "idCarreras", "idMaestria", "nivMaestria",
                            "idDoctorado", "nivDoctorado", "colegiatura", "habilitacion"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request,campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
            String sql;
            int _idPerfilPuestoA = Integer.parseInt(idPerfilPuestoA);
            int _idNivelEdu = Integer.parseInt(idNivelEdu);
            int _idGradoEstu = Integer.parseInt(idGradoEstu);
            int _idMaestria = Integer.parseInt(idMaestria);
            int _idDoctorado = Integer.parseInt(idDoctorado);
            
            sql = "INSERT INTO form_academica VALUES(form_academica_seq.nextval,?,?,?,?,?,?,?,?,?,?,?, 22)";
            int rpta = gisService.update(sql, _idPerfilPuestoA, _idNivelEdu, nivelCompleto, _idGradoEstu, idCarreras, _idMaestria, nivMaestria, _idDoctorado, nivDoctorado, colegiatura, habilitacion);
            
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
        String idPerfilPuestoA = request.getParameter("idPerfilPuestoA");

        String [] campos = {"idPerfilPuestoA"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if (objects.isEmpty())
        {
            int _idPerfilPuestoA = Integer.parseInt(idPerfilPuestoA);  
            
            String sql =    "SELECT a.*, m.descripcion as niveleducativo, g.descripcion as gradoestudios " +
                            "FROM form_academica a " +
                            "INNER JOIN M_NIVELEDUCATIVO m ON a.idNiveledu = m.IDNIVELEDUCATIVO " +
                            "INNER JOIN M_GRADOESTUDIOS g ON a.idGradoEstu = g.IDGRADOESTUDIOS " +
                            "WHERE a.idPerfilPuestoA = " + _idPerfilPuestoA + " and rownum = 1";
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros form_academica con id " + _idPerfilPuestoA);
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
    
    @RequestMapping(value = "/home/createConocimientOtros", method = RequestMethod.POST)
    public @ResponseBody List<Map<String,Object>> createConocimientOtros(HttpServletRequest request)
    {   
        //String idPerfilPuesto = request.getParameter("idPerfilPuesto");
        String idPerfilPuesto = "1";
        String idListaCursoso = request.getParameter("idListaCursoso");        
        String idNivelConoc = request.getParameter("idNivelConoc");
             
        //String [] campos = {"idPerfilPuesto","idListaCursoso","idNivelConoc"};
        String [] campos = {"idListaCursoso","idNivelConoc"};

        List<Map<String,Object>> candidatos = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if(candidatos.isEmpty())
        {
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
        String idPerfilPuestoCO = request.getParameter("idPerfilPuestoCO");

        String [] campos = {"idPerfilPuestoCO"};

        List<Map<String,Object>> objects = hayCamposVacios(request, campos);
        Map error = new HashMap();
        
        if (objects.isEmpty())
        {
            int _idPerfilPuestoCO = Integer.parseInt(idPerfilPuestoCO);  
            
            String sql =    "SELECT c.*, l.descripcion as curso, n.descripcion as nivel " +
                            "FROM conocimientOtros c " +
                            "INNER JOIN listaCursos l ON c.idListaCursoso = l.idListaCursos " +
                            "INNER JOIN nivelConoc n ON c.idNivelConoc = n.idNivelConoc " +
                            "WHERE c.IDPERFILPUESTOO = " + _idPerfilPuestoCO + " and rownum = 1";
            
            objects = gisService.consulta(sql);
            
            if(objects.isEmpty())
            {
                error.put("error", true);
                error.put("mensaje", "No se encontró registros form_academica con id " + idPerfilPuestoCO);
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
        
        if(candidatos.isEmpty())
        {
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
                error.put("mensaje", "No se actualizaron registros.");
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
        
        if(candidatos.isEmpty())
        {
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
                error.put("mensaje", "No se eliminaron registros.");
            }
            
            candidatos.add(error);
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
