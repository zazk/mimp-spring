/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pe.gob.mimp.gis.commons.Constantes;
import pe.gob.mimp.gis.commons.Locations;
import pe.gob.mimp.gis.entity.CentroUploadForm;
import pe.gob.mimp.gis.service.GisService;

/**
 *
 * @author zazk
 */
@Controller("centrosatencionController")
public class CentrosatencionController {
    
    @Autowired
    private GisService gisService;
    @Autowired
    private Locations locations;
    
    private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @RequestMapping("/centrosatencion/listado")
    public ModelAndView listado(HttpServletRequest request) {
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        ModelAndView mav = new ModelAndView("centrosatencion/listado");
        if(request.getSession().getAttribute("actualizacion_correcta") != null){
            //Recuperamos la data del centro
            List<Map<String,Object>> centrosatencion = gisService.consulta(
                "select ca.cod_ca,ca.nom_ca, ca.dir_ca,ca.ref_ca,ca.telef_ca,ca.coord_x,ca.coord_y,"
                        + " d.nom_dist,d.nom_prov,d.nom_dep"
                        + " from geo_centro_atencion ca" +
                        " left join geo_distrito d " +
                        " on d.COD_DIST = ca.COD_DIST WHERE cod_ca = '" + request.getSession().getAttribute("actualizacion_correcta") + "' ");
        

            Map<String, Object> centroatencion = centrosatencion.get(0);
            centroatencion.put("geo_exists", null);
            if(centroatencion.get("coord_x") != null && centroatencion.get("coord_y") != null){
                if(!centroatencion.get("coord_x").toString().isEmpty() && !centroatencion.get("coord_y").toString().isEmpty()){
                    centroatencion.put("geo_exists", "y");
                }
            }
            mav.addObject("centroatencion", centroatencion);
        }
        
        
        Boolean isOperador = dataUsuario.get("idperfil").toString().equals(Constantes.perfilOperador);
        String sqlEntidad = "";
        if(isOperador){
            System.out.println("Es operador");
            //agregamos el filtro
            sqlEntidad = " AND ca.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }

        System.out.println("++++++++++++++++++Locations: " + locations.getUploads());
 
        List<Map<String,Object>> centrosatencion = gisService.consulta(
                "select ca.cod_ca,ca.nom_ca, ca.dir_ca,ca.ref_ca,ca.telef_ca,ca.coord_utm_x,ca.coord_utm_y,"
                        + " d.nom_dist,d.nom_prov,d.nom_dep"
                        + " from geo_centro_atencion ca" +
                        " left join geo_distrito d " +
                        " on d.COD_DIST = ca.COD_DIST WHERE 1 = 1 " + sqlEntidad );
        
        mav.addObject("centrosatencion", centrosatencion); 

        //Recuperamos las notificaciones
        String perfilUsuario = ((Map<String, Object>) request.getSession().getAttribute("usuario")).get("idperfil").toString();
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
    
    @RequestMapping("/centrosatencion/editar")
    public ModelAndView editar(HttpServletRequest request) {        
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return new ModelAndView("redirect:/pages/usuarios/login");
        }
        
        Boolean isOperador = dataUsuario.get("idperfil").toString().equals(Constantes.perfilOperador);
        String sqlEntidad = "";
        if(isOperador){
            System.out.println("Es operador");
            //agregamos el filtro
            sqlEntidad = " AND ca.COD_ENTIDAD = '" + dataUsuario.get("cod_entidad") + "' ";
        }
        
        ModelAndView mav = new ModelAndView("centrosatencion/editar");  
        
        String cod_ca = request.getParameter("cod_ca");
        
        List<Map<String,Object>> centrosatencion = gisService.consulta(
                "select ca.cod_ca,ca.nom_ca, ca.dir_ca,ca.ref_ca,ca.telef_ca,ca.coord_x,ca.coord_y,"
                        + " d.nom_dist,d.nom_prov,d.nom_dep"
                        + " from geo_centro_atencion ca" +
                        " left join geo_distrito d " +
                        " on d.COD_DIST = ca.COD_DIST WHERE cod_ca = '" + cod_ca + "' " + sqlEntidad );
        
        if(centrosatencion.isEmpty()){
            return new ModelAndView("redirect:/pages/centrosatencion/listado");
        }
        Map<String, Object> centroatencion = centrosatencion.get(0);
        
        centroatencion.put("foto_exists", null);
        File foto = new File(locations.getUploads() + File.separator + "centrosatencion" + File.separator + centroatencion.get("cod_ca") + ".jpg");
        if(foto.exists()){
            centroatencion.put("foto_exists", "y");
        }
        
        centroatencion.put("geo_exists", null);
        if(centroatencion.get("coord_x") != null && centroatencion.get("coord_y") != null){
            if(!centroatencion.get("coord_x").toString().isEmpty() && !centroatencion.get("coord_y").toString().isEmpty()){
                centroatencion.put("geo_exists", "y");
            }
        }
        
        mav.addObject("centroatencion", centroatencion); 
        return mav;
    }
    
    @RequestMapping(value = "centrosatencion/actualizar", method = RequestMethod.POST)
    public String procesar(@ModelAttribute("centroUploadForm") CentroUploadForm centroUploadForm, Model map, HttpServletRequest request) {     
        Map<String, Object> dataUsuario = (Map<String, Object>) request.getSession().getAttribute("usuario");
        if(dataUsuario == null){
            return "redirect:/pages/usuarios/login";
        } 
        
        String cod_ca = centroUploadForm.getCod_ca();
        String dir_ca =  centroUploadForm.getDir_ca();
        String ref_ca = centroUploadForm.getRef_ca();
        String telef_ca = centroUploadForm.getTelef_ca();
        
        String coord_x = centroUploadForm.getCoord_x();
        String coord_y = centroUploadForm.getCoord_y();

        try {
            byte[] bytesFile = centroUploadForm.getFoto().getBytes();
            String fileName = centroUploadForm.getFoto().getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));
            
            File serverFile = new File(locations.getUploads() + File.separator + "centrosatencion" + File.separator + cod_ca + ".jpg");
            System.out.println("Ruta en Servidor: " + serverFile.getAbsolutePath() );
            BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
            stream.write(bytesFile);
            stream.close();
        }catch (Exception e) {
            System.out.println("You failed to upload " + e.getMessage());
        }
        
        
        gisService.update("UPDATE GEO_CENTRO_ATENCION SET "
            + "dir_ca = ?, ref_ca = ?, telef_ca = ?, coord_x = ?, coord_y = ?  WHERE cod_ca = ?", 
                 dir_ca, ref_ca, telef_ca, coord_x, coord_y, cod_ca);
        
        
        //Registramos la notificacion
        DateFormat dfn = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime(); 
                    
        String idusuario = dataUsuario.get("idusuario").toString();
        String tipo_accion = "actualizar_centro";
        String id_objeto = "1";
        String mensaje = dataUsuario.get("nombre") + " " + dataUsuario.get("apellidos") + " " + Constantes.accionesUsuario.get(tipo_accion).get("mensaje").toString() + cod_ca;
        String ind_sistema = "1";
        String fec_notificacion = dfn.format(today);
        String tipo_notificacion = Constantes.accionesUsuario.get(tipo_accion).get("tipo").toString();
        
        gisService.update("INSERT INTO GEO_NOTIFICACIONES "
            + "( IDUSUARIO, COD_ENTIDAD, ID_OBJETO, IDACCION, MENSAJE, IND_SISTEMA, FEC_NOTIFICACION, TIPO_NOTIFICACION ) VALUES (?,?,?,?,?,?,TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss'),? )",
                idusuario, dataUsuario.get("cod_entidad"), id_objeto, tipo_accion, mensaje, ind_sistema, fec_notificacion, tipo_notificacion);
        
        request.getSession().setAttribute("actualizacion_correcta", cod_ca);
        return "redirect:/pages/centrosatencion/listado";
    }
}
