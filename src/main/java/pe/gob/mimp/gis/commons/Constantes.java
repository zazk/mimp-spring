/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.commons;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Luis
 */
 public class Constantes {
    
    public static String estadoRetrasado = "2";
    public static String estadoPendiente = "1";
    public static String estadoAprobado = "3";
    public static String estadoRechazado = "5";
    public static String estadoRevertido = "6";
    public static String perfilOperador = "1"; 
    
    public static Map<String, HashMap> estadosArchivo;
    
    static{
            Map<String, HashMap> tempMap = new HashMap<String, HashMap>();
            tempMap.put("1", new HashMap<String, String>(){{put("clase", "label-primary");put("nombre", "Pendiente");}});
            tempMap.put("2", new HashMap<String, String>(){{put("clase", "label-warning");put("nombre", "Retrasado");}});
            tempMap.put("3", new HashMap<String, String>(){{put("clase", "label-success");put("nombre", "Aprobado");}});
            tempMap.put("4", new HashMap<String, String>(){{put("clase", "label-info");put("nombre", "Aprobado con Retraso");}});
            tempMap.put("5", new HashMap<String, String>(){{put("clase", "label-danger");put("nombre", "Rechazado");}});
            tempMap.put("6", new HashMap<String, String>(){{put("clase", "label-danger");put("nombre", "Revertido");}});

            estadosArchivo = Collections.unmodifiableMap(tempMap);
    }
    
    public static Map<String, HashMap> accionesUsuario;
    
    static{
            Map<String, HashMap> tempMap = new HashMap<String, HashMap>();
            tempMap.put("agregar_usuario", new HashMap<String, String>(){{put("id", "1");put("mensaje", " ha sido registrado");put("clase", "im-user");put("tipo", "WARNING");}});
            tempMap.put("enviar_archivo", new HashMap<String, String>(){{put("id", "2");put("mensaje", " envió archivo");put("clase", "im-bubble12");put("tipo", "WARNING");}});
            tempMap.put("aprobar_archivo", new HashMap<String, String>(){{put("id", "3");put("mensaje", "Se aprobó ");put("clase", "im-power");put("tipo", "WARNING");}});
            tempMap.put("rechazar_archivo", new HashMap<String, String>(){{put("id", "4");put("mensaje", "Se rechazó ");put("clase", "im-database");put("tipo", "WARNING");}});
            tempMap.put("enviar_mensaje", new HashMap<String, String>(){{put("id", "5");put("mensaje", " envió un mensaje");put("clase", "im-github3");put("tipo", "WARNING");}});
            tempMap.put("actualizar_centro", new HashMap<String, String>(){{put("id", "5");put("mensaje", " actualizo el centro de atención ");put("clase", "im-github3");put("tipo", "WARNING");}});
            tempMap.put("revertir_archivo", new HashMap<String, String>(){{put("id", "6");put("mensaje", "Se ha eliminado los archivos del reporte seleccionado ");put("clase", "im-github3");put("tipo", "WARNING");}});

            accionesUsuario = Collections.unmodifiableMap(tempMap);
    }
    
    public static Map<String, HashMap> camposReporteServicios;
    
    static{
            Map<String, HashMap> tempMap = new HashMap<String, HashMap>();
            //tempMap.put("id_reporte", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("cod_ca", new HashMap<String, String>(){{put("tipo", "varchar");put("defecto", "  ");put("size", "16");}});
            tempMap.put("cod_serv", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "10");}});
            tempMap.put("cod_linea", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "8");}});
            tempMap.put("cod_entidad", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "6");}});
            //tempMap.put("año", new HashMap<String, String>(){{put("tipo", "varchar");}});
            //tempMap.put("periodo", new HashMap<String, String>(){{put("tipo", "varchar");}});
            tempMap.put("fec_envio", new HashMap<String, String>(){{put("tipo", "date");}});
            tempMap.put("ubigeo", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "6");}});
            tempMap.put("nom_ca", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "140");}});
            tempMap.put("num_ca", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec1", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec2", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec3", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec4", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec5", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec6", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec6_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec6_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec1_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec1_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec2_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_ben_ejec2_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad0", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad0_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad0_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad1", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad1_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad1_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad2", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad2_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad2_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad3", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad3_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad3_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad4", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad4_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad4_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad5", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad5_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad5_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad6", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad6_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad6_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad7", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad7_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad7_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad8", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad8_h", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_grup_edad8_m", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador1", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador2", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador3", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador4", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador5", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador6", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador7", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador8", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador9", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador10", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador11", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador12", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador13", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador14", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador15", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador16", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador17", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador18", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador19", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_indicador20", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_inv_ejec", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("num_inv_pim", new HashMap<String, String>(){{put("tipo", "number");}});
            tempMap.put("depart_ca", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "40");}});
            tempMap.put("provin_ca", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "40");}});
            tempMap.put("distr_ca", new HashMap<String, String>(){{put("tipo", "varchar");put("size", "40");}});

            camposReporteServicios = Collections.unmodifiableMap(tempMap);
    }
    
    public static Map<String, String> meses;
    
    static{
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("01", "Enero");
        tempMap.put("02", "Febrero");
        tempMap.put("03", "Marzo");
        tempMap.put("04", "Abril");
        tempMap.put("05", "Mayo");
        tempMap.put("06", "Junio");
        tempMap.put("07", "Julio");
        tempMap.put("08", "Agosto");
        tempMap.put("09", "Setiembre");
        tempMap.put("10", "Octubre");
        tempMap.put("11", "Noviembre");
        tempMap.put("12", "Diciembre");
        meses = Collections.unmodifiableMap(tempMap);
    }
    
}
