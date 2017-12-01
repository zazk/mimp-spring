/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mimp.gis.entity;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Luis
 */
public class FileUploadForm {
    private List<MultipartFile> files;
    private String observ_his;
    private String anio;

    private String nom_periodo_1;
    private String nom_periodo_2;

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
    
    public String getNom_periodo_1() {
        return nom_periodo_1;
    }

    public void setNom_periodo_1(String nom_periodo_1) {
        this.nom_periodo_1 = nom_periodo_1;
    }

    public String getNom_periodo_2() {
        return nom_periodo_2;
    }

    public void setNom_periodo_2(String nom_periodo_2) {
        this.nom_periodo_2 = nom_periodo_2;
    }
    

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getObserv_his() {
        return observ_his;
    }

    public void setObserv_his(String observ_his) {
        this.observ_his = observ_his;
    }

}
