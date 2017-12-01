<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Historial</h2>

    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="<c:url value='/pages/usuarios/panel'/>" class="btn btn-primary ">Regresar</a>   
                        </div> 
                    </div>
                    <div class="panel-body">
                        <c:if test="${sessionScope.envio_correcto == true}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Correcto!</strong> El archivo ha sido enviado
                        </div>
                        <%  
                            request.getSession().removeAttribute("envio_correcto");
                        %>  
                        </c:if>
                        <c:if test="${sessionScope.historico_aprobado == true}">
                        <div class="alert alert-info alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Aprobado!</strong> El archivo ha sido aprobado
                        </div>
                        <%  
                            request.getSession().removeAttribute("historico_aprobado");
                        %>  
                        </c:if>
                        <c:if test="${sessionScope.historico_rechazado == true}">
                        <div class="alert alert-info alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Rechazado!</strong> El archivo ha sido rechazado
                        </div>
                        <%  
                            request.getSession().removeAttribute("historico_rechazado");
                        %>  
                        </c:if>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="per5">
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><input class="check-all" type="checkbox" value="option1" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </th>
                                    <th class="per40">Mes y Año</th>
                                    <th class="per40">Estado</th>
                                    <th class="per15">Comentarios</th> 
                                    <th class="per15">Descarga</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${historicos}" var="historico">
                                <tr>
                                    <td>
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><input class="check" type="checkbox" value="option2" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </td>
                                    <td><a href="<c:url value='/pages/usuarios/historico_editar'/>?cod_his=${historico.cod_his}">
                                            ${historico.nom_periodo} ${historico.ano}
                                        </a>
                                    </td>
                                    <td><span class="label ${estadosArchivo.get(historico.cod_estado).get("clase")}">${estadosArchivo.get(historico.cod_estado).get("nombre")}</span></td>
                                    <td>${historico.observ_his}</td>
                                    <td><a href="<c:url value='/pages/usuarios/historico_descargar'/>?cod_his=${historico.cod_his}"><span class="icon-download"></span> Descarga </a></td>
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