<%@page import="pe.gob.mimp.gis.commons.Constantes"%>
<%@page import="java.io.File"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <h2 class="inline">Actualizar Centros de Atención</h2>
        <p>Actualice los datos del centro de atención indicado y presione el botón <strong>Actualizar</strong></p> 
    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
         <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="panel panel-default plain" id="jst_0"> 
                    <div class="panel-body">
                        <c:if test="${sessionScope.actualizacion_incorrecta != null}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Error!</strong> Hubo un error al intentar actualizar el centro de atención ${sessionScope.actualizacion_incorrecta}
                        </div>
                        <%  
                            request.getSession().removeAttribute("actualizacion_incorrecta");
                         %>
                        </c:if>
                        <div class="table-responsive"><table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="per10">Código</th>
                                    <th class="per40">Centro de Atención</th> 
                                    <th class="per50">Localización</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${centroatencion.cod_ca}</td>
                                    <td>${centroatencion.nom_ca}<br>
                                        ${centroatencion.dir_ca}, ${centroatencion.nom_dist}, ${centroatencion.nom_prov}, ${centroatencion.nom_dep}
                                        <c:if test="${centroatencion.foto_exists != null}">
                                        <br />  
                                        <img src="<c:url value='/files/centrosatencion'/>/${centroatencion.cod_ca}.jpg" width="250" />
                                        </c:if>
                                    </td>
                                    <td>
                                        <form:form method="post" action="actualizar" 
    modelAttribute="centroUploadForm" enctype="multipart/form-data">

                                            <div class="form-group">
                                              <label for="fieldA">Dirección</label>
                                              <input type="text" name="dir_ca" class="form-control" id="fieldA" 
                                                     value="${centroatencion.dir_ca}" placeholder="Dirección">
                                            </div> 
                                            <div class="form-group">
                                              <label for="fieldB">Referencia</label>
                                              <input type="text" name="ref_ca" class="form-control" id="fieldB" 
                                                     value="${centroatencion.ref_ca}" placeholder="Referencia">
                                            </div> 
                                            <div class="form-group">
                                              <label for="fieldC">Teléfono</label>
                                              <input type="text" name="telef_ca" class="form-control" id="fieldC" 
                                                     value="${centroatencion.telef_ca}" placeholder="Teléfono">
                                            </div> 
                                            <div class="form-group">
                                              <label for="fieldD">Coordenada X</label>
                                              <input type="text" name="coord_x" class="form-control" id="fieldD" 
                                                     value="${centroatencion.coord_x}" placeholder="Coordenada X">
                                            </div> 
                                            <div class="form-group">
                                              <label for="fieldE">Coordenada Y</label>
                                              <input type="text" name="coord_y" class="form-control" id="fieldE" 
                                                     value="${centroatencion.coord_y}" placeholder="Coordenada Y">
                                            </div>
                                            <div class="form-group">
                                              <label for="fieldE">Departamento</label>
                                              <strong>${centroatencion.nom_dep}</strong>
                                            </div>
                                            <div class="form-group">
                                              <label for="fieldE">Provincia</label>
                                              <strong>${centroatencion.nom_prov}</strong>
                                            </div>
                                            <div class="form-group">
                                              <label for="fieldE">Distrito</label>
                                              <strong>${centroatencion.nom_dist}</strong>
                                            </div>
                                            <c:if test="${centroatencion.geo_exists != null}">
                                            <div class="form-group">
                                              
                                              <a href="http://181.65.173.175:8080/mimp.gis/pages/home/index?x=${centroatencion.coord_x}&y=${centroatencion.coord_y}" target="_blank">Ver en el mapa</a>
                                            </div>
                                            </c:if>
                                            <div class="form-group">
                                              <label for="exampleInputFile">Foto</label>
                                              <input type="file" name="foto">
                                              <p class="help-block">Archivo en formato JPEG</p>
                                            </div>
                                            <input type="hidden" name="cod_ca" value="${centroatencion.cod_ca}" />
                                            <button type="submit" class="btn btn-default">Actualizar Centro</button>
                                        </form:form>
                                        
                                    </td>
                                </tr>
                                
                            </tbody>
                        </table></div>

                    </div>

                </div>    
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
</div>