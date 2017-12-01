<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Centros de Atención</h2>

    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="<c:url value='/pages/usuarios/panel'/>" class="btn btn-success">Regresar</a>  
                        </div> 
                    </div>
                    
                    <div class="panel-body">
                        <c:if test="${sessionScope.actualizacion_correcta != null}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Correcto!</strong> El centro de atención ${centroatencion.cod_ca} se actualizó correctamente
                          <c:if test="${centroatencion.geo_exists != null}">
                          <a href="http://181.65.173.175:8080/mimp.gis/pages/home/index?x=${centroatencion.coord_x}&y=${centroatencion.coord_y}" target="_blank">Ver en el mapa</a>
                          </c:if>
                        </div>
                        <%  
                            request.getSession().removeAttribute("actualizacion_correcta");
                        %>
                        </c:if>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="per15">Código</th>
                                    <th class="per25">Nombre</th>
                                    <th class="per45">Dirección</th>
                                    <th class="per15">Editar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${centrosatencion}" var="centroatencion">
                                <tr>
                                    <td>${centroatencion.cod_ca}</td>
                                    <td>${centroatencion.nom_ca}</td>
                                    <td>${centroatencion.dir_ca}</td>
                                    <td><a href="<c:url value='/pages/centrosatencion/editar'/>?cod_ca=${centroatencion.cod_ca}">Editar</a></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
</div>