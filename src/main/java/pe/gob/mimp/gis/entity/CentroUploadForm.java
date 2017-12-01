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
public class CentroUploadForm {
    private MultipartFile foto;
    private String cod_ca;
    private String dir_ca;
    private String ref_ca;
    private String telef_ca;
    private String coord_x;
    private String coord_y;

    public String getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(String coord_x) {
        this.coord_x = coord_x;
    }

    public String getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(String coord_y) {
        this.coord_y = coord_y;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public String getCod_ca() {
        return cod_ca;
    }

    public void setCod_ca(String cod_ca) {
        this.cod_ca = cod_ca;
    }

    public String getDir_ca() {
        return dir_ca;
    }

    public void setDir_ca(String dir_ca) {
        this.dir_ca = dir_ca;
    }

    public String getRef_ca() {
        return ref_ca;
    }

    public void setRef_ca(String ref_ca) {
        this.ref_ca = ref_ca;
    }

    public String getTelef_ca() {
        return telef_ca;
    }

    public void setTelef_ca(String telef_ca) {
        this.telef_ca = telef_ca;
    }


}
