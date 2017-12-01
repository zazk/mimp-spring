package pe.gob.mimp.gis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import pe.gob.mimp.gis.entity.Credential;
import pe.gob.mimp.gis.entity.CriterioBusqueda;
import pe.gob.mimp.gis.service.UsuarioService;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pe.gob.mimp.gis.commons.Constantes;
import pe.gob.mimp.gis.commons.Locations;
import pe.gob.mimp.gis.entity.ExcelReader;
import pe.gob.mimp.gis.entity.FileUploadForm;
import pe.gob.mimp.gis.service.ExcelReaderService;
import pe.gob.mimp.gis.service.GisService;

/**
 *
 * @author Luis
 */

/*
SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
String dateInString = "7-Jun-2013";   
try {   
   Date date = formatter.parse(dateInString); 
   System.out.println(date); 
   System.out.println(formatter.format(date));
} catch (ParseException e) { 
   e.printStackTrace(); 
}
*/
@Controller("usuarioController")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private GisService gisService;
    @Autowired
    private ExcelReaderService excelReaderService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Locations locations;
    
    
    private static Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private String tempFiles = "C:" + File.separator + "tmpFiles";
    
    @RequestMapping("/usuarios/panel")
    public ModelAndView panel(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        ModelAndView mav = new ModelAndView("usuarios/panel");
        List<Map<String,Object>> entidades = gisService.consulta("SELECT e.*, h.*, u.nombre, u.apellidos FROM GEO_HISTORICO h, " +
                                            "(SELECT MAX(COD_HIS) AS COD_HIS, COD_ENTIDAD " +
                                            "FROM GEO_HISTORICO " +
                                            "GROUP BY COD_ENTIDAD ORDER BY COD_HIS DESC) eh " +
                                            "LEFT JOIN GEO_USUARIOS u ON u.COD_ENTIDAD = eh.COD_ENTIDAD " +
                                            "LEFT JOIN GEO_ENTIDAD e ON e.COD_ENTIDAD = eh.COD_ENTIDAD " +
                                            "WHERE  h.COD_HIS = eh.COD_HIS");
        
        System.err.println("+++++++++++++++++++++++++++++++++");
        Map<String, HashMap> estadosArchivo = Constantes.estadosArchivo;
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        mav.addObject("estadosArchivo", estadosArchivo); 
        mav.addObject("entidades", entidades); 

        return mav;
    }
    
    @RequestMapping("/usuarios/notificaciones")
    public ModelAndView notificaciones(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            System.out.println("ejecutando redireccion por perfil operador");
            return new ModelAndView("redirect:/pages/usuarios/panel");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/notificaciones");
        List<Map<String,Object>> notificaciones = gisService.consulta("SELECT n.*, u.* FROM GEO_NOTIFICACIONES n " +
                                            "LEFT JOIN GEO_USUARIOS u ON u.IDUSUARIO = n.IDUSUARIO ORDER BY FEC_NOTIFICACION DESC");
        
        mav.addObject("notificaciones", notificaciones); 
        mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        //Recuperamos las notificaciones
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
 

        return mav;
    }
    
    @RequestMapping("/usuarios/listado")
    public ModelAndView listado(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/listado");
        
        List<Map<String,Object>> usuarios = gisService.consulta("SELECT u.*, p.DESCRIP, d.NOM_DEP, s.NOM_SERV "
                + " FROM GEO_USUARIOS u LEFT JOIN GEO_PERFIL p ON p.IDPERFIL = u.IDPERFIL "
                + " LEFT JOIN GEO_DEPARTAMENTO d ON d.COD_DEPAR = u.COD_DEPAR "
                + " LEFT JOIN GEO_SERVICIOS_MIMP s ON s.COD_SERV = u.COD_SERV");
        System.out.println(usuarios);
        mav.addObject("usuarios", usuarios); 
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));

        return mav;
    }
    
    @RequestMapping(value = "/usuarios/historico", method = RequestMethod.GET)
    public ModelAndView historico(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        Map<String, HashMap> estadosArchivo = Constantes.estadosArchivo;
        String cod_entidad = request.getParameter("cod_entidad");
        
        Boolean isOperador = dataUsuario.get("idperfil").toString().equals(Constantes.perfilOperador);
        
        String sqlEntidad = "";
        if(isOperador){
            System.out.println("Es operador");
            //agregamos el filtro
            sqlEntidad = " AND COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        
        if(!isOperador && cod_entidad != null){
            System.out.println("No es operador filtro por entidad");
            //agregamos el filtro
            sqlEntidad = " AND COD_ENTIDAD = '" + request.getParameter("cod_entidad") + "' ";
        }
        System.out.println("isOperador: " + isOperador);
        
        
        ModelAndView mav = new ModelAndView("usuarios/historico");
        List<Map<String,Object>> historicos = gisService.consulta("SELECT COD_HIS, NOM_ARCHIVO, NOM_PERIODO, OBSERV_HIS, COD_ESTADO, ANO FROM GEO_HISTORICO WHERE 1 = 1 " + sqlEntidad + " ORDER BY FEC_HIS DESC");
        
        mav.addObject("historicos", historicos);
        mav.addObject("estadosArchivo", estadosArchivo);
        
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        return mav;
    }
    
    @RequestMapping("/usuarios/historico_editar")
    public ModelAndView historico_editar(HttpServletRequest request) {
        
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        Map<String, HashMap> estadosArchivo = Constantes.estadosArchivo;
        
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            System.out.println("ejecutando redireccion por perfil operador");
            return new ModelAndView("redirect:/pages/usuarios/panel");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/historico_editar");
        String cod_his = request.getParameter("cod_his");
        
        List<Map<String,Object>> historico = gisService.consulta("SELECT COD_HIS, NOM_ARCHIVO, NOM_PERIODO, FEC_HIS, OBSERV_HIS, COD_ESTADO, ANO FROM GEO_HISTORICO WHERE COD_HIS = " + cod_his + " ORDER BY FEC_HIS DESC");
        
        mav.addObject("historico", historico.get(0));
        
        //Recuperamos las notificaciones
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        mav.addObject("estadosArchivo", estadosArchivo);
        
        return mav;
    }
    
    @RequestMapping("/usuarios/historico_descargar")
    public ModelAndView historico_descargar(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        String sqlEntidad = "";
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            sqlEntidad = " AND cod_entidad = '" + dataUsuario.get("cod_entidad") + "' ";    
        }
     
        String cod_his = request.getParameter("cod_his");
        List<Map<String,Object>> historico = gisService.consulta("SELECT COD_HIS, NOM_ARCHIVO, NOM_PERIODO, FEC_HIS, OBSERV_HIS, COD_ESTADO FROM GEO_HISTORICO WHERE COD_HIS = " + cod_his + sqlEntidad);
        if(historico.isEmpty()){
            return new ModelAndView("redirect:/pages/usuarios/historico");
        }
        Map<String,Object> dataHistorico = historico.get(0);
        String pathFile = locations.getUploads() + File.separator + "reportes" + File.separator + dataHistorico.get("NOM_ARCHIVO");
 
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + dataHistorico.get("NOM_ARCHIVO")+ "\"");

        InputStream is = new FileInputStream(pathFile);

        IOUtils.copy(is, response.getOutputStream());

        response.flushBuffer();  
        
        return null;
    }
    
    @RequestMapping(value = "usuarios/historico_aprobar", method = RequestMethod.POST)
    public String historico_actualizar(HttpServletRequest request){
        
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }

        String estado = request.getParameter("estado");
        String cod_his = request.getParameter("cod_his");
        
        gisService.update("UPDATE GEO_HISTORICO SET cod_estado = ? WHERE cod_his = ?", estado, cod_his );
        String idmensaje = request.getParameter("idmensaje");
        
        
        
        //Recuperamos la data del archivo en historico
        List<Map<String,Object>> mhistorico = gisService.consulta("SELECT * FROM GEO_HISTORICO WHERE COD_HIS = '" + cod_his + "'");
        Map<String,Object> historico = mhistorico.get(0); 
        
        //Registramos la notificacion
        DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime(); 
        
        String tipo_accion = "";
        if(estado.equals(Constantes.estadoAprobado)){
            tipo_accion = "aprobar_archivo";
            request.getSession().setAttribute("historico_aprobado", true); 
            
            ///Procesamos el archivo
            File serverFile = new File(locations.getUploads() + File.separator + "reportes" + File.separator + historico.get("nom_archivo").toString());
            //eReader.readExcelFile(serverFile.getAbsolutePath());
            ExcelReader excelReader = new ExcelReader();
            excelReader.setAnio(historico.get("ano").toString());
            excelReader.setPeriodo(historico.get("nom_periodo").toString());
            excelReader.setCodigo(historico.get("cod_his").toString());
            excelReader.setFileName(serverFile.getAbsolutePath());
            excelReader.readExcelFile();
            this.excelReaderService.insertar(excelReader);
        }else if(estado.equals(Constantes.estadoRechazado)){
            tipo_accion = "rechazar_archivo";
            request.getSession().setAttribute("historico_rechazado", true); 
        }else if(estado.equals(Constantes.estadoRevertido)){
            tipo_accion = "revertir_archivo";
            request.getSession().setAttribute("historico_revertido", true);  
            gisService.update("DELETE GEO_REPORTE_SERVICIOS WHERE cod_his = ?", cod_his );
            
        }
        

        System.out.println("------------------ Revertir Archivo --------------------"); 
        System.out.println("------------------ " + (tipo_accion) +" --------------------");
        System.out.println("------------------ " + Constantes.accionesUsuario.get(tipo_accion) +" --------------------");
        System.out.println("-------------------------------------"); 
            
            
        String idusuario = dataUsuario.get("idusuario").toString();
        String cod_entidad = dataUsuario.get("cod_entidad").toString();
        String id_objeto = "1";
        String mensaje = Constantes.accionesUsuario.get(tipo_accion).get("mensaje").toString() + " " + historico.get("nom_archivo");
        String ind_sistema = "1"; 
        String fec_notificacion = dfn.format(today);
        String tipo_notificacion = Constantes.accionesUsuario.get(tipo_accion).get("tipo").toString();

        gisService.update("INSERT INTO GEO_NOTIFICACIONES "
            + "( IDUSUARIO, COD_ENTIDAD, ID_OBJETO, IDACCION, MENSAJE, IND_SISTEMA, FEC_NOTIFICACION, TIPO_NOTIFICACION ) VALUES (?,?,?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),? )",
                idusuario, cod_entidad, id_objeto, tipo_accion, mensaje, ind_sistema, fec_notificacion, tipo_notificacion);
        
        return "redirect:/pages/usuarios/historico";
    }
      
    @RequestMapping("/usuarios/enviar")
    public ModelAndView enviar(HttpServletRequest request) throws ParseException {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/enviar");
        //Definimos el array de periodos
        SimpleDateFormat dfM = new SimpleDateFormat("MM"); 
        SimpleDateFormat dfA = new SimpleDateFormat("yyyy"); 
        SimpleDateFormat dfp = new SimpleDateFormat("MM");
        Date today = Calendar.getInstance().getTime(); 
        String mesActual = dfp.format(today);
        int anioActual = Integer.parseInt( dfA.format( today ) );
        ArrayList years  = new ArrayList()  ;
        
        String fechaInicio = "01-" + dfM.format(today) + "-" + dfA.format(today); 
        
        SimpleDateFormat dfP = new SimpleDateFormat("dd-MM-yyyy"); 
        List<Map<String, String>> periodos = new ArrayList<Map<String, String>>();
        try{
            Date dInicioPeriodo = dfP.parse(fechaInicio);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dInicioPeriodo);
            
            for(int m = 0; m < Constantes.meses.size(); m ++){
                //Mes actual
                Map<String, String> hashPeriodo = new HashMap<String, String>(); 
                hashPeriodo.put("valor", dfp.format(calendario.getTime()));
                hashPeriodo.put("etiqueta", Constantes.meses.get(dfM.format(calendario.getTime()))  );
                periodos.add(hashPeriodo);

                calendario.add(Calendar.MONTH, -1);
                Integer ano = anioActual -m;
                years.add( ano.toString() );  
            }
            
            mav.addObject("periodos", periodos);
            mav.addObject("years", years );
        } catch (ParseException e) { 
            e.printStackTrace(); 
        }
        
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        return mav;
    }
    
    @RequestMapping(value = "usuarios/procesar", method = RequestMethod.POST)
    public String procesar(@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map, HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }

        //System.err.println("UploadForm: " + uploadForm.getFiles());+
        List<MultipartFile> files = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
        
        // Creating the directory to store file
        File dir = new File(locations.getUploads() + File.separator + "reportes");
        /*
        if (!dir.exists()){
            dir.mkdirs();
        }
        */
        
        System.out.println("Files: " + files);
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                try{
                    byte[] bytes = multipartFile.getBytes();
                    //Proceso de los archivos
                    // Create the file on server
                    DateFormat dfi = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    DateFormat dfa = new SimpleDateFormat("yyyyMMddHHmmss");

                    Date today = Calendar.getInstance().getTime(); 
                    
                    String extension = fileName.substring(fileName.lastIndexOf("."));

                    File serverFile = new File(dir.getAbsolutePath() + File.separator + dfa.format(today) + extension);
                    System.out.println("Ruta en Servidor: " + serverFile.getAbsolutePath() );
                    BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    fileNames.add(fileName);
                    
                    String cod_estado = Constantes.estadoPendiente;
                    // Using DateFormat format method we can create a string
                    String periodo_1 = uploadForm.getNom_periodo_1();
                    String periodo_2 = uploadForm.getNom_periodo_2();
                    String anio = uploadForm.getAnio();
                    String nom_periodo = "";
                    
                    SimpleDateFormat dfCompleto = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat dfAnio = new SimpleDateFormat("yyyy");
                    SimpleDateFormat dfMes = new SimpleDateFormat("MM");
                    Calendar calendario = Calendar.getInstance();
                    
                    String sPeriodo1 = "01-" + periodo_1 + "-" + anio;
                    Date dPeriodo1 = dfCompleto.parse(sPeriodo1);

                    String sPeriodo2 = "01-" + periodo_2 + "-" + anio;
                    Date dPeriodo2 = dfCompleto.parse(sPeriodo2);
                    
                    if(dPeriodo1.before(dPeriodo2) || dPeriodo1.equals(dPeriodo2)){
                        //Mismo periodo, solo comparo si hoy han pasado disez dias desde el ultimo dia del periodo señalado
                        String sPeriodo = "01-" + periodo_2 + "-" + anio;
                        Date dPeriodo = dfCompleto.parse(sPeriodo);
                        
                        //Buscamos el limite para el periodo seleccionado
                        calendario.setTime(dPeriodo);
                        calendario.add(calendario.MONTH, 1);
                        calendario.add(calendario.DATE, 10);
                        //Comparamos si la fecha actual es mayor que la fecha limite
                        if(today.after(calendario.getTime())){
                            cod_estado = Constantes.estadoRetrasado;
                        }
                        System.out.println("------------------------------------------------------");
                        System.out.println("---" + periodo_1 + " - " + periodo_2 + "----");
                        System.out.println("------------------------------------------------------");
                        if( periodo_1.equals( periodo_2 ) ){
                            nom_periodo = Constantes.meses.get( dfMes.format(dPeriodo1) ).substring(0, 3).toUpperCase();
                        } else {
                            nom_periodo = Constantes.meses.get(dfMes.format(dPeriodo1)).substring(0, 3).toUpperCase() + " - " 
                                    + Constantes.meses.get(dfMes.format(dPeriodo2)).substring(0, 3).toUpperCase() ;
                        }
                    }else if(dPeriodo1.after(dPeriodo2)){
                        //Retornamos al formulario de envio con el error de fecha
                        request.getSession().setAttribute("error_fechas", true);
                        return "redirect:/pages/usuarios/enviar";
                    }
                    
                    
                    // representation of a date with the defined format.
                    String nom_archivo = dfa.format(today) + extension;
                    
                    String observ_his = uploadForm.getObserv_his();
                    
                    System.out.println("Session: " + request.getSession().getAttribute("usuario"));
                    
                    String idusuario = dataUsuario.get("idusuario").toString();
                    //Map<String,Object> dataUsuario = request.getSession().getAttribute("usuario");
                    String fec_his = dfi.format(today);
                    //Revisamos si esta atrasado
                    String cod_entidad = dataUsuario.get("cod_entidad").toString();
                    
                    //String ano = dfAnio.format(dPeriodo2);

                    // Print what date is today!
                    System.out.println("Report Date: " + fec_his + " -> " + cod_entidad + " -> " + idusuario);

                    
                    gisService.update("INSERT INTO GEO_HISTORICO "
                    + "( nom_archivo, nom_periodo, observ_his, idusuario, fec_his, cod_estado, cod_entidad, ano ) VALUES (?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'), ?, ?, ?)", 
                        nom_archivo, nom_periodo, observ_his, idusuario, fec_his, cod_estado, cod_entidad, anio);
                    
                    //Handle file content - multipartFile.getInputStream()
                    //Registramos la notificacion
                    DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                    String tipo_accion = "enviar_archivo";
                    String id_objeto = "1";
                    String mensaje = Constantes.accionesUsuario.get(tipo_accion).get("mensaje").toString() + " " + nom_archivo;
                    String ind_sistema = "1";
                    String fec_notificacion = dfn.format(today);
                    String tipo_notificacion = Constantes.accionesUsuario.get(tipo_accion).get("tipo").toString();

                    gisService.update("INSERT INTO GEO_NOTIFICACIONES "
                        + "( IDUSUARIO, COD_ENTIDAD, ID_OBJETO, IDACCION, MENSAJE, IND_SISTEMA, FEC_NOTIFICACION, TIPO_NOTIFICACION ) VALUES (?,?,?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),? )",
                            idusuario, cod_entidad, id_objeto, tipo_accion, mensaje, ind_sistema, fec_notificacion, tipo_notificacion);

                }catch (Exception e) {
                     System.out.println("You failed to upload " + fileName + " => " + e.getMessage());

                }
            }
        }
         
        map.addAttribute("files", fileNames);
        
        
        System.out.println("Redireccion ");
        return "redirect:/pages/usuarios/historico";
    }
    
    
    @RequestMapping("/usuarios/agregar")
    public ModelAndView agregar(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        //Validando tipo perfil
        System.out.println("login validado");
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            System.out.println("ejecutando redireccion por perfil operador");
            return new ModelAndView("redirect:/pages/usuarios/panel");
        }
        
        
        ModelAndView mav = new ModelAndView("usuarios/agregar");
        
        List<Map<String,Object>> perfiles = gisService.consulta("SELECT * FROM GEO_PERFIL");
        List<Map<String,Object>> departamentos = gisService.consulta("SELECT * FROM GEO_DEPARTAMENTO");
        List<Map<String,Object>> entidades = gisService.consulta("SELECT * FROM GEO_ENTIDAD");
        
        mav.addObject("perfiles", perfiles); 
        mav.addObject("departamentos", departamentos); 
        mav.addObject("entidades", entidades); 
        
        //Recuperamos las notificaciones
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        return mav;
    }
    
    @RequestMapping(value = "/usuarios/ingresar", method=RequestMethod.POST)
    public String ingresar(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }
        Date today = Calendar.getInstance().getTime();
        DateFormat dfi = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String nombre =  request.getParameter("nombre") ;
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String cod_entidad = request.getParameter("cod_entidad");
        String idperfil = request.getParameter("idperfil");
        String cargo = request.getParameter("cargo");
        String cod_depar = request.getParameter("cod_depar");
        String cod_servicio = request.getParameter("cod_servicio"); 
        
        String fechareg = dfi.format(today);
        
        //Validamos si existe algun usuario con el mismo nombre de usuario o con el mismo email
        List<Map<String,Object>> validacion = gisService.consulta("SELECT idusuario FROM GEO_USUARIOS WHERE usuario = '" + usuario + "' OR email = '" + email + "'");
        if(validacion.size() > 0){
            System.out.println("Validacion de email y usuario incorrecta, ya existen");
            request.getSession().setAttribute("error_ingresar", true);
            return "redirect:/pages/usuarios/agregar";
        }
        
        
        System.out.println("INSERT INTO GEO_USUARIOS "
            + "( USUARIO, CLAVE, NOMBRE, APELLIDOS, EMAIL, COD_DEPAR, COD_SERV, IDPERFIL, CARGO, FECHAREG ) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss') )" + 
                usuario+ "," +clave+ "," + nombre+  "," +apellidos+  "," +email+ "," + cod_depar+  "," +cod_servicio+ "," + idperfil+ "," + cargo+ "," + fechareg);

        
        gisService.update("INSERT INTO GEO_USUARIOS "
            + "( USUARIO, CLAVE, NOMBRE, APELLIDOS, EMAIL, COD_DEPAR, COD_SERV, IDPERFIL, CARGO, FECHAREG ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss') )", 
                usuario, clave, nombre, apellidos, email, cod_depar, cod_servicio , idperfil, cargo, fechareg);
                //Enviamos el correo al destino
        // takes input from e-mail form
        final String recipientAddress = email;
        final String subject = "Nuevo Registro en mimp.gis";
        final String message = "<h1>Registro en MIMP</h1><strong>Felicidades</strong> su registro fue exitoso";
         
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        
        /*
        // creates a simple e-mail object
        SimpleMailMessage sEmail = new SimpleMailMessage();
        sEmail.setTo(recipientAddress);
        sEmail.setSubject(subject);
        sEmail.setText(message);

        // sends the e-mail
        mailSender.send(sEmail);
        */
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("info@mimp.gob.pe");
                message.setTo(recipientAddress);
                message.addBcc("juandedioz@gmail.com");
                message.setSubject(subject);
                message.setText("<h1>Registro en MIMP</h1><strong>Felicidades</strong> su registro fue exitoso", true);
            }
        });
                
        //Registramos la notificacion
        DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String idusuario = dataUsuario.get("idusuario").toString();
        String tipo_accion = "agregar_usuario";
        String id_objeto = "1";
        String mensaje = nombre + " " + apellidos + " " + Constantes.accionesUsuario.get(tipo_accion).get("mensaje").toString();
        String ind_sistema = "1";
        String fec_notificacion = dfn.format(today);
        String tipo_notificacion = Constantes.accionesUsuario.get(tipo_accion).get("tipo").toString();
        
        gisService.update("INSERT INTO GEO_NOTIFICACIONES "
            + "( IDUSUARIO, COD_ENTIDAD, ID_OBJETO, IDACCION, MENSAJE, IND_SISTEMA, FEC_NOTIFICACION, TIPO_NOTIFICACION ) VALUES (?,?,?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),? )",
                idusuario, cod_entidad, id_objeto, tipo_accion, mensaje, ind_sistema, fec_notificacion, tipo_notificacion);
        
        request.getSession().setAttribute("ingreso_correcto", true);
        return "redirect:/pages/usuarios/listado";
    }
    
    @RequestMapping("/usuarios/editar")
    public ModelAndView editar(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        //Validando tipo perfil
        System.out.println("login validado");
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            System.out.println("ejecutando redireccion por perfil operador");
            return new ModelAndView("redirect:/pages/usuarios/panel");
        }
        
        String idusuario = request.getParameter("idusuario");
        ModelAndView mav = new ModelAndView("usuarios/editar");
        
        List<Map<String,Object>> perfiles = gisService.consulta("SELECT * FROM GEO_PERFIL");
        List<Map<String,Object>> entidades = gisService.consulta("SELECT * FROM GEO_ENTIDAD");
        List<Map<String,Object>> departamentos = gisService.consulta("SELECT * FROM GEO_DEPARTAMENTO");
        List<Map<String,Object>> rsUsuario = gisService.consulta("SELECT * FROM GEO_USUARIOS WHERE idusuario = '" + idusuario + "'");
        
        if(rsUsuario.isEmpty()){
            return new ModelAndView("redirect:/pages/usuarios/listado");
        }
        
        mav.addObject("perfiles", perfiles); 
        mav.addObject("entidades", entidades);
        mav.addObject("departamentos", departamentos); 
        mav.addObject("usuario", rsUsuario.get(0));
        
        //Recuperamos las notificaciones
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        return mav;
    }
    
    @RequestMapping(value = "/usuarios/actualizar", method=RequestMethod.POST)
    public String actualizar(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }
        
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String nombre =  request.getParameter("nombre") ;
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String cod_serv = request.getParameter("cod_servicio");
        String cod_depar = request.getParameter("cod_depar");
        String idperfil = request.getParameter("idperfil");
        String cargo = request.getParameter("cargo");
        String idusuario = request.getParameter("idusuario");
        
        //Validamos si existe algun usuario con el mismo nombre de usuario o con el mismo email
        List<Map<String,Object>> validacion = gisService.consulta("SELECT idusuario FROM GEO_USUARIOS WHERE (usuario = '" + usuario + "' OR email = '" + email + "') AND idusuario != " + idusuario);
        if(validacion.size() > 0){
            System.out.println("Validacion de email y usuario incorrecta, ya existen");
            request.getSession().setAttribute("error_actualizar", true);
            return "redirect:/pages/usuarios/editar?idusuario=" + idusuario;
        }
        
        gisService.update("UPDATE GEO_USUARIOS SET "
            + "USUARIO = ?, CLAVE = ?, NOMBRE = ?, APELLIDOS = ?, EMAIL = ?, COD_SERV = ?,COD_DEPAR = ?,IDPERFIL = ?, CARGO = ? WHERE IDUSUARIO = ?", 
                usuario, clave, nombre, apellidos, email, cod_serv, cod_depar,idperfil, cargo, idusuario);
        
        request.getSession().setAttribute("actualizacion_correcta", true);
        return "redirect:/pages/usuarios/listado";
    }
    
    @RequestMapping("/usuarios/eliminar")
    public String eliminar(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }
        
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        if(perfilUsuario.equals(Constantes.perfilOperador)){
            System.out.println("ejecutando redireccion por perfil operador");
            return "redirect:/pages/usuarios/panel";
        }
        
        String idusuario = request.getParameter("idusuario");
        
        gisService.update("DELETE FROM GEO_USUARIOS WHERE idusuario = '" + idusuario + "'");
        return "redirect:/pages/usuarios/listado";
    }   
    
    @RequestMapping(value = "/usuarios/mensajes", method = RequestMethod.GET)
    public ModelAndView mensajes(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        Boolean isOperador = dataUsuario.get("idperfil").toString().equals(Constantes.perfilOperador);
        String sqlEntidad = "";
        if(isOperador){
            System.out.println("Es operador");
            //agregamos el filtro
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        
        ModelAndView mav = new ModelAndView("usuarios/mensajes");
        List<Map<String,Object>> mensajes = gisService.consulta("SELECT m.*, u.nombre, u.apellidos FROM GEO_MENSAJES m LEFT JOIN GEO_USUARIOS u ON u.idusuario = m.idusuario WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS' ORDER BY m.FEC_MEN DESC");
        
        for(Map<String,Object> mensaje: mensajes ){
            System.out.println("---------------" + mensaje.get("nombre") + "----------" + mensaje.get("apellidos") + "---" + mensaje.get("asunto"));
        }
        
        mav.addObject("mensajes", mensajes);
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        return mav;
    }
    
    @RequestMapping("/usuarios/mensaje")
    public ModelAndView mensaje(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/mensaje");
        
        Boolean isOperador = dataUsuario.get("idperfil").toString().equals(Constantes.perfilOperador);
        String sqlEntidad = "";
        if(isOperador){
            System.out.println("Es operador");
            //agregamos el filtro
            sqlEntidad = " m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' OR ";
        }
        
        String idmensaje = request.getParameter("idmensaje");
        List<Map<String,Object>> mensaje = gisService.consulta("SELECT m.*, u.nombre, u.apellidos FROM GEO_MENSAJES m LEFT JOIN GEO_USUARIOS u ON u.idusuario = m.idusuario WHERE m.IDMENSAJE = '" + idmensaje + "' AND (" + sqlEntidad +  " m.COD_ENTIDAD = 'TODOS' OR m.COD_ENTIDAD IS NULL) ORDER BY m.FEC_MEN DESC");
        
        if(mensaje.isEmpty()){
            System.out.println("No se tiene acceso al mensaje");
            return new ModelAndView("redirect:/pages/usuarios/mensajes");
        }
        
        mav.addObject("mensaje", mensaje.get(0));
        
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        return mav;  
        
    }
    
    @RequestMapping("/usuarios/nuevo_mensaje")
    public ModelAndView nuevo_mensaje(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        ModelAndView mav = new ModelAndView("usuarios/nuevo_mensaje");
        
        List<Map<String,Object>> entidades = gisService.consulta("SELECT * FROM GEO_ENTIDAD");
        mav.addObject("entidades", entidades);
        
        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
        String sqlEntidad = "";
        if(!perfilUsuario.equals(Constantes.perfilOperador)){
            //Si es jefe
            List<Map<String,Object>> notResumen = gisService.consulta("SELECT n.*,  ROWNUM rnum FROM (SELECT * FROM GEO_NOTIFICACIONES ORDER BY FEC_NOTIFICACION DESC) n WHERE ROWNUM <= 5");
            mav.addObject("notResumen", notResumen);
            mav.addObject("accionesUsuario", Constantes.accionesUsuario); 
        }else{
            //Si es operador
            sqlEntidad = " AND m.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        List<Map<String,Object>> rsMensajes = gisService.consulta("SELECT COUNT(m.IDMENSAJE) AS numMensajes FROM GEO_MENSAJES m WHERE 1 = 1 " + sqlEntidad + " OR m.COD_ENTIDAD = 'TODOS'");
        Map<String,Object> rowMensaje = rsMensajes.get(0);
        mav.addObject("numMensajes", rowMensaje.get("numMensajes"));
        
        return mav;
    }
    
    @RequestMapping(value = "/usuarios/enviar_mensaje", method=RequestMethod.POST)
    public String enviar_mensaje(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        }
        
        DateFormat dfm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        
        System.out.println("++++++++++++++Envio de variables");
        
        String idusuario = dataUsuario.get("idusuario").toString();        
        String asunto = request.getParameter("asunto");
        String contenido = request.getParameter("contenido");
        String fec_men = dfm.format(today);
        String cod_entidad =  request.getParameter("cod_entidad") ;
        
        gisService.update("INSERT INTO GEO_MENSAJES "
            + "( idusuario, asunto, contenido, fec_men, cod_entidad ) VALUES (?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),?)", 
                idusuario, asunto, contenido, fec_men, cod_entidad);
        
        
        
        //Registramos la notificacion
        DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String tipo_accion = "enviar_mensaje";
        String id_objeto = "1";
        String mensaje = dataUsuario.get("nombre").toString() + " " + dataUsuario.get("apellidos").toString() + Constantes.accionesUsuario.get(tipo_accion).get("mensaje").toString();
        String ind_sistema = "1";
        String fec_notificacion = dfn.format(today);
        String tipo_notificacion = Constantes.accionesUsuario.get(tipo_accion).get("tipo").toString();
        
        gisService.update("INSERT INTO GEO_NOTIFICACIONES "
            + "( IDUSUARIO, COD_ENTIDAD, ID_OBJETO, IDACCION, MENSAJE, IND_SISTEMA, FEC_NOTIFICACION, TIPO_NOTIFICACION ) VALUES (?,?,?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),? )",
                idusuario, cod_entidad, id_objeto, tipo_accion, mensaje, ind_sistema, fec_notificacion, tipo_notificacion);
        
        request.getSession().setAttribute("envio_correcto", true);       
        return "redirect:/pages/usuarios/mensajes";
    }
    
    @RequestMapping("/usuarios/login")
    public ModelAndView login(HttpServletRequest request) {
        
        ModelAndView mav = new ModelAndView("usuarios/login");
        Credential credential = new Credential();
        mav.getModelMap().put("credential", credential);
        
        System.out.println("Enter to Login ");
        return mav;
    }
    
    @RequestMapping(value = "/usuarios/autenticar", method=RequestMethod.POST)
    public String autenticar(HttpServletRequest request) {
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");
        
        List<Map<String,Object>> usuarios = gisService.consulta("SELECT * FROM GEO_USUARIOS WHERE email = '" + email +  "' AND clave = '" + clave + "'");

        System.out.println("Usuario: " + usuarios);
        
        //if (usuarioService.autenticar(credential.getLogin(), credential.getClave())) {
        if ( usuarios.isEmpty() ) {
            
            request.getSession().setAttribute("login_incorrecto", true);
            return "redirect:/pages/usuarios/login";
        }
        Map<String,Object> usuario = usuarios.get(0);
        request.getSession().setAttribute("usuario", usuario);
        
        return "redirect:/pages/usuarios/panel";
    }
    
    @RequestMapping(value = "/usuarios/solicitar_ingreso", method=RequestMethod.POST)
    public String solicitar_ingreso(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String nombres = request.getParameter("nombres");
        final String apellidos = request.getParameter("apellidos");
        final String telefono = request.getParameter("telefono");
        
        DateFormat dfm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        
        String idusuario = "1";        
        String asunto = "Nueva solicitud de acceso";
        String contenido = "Nueva solicitud de acceso al sistema: <br />Nombres: " + nombres + " <br />Apellidos: " + apellidos + " <br />Email: " + email + " <br />Telefono: " + telefono + "<br />";
        String fec_men = dfm.format(today);
        String cod_entidad =  "";
        
        gisService.update("INSERT INTO GEO_MENSAJES "
            + "( idusuario, asunto, contenido, fec_men, cod_entidad ) VALUES (?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),?)", 
                idusuario, asunto, contenido, fec_men, cod_entidad);
        
        mailSender.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws MessagingException {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("info@mimp.gob.pe");
                message.setTo(locations.getPostmaster());
                message.addBcc("juandedioz@gmail.com");
                message.addBcc("ljbustamante@gmail.com");
                message.setSubject("Solicitud de acceso al sistema");
                message.setText("<h1>Solicitud de acceso al sistema</h1><br />Nombres: " + nombres + " <br />Apellidos: " + apellidos + " <br />Email: " + email + " <br />Telefono: " + telefono + "<br />", true);
            }
        });
        
        request.getSession().setAttribute("solicitud_correcta", true);
        return "redirect:/pages/usuarios/login";
    }
    
    @RequestMapping("/usuarios/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/pages/usuarios/login";
    }        
    
    
    @RequestMapping(value = "/usuarios/buscar", method=RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("criterioBusqueda") CriterioBusqueda criterio, SessionStatus status) {
        ModelAndView mav = new ModelAndView("usuarios/resultadoBusqueda");
        log.info("resultado = " + usuarioService.buscarPorNombre(criterio.getNoUsuario()).size());
        mav.getModel().put("resultado",usuarioService.buscarPorNombre(criterio.getNoUsuario()));
        return mav;
    }
    
}
