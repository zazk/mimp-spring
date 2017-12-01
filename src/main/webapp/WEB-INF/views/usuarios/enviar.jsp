<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Enviar Información</h2>

    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="#" class="btn btn-primary ">Regresar</a>   
                        </div> 
                        <h3 class="pull-left">Subir el archivo éxcel</h3>
                    </div>
                    <div class="panel-body">
                        <c:if test="${sessionScope.error_fechas == true}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Error!</strong> La fecha inicial debe ser menor o igual que la fecha final
                        </div>
                        <%  
                            request.getSession().removeAttribute("error_fechas");
                        %>  
                        </c:if>
                        <form:form method="post" action="procesar" 
    modelAttribute="uploadForm" enctype="multipart/form-data"> 
                          <div class="form-group">
                                <label for="exampleInputFile">Cuadro de envío</label>
                                <input type="file" name="files[0]">
                                <p class="help-block">Asegurarse que el archivo es el correcto.</p>
                          </div>
                          <div>
								<a href="../../files/PlantillaCargaGeoReporteServicios.xls" class="btn btn-primary btn-xs">Descargar Plantilla de Enviar información</a> 
                                <small class="help-block">Tener cuidado con el formato porque puede causar errores al procesar el archivo</small>
                          </div>
                          <div class="form-group">
                                <label for="exampleInputFile">Mensaje</label>
                                <textarea class="form-control" rows="3" name="observ_his"></textarea>
                                <p class="help-block">Opcional. Escribir un mensaje si desea realizar una aclaración.</p>
                          </div>
                          <div class="form-group">
                                <label for="exampleInputFile">Mes Inicial</label>
                                <select class="form-control" name="nom_periodo_1">
                                  <c:forEach items="${periodos}" var="periodo">
                                  <option value="${periodo.valor}">${periodo.etiqueta}</option> 
                                  </c:forEach>
                                </select>
                                <p class="help-block">Seleccione el periodo al cuál pertenece el archivo.</p>
                          </div>
                          <div class="form-group">
                                <label for="exampleInputFile">Mes Final</label>
                                <select class="form-control" name="nom_periodo_2">
                                  <c:forEach items="${periodos}" var="periodo">
                                  <option value="${periodo.valor}">${periodo.etiqueta}</option> 
                                  </c:forEach>
                                </select>
                                <p class="help-block">Si el periodo es un solo més. Poner el mismo mes que el inicio.</p>
                          </div>
                          <div class="form-group">
                                <label for="anio">Año</label>
                                <select class="form-control" id="anio" name="anio">
                                  <c:forEach items="${years}" var="year">
                                  <option value="${year}">${year}</option> 
                                  </c:forEach>"> 
                                </select>
                                <p class="help-block">El año del Periodo.</p>
                          </div>
                          <button type="submit" class="btn btn-default">Enviar</button>
                        </form:form>
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
    </div>