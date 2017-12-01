package pe.gob.mimp.gis.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import pe.gob.mimp.gis.dao.ExcelReaderDao;
import pe.gob.mimp.gis.entity.ExcelReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mimp.gis.commons.Constantes;


/**
 *
 * @author Luis
 */
@Repository
public class ExcelReaderDaoImpl extends SimpleJdbcDaoSupport implements ExcelReaderDao {

    private static Logger log = LoggerFactory.getLogger(ExcelReaderDaoImpl.class);

    @Autowired
    public ExcelReaderDaoImpl(DataSource dataSource) {
        log.info("Asignando el dataSource");
        setDataSource(dataSource);
    }

    @Override
    public void insertar(ExcelReader excelReader) {
        Map<String, String> headersTabla = new HashMap<String, String>();
        //log.info("ExcelReaderDaoImpl Filas: " + excelReader.getCellDataList().size());
        String sqlCampos = "";
        String sqlValues = "";
        String cod_ca="", ubigeo="", cod_serv = "", updateFields ="", cod_entidad="", cod_linea="";
        
        int totalCabeceras = 0;
        try{
            for (int i = 0; i < excelReader.getCellDataList().size(); i++){
                //System.out.println("Procesando la fila[" + i + "]: ");
                List cellTempList = (List) excelReader.getCellDataList().get(i);

                System.out.println("************************************************************************");
                System.out.println("Procesando la fila de datos[" + i + "]: ");
                sqlValues = "";
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxSize Fila: " + cellTempList.size());

                if(i == 0){
                    //System.out.println("Procesando la fila de cabecera[" + i + "]: ");
                    //Generamos el sql de values
                    boolean first = true;
                    sqlCampos = "";
                    for (int j = 0; j < cellTempList.size(); j++){
                        HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
                        String stringCampo = hssfCell.toString().toLowerCase();
                        totalCabeceras ++;

                        HashMap dataCampo = Constantes.camposReporteServicios.get(stringCampo);
                        if(dataCampo == null){
                            continue;
                        }
                        if(first){
                            first = false;
                            sqlCampos += stringCampo;
                        }else{
                            sqlCampos += ", " + stringCampo;
                        }
                        headersTabla.put("pos_" + j, stringCampo);
                    }
                    //System.out.println("Array Headers: " + headersTabla);
                }else{ 
                    sqlValues = ""; 
                    boolean first = true;
                    //for (int k = 0; k < headersTabla.size(); k++){
                    for (int k = 0; k < totalCabeceras; k++){
                        //System.out.println("Procesando el campo[" + i + "][" + k + "] ");

                        //Recuperamos el campo que es
                        String campoActual = headersTabla.get("pos_" + k);
                        //System.out.println("Campo Actual[" + k + "]: " + campoActual);
                        HashMap dataCampo = Constantes.camposReporteServicios.get(campoActual);
                        //System.out.println("Data Campo Actual[" + k + "]: " + dataCampo);

                        if(dataCampo == null){
                            continue;
                        }

                        String stringValue = "";
                        //Recuperamos el valor del campo
                        try{
                            HSSFCell hssfCell = (HSSFCell) cellTempList.get(k);
                            stringValue = hssfCell.toString().trim();
                        }catch(Exception e){
                            stringValue = "";
                        }

                        //DateFormat para los campos de date
                        SimpleDateFormat formatoExcel = new SimpleDateFormat("dd-MMM-yyyy");
                        SimpleDateFormat formatoOracle = new SimpleDateFormat("yyyy/MM/dd");

                        if(first == true){
                            first = false;
                        }else{
                            sqlValues += ", ";
                        }

                        if(dataCampo.get("tipo").equals("varchar")){
                            if(stringValue.equals("") && dataCampo.get("defecto") != null){
                                stringValue = dataCampo.get("defecto").toString();
                            }
                             
                                System.out.println(">>>>>>>>>> VARCHAR");
                            if( dataCampo.get("size") != null ){      

                                int size =Integer.parseInt( dataCampo.get("size").toString() );
                                int length = stringValue.length();
                                System.out.println("Size>"+size+" Lenght>"+length);
                                stringValue = ( length > size    )? stringValue.substring(0, size )  : stringValue ;
                            } 
                                 
                           sqlValues += "q'[" + stringValue + "]'";
                        }else if(dataCampo.get("tipo").equals("date")){
                            try {
                                Date date = formatoExcel.parse(stringValue);
                                String dateFormatted = formatoOracle.format(date);
                                sqlValues += "TO_DATE('" + dateFormatted + "', 'yyyy/mm/dd')";
                            } catch (ParseException ex) {
                                java.util.logging.Logger.getLogger(ExcelReaderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else if(dataCampo.get("tipo").equals("number")){
                            if(stringValue.equals("")){
                                sqlValues += "0";
                            }else{
                                sqlValues += stringValue;
                            }
                        }

                        //if (k ==1){
                            System.out.println("..............Campo Actual[" + k + "]: " + stringValue.toString() + " - Data Campo:" + campoActual);
                        //}
                        //Rastrear Los campos.
                        //COD_SERV
                        if (k == 8){ 
                            cod_serv = stringValue.toString() ;
                        }
                        //UBIGEO
                        if (k == 10){ 
                            ubigeo = stringValue.toString() ; 
                        }
                        //COD_CA
                        if (k == 14){ 
                            cod_ca = stringValue.toString() ;
                        }
                    }

                    //Query para eliminar antiguo registro
                    updateFields = "DELETE FROM GEO_REPORTE_SERVICIOS WHERE ANO='"+excelReader.getAnio()
                            +"' AND COD_CA='"+ cod_ca +"' AND UBIGEO='"+ubigeo+"' AND COD_SERV='"+cod_serv
                            +"'  AND COD_ENTIDAD='"+cod_entidad+"' AND COD_LINEA='"+cod_linea+"'";
                    //System.out.println("*********************************************");
                    //System.out.println(updateFields);
                    //Ejecutar el campo
                    getJdbcTemplate().execute(updateFields); 

                    getJdbcTemplate().update("INSERT INTO GEO_REPORTE_SERVICIOS (" 
                            + sqlCampos + ", ano, periodo,cod_his ) VALUES (" 
                            + sqlValues + ", '" 
                            + excelReader.getAnio() + "', '" 
                            + excelReader.getPeriodo()  + "', " 
                            + excelReader.getCodigo() + " )"); 
                    //System.out.println("Consulta de ingreso: INSERT INTO GEO_REPORTE_SERVICIOS (" + sqlCampos + ") VALUES (" + sqlValues + ")");
                }
            }
        }catch(Exception e){ 
            log.info("Error Parsing Excel");
        }
    }
    
    public void insertar2007(ExcelReader excelReader) {
        Map<String, String> headersTabla = new HashMap<String, String>();
        log.info("ExcelReaderDaoImpl Filas: " + excelReader.getCellDataList().size());
        String sqlCampos = "";
        String sqlValues = "";
        String cod_ca="", ubigeo="", cod_serv = "", updateFields ="", cod_entidad="", cod_linea="";
        
        int totalCabeceras = 0;
        for (int i = 0; i < excelReader.getCellDataList().size(); i++){
            System.out.println("*******************************************************");
            System.out.println("Procesando la fila[" + i + "]: ");
            List cellTempList = (List) excelReader.getCellDataList().get(i);
            
            if(i == 0){
                //System.out.println("Procesando la fila de cabecera[" + i + "]: ");
                //Generamos el sql de values
                boolean first = true;
                sqlCampos = "";
                for (int j = 0; j < cellTempList.size(); j++){
                    //HSSFCell cell = (HSSFCell) cellTempList.get(j);
                    //XSSFCell cell = (XSSFCell) cellTempList.get(j);
                    //String stringCampo = cell.toString().toLowerCase();
                    String stringCampo = ( (String)cellTempList.get(j) ).toLowerCase();
                    totalCabeceras ++;
                    
                    System.out.println("Procesando la fila de cabecera[" + i + "]: " + stringCampo);
                    
                    HashMap dataCampo = Constantes.camposReporteServicios.get(stringCampo);
                    if(dataCampo == null){
                        continue;
                    }
                    if(first){
                        first = false;
                        sqlCampos += stringCampo;
                    }else{
                        sqlCampos += ", " + stringCampo;
                    }
                    headersTabla.put("pos_" + j, stringCampo);
                }
                //System.out.println("Array Headers: " + headersTabla);
            }else{
                //System.out.println("Procesando la fila de datos[" + i + "]: ");
                sqlValues = "";
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxSize Fila: " + cellTempList.size());
                boolean first = true;
                //for (int k = 0; k < headersTabla.size(); k++){
                for (int k = 0; k < totalCabeceras; k++){
                    //System.out.println("Procesando el campo[" + i + "][" + k + "] ");

                    //Recuperamos el campo que es
                    String campoActual = headersTabla.get("pos_" + k);
                    //System.out.println("Campo Actual[" + k + "]: " + campoActual);
                    HashMap dataCampo = Constantes.camposReporteServicios.get(campoActual);
                    //System.out.println("Data Campo Actual[" + k + "]: " + dataCampo + " Campo Actual: " + campoActual);


                    if(dataCampo == null){
                        
                        //System.out.println("--------- Continue dataCampo==null");
                        continue;
                    }
                    
                    //System.out.println("--------- Continue YESSSSSSSSSSSSS");
                        
                    String stringValue = "";
                    //Recuperamos el valor del campo
                    try{
                        //HSSFCell hssfCell = (HSSFCell) cellTempList.get(k);
                        //stringValue = hssfCell.toString().trim();
                        //XSSFCell cell = (XSSFCell) cellTempList.get(k);
                        //stringValue = cell.toString().trim();
                        stringValue = (String)cellTempList.get(k);
                    }catch(Exception e){
                        stringValue = "";
                    }
                    
                    //DateFormat para los campos de date
                    SimpleDateFormat formatoExcel = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat formatoOracle = new SimpleDateFormat("yyyy/MM/dd");
                            
                    if(first == true){
                        first = false;
                    }else{
                        sqlValues += ", ";
                    }
                    //System.out.println("..............Campo Actual[" + k + "]: " + stringValue.toString() + " - Data Campo:" + campoActual);
 
                    if(dataCampo.get("tipo").equals("varchar")){
                        if(stringValue.equals("") && dataCampo.get("defecto") != null){
                            stringValue = dataCampo.get("defecto").toString();
                        }
                        sqlValues += "q'[" + stringValue + "]'";
                    }else if(dataCampo.get("tipo").equals("date")){
                        try {
                            Date date = formatoExcel.parse(stringValue);
                            String dateFormatted = formatoOracle.format(date);
                            sqlValues += "TO_DATE('" + dateFormatted + "', 'yyyy/mm/dd')";
                        } catch (ParseException ex) {
                            java.util.logging.Logger.getLogger(ExcelReaderDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(dataCampo.get("tipo").equals("number")){
                        if(stringValue.equals("")){
                            sqlValues += "0";
                        }else{
                            sqlValues += stringValue;
                        }
                    }
                    
                    //if (k ==1){
                    //}
                    //Rastrear Los campos.
                    //COD_SERV
                    if (k == 8){ 
                        cod_serv = stringValue.toString() ;
                    }
                    //UBIGEO
                    if (k == 10){ 
                        ubigeo = stringValue.toString() ;
                    }
                    //COD_CA
                    if (k == 14){ 
                        cod_ca = stringValue.toString() ;
                    }
                    
                    //System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx END COLUMN");
                }
                
                log.info("------------Total Columnas: " + totalCabeceras);
                //Query para eliminar antiguo registro
                updateFields = "DELETE FROM GEO_REPORTE_SERVICIOS WHERE ANO='"+excelReader.getAnio()
                        +"' AND COD_CA='"+ cod_ca +"' AND UBIGEO='"+ubigeo+"' AND COD_SERV='"+cod_serv
                        +"'  AND COD_ENTIDAD='"+cod_entidad+"' AND COD_LINEA='"+cod_linea+"'";
                //System.out.println("*********************************************");
                //System.out.println(updateFields);
                //Ejecutar el campo
                getJdbcTemplate().execute(updateFields); 
                
                getJdbcTemplate().update( "INSERT INTO GEO_REPORTE_SERVICIOS (" 
                        + sqlCampos + ", ano, periodo,cod_his ) VALUES (" + sqlValues + ", '" 
                        + excelReader.getAnio() + "', '" 
                        + excelReader.getPeriodo() + "', " 
                        + excelReader.getCodigo() +" )"); 
                //System.out.println("Consulta de ingreso: INSERT INTO GEO_REPORTE_SERVICIOS (" + sqlCampos + ") VALUES (" + sqlValues + ")");
                
            }
        }
    }
}