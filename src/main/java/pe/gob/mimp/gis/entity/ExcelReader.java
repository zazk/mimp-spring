/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luis
 */
@XmlRootElement
public class ExcelReader implements Serializable{
    
    private String fileName;
    private List cellDataList;
    private String anio;
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    private String periodo;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List getCellDataList() {
        return cellDataList;
    }

    public void setCellDataList(List cellDataList) {
        this.cellDataList = cellDataList;
    }
    
    public void readExcelFile(){

        this.cellDataList = new ArrayList();
        try{
            FileInputStream fileInputStream = new FileInputStream(this.getFileName());
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

            HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            HSSFSheet hssfSheet = workBook.getSheetAt(0);

            Iterator rowIterator = hssfSheet.rowIterator();
            
            System.out.println("Iniciando iteracion sobre Excel: " + fileName);
            
            int rowC;
            rowC = 0;
            while (rowIterator.hasNext()){
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                List cellTempList = new ArrayList();
                int colC;
                colC = 0;
                //System.out.println("Columna: " + rowC);
                while (iterator.hasNext()){
                    HSSFCell hssfCell = (HSSFCell) iterator.next();
                    cellTempList.add(hssfCell);
                    colC++;
                }
                cellDataList.add(cellTempList);
                
                rowC++;
            }
            
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Error al intentar leer el archivo: " + fileName + " con ExcelSheetReader" + e.getMessage());
        }

        //printToConsole(cellDataList);
    }
    
    
    public void readExcelFile2007(){

        this.cellDataList = new ArrayList();
        try{
            FileInputStream fileInputStPOIFSFileSystemream = new FileInputStream(this.getFileName());
            //POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

            // 2007 Get the workbook instance for XLS file 
            XSSFWorkbook workbook = new XSSFWorkbook (fileInputStPOIFSFileSystemream);
            //HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
            
            //HSSFSheet sheet = workBook.getSheetAt(0);
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterator rowIterator = sheet.rowIterator();
            
            System.out.println("Iniciando iteracion sobre Excel: " + fileName);
            
            int rowNum = 788;//sheet.getLastRowNum() + 1;
            int colNum = sheet.getRow(0).getLastCellNum();
            String [][] data = new String [rowNum] [colNum];
            
            for(int i = 0; i <rowNum; i++){
                System.out.println ("===========================================");
                System.out.println ("Start bucle");     
                XSSFRow row = sheet.getRow(i);
                List cellTempList = new ArrayList(); 
                for (int j = 0; j < colNum; j++){ 
                    System.out.println ("Follow J:" + j); 
                    XSSFCell cell = row.getCell(j); 
                    String value;
                    if (cell != null){
                        value = cell.toString();
                    }else{
                        value = "";
                    }
                    System.out.println ("Follow bucle:" + cell);
                    data[i][j] = value;
                    cellTempList.add(value);
                    System.out.println ("Last Line:---------------");
                }
                System.out.println ("the value is " + i + " Row Num Total-" + rowNum);     
                
                cellDataList.add(cellTempList);
            }
            
            //int rowC;
            //rowC = 0;
            //while (rowIterator.hasNext()){
                //HSSFRow row = (HSSFRow) rowIterator.next();
              //  XSSFRow row = (XSSFRow) rowIterator.next();
                
                //Iterator iterator = row.cellIterator();
                //List cellTempList = new ArrayList();
                /*
                int colC;
                colC = 0;
                //System.out.println("Columna: " + rowC);
                while (iterator.hasNext()){
                    //HSSFCell cell = (HSSFCell) iterator.next();
                    XSSFCell cell = (XSSFCell) iterator.next();
                    cellTempList.add(cell);
                    colC++;
                }
                */
                //cellDataList.add(cellTempList);
                
                //rowC++;
            //}
            
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Error al intentar leer el archivo: " + fileName + " con ExcelSheetReader" + e.getMessage());
        }

        //printToConsole(cellDataList);
    }
}
