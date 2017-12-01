<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Actualizar Información</h2>

    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="<c:url value='/pages/usuarios/historico'/>" class="btn btn-primary ">Regresar</a>   
                        </div> 
                        <h3>Aprobar archivo enviado</h3>
                        <div>
                            <strong>Periodo:</strong>
                            <span>${historico.nom_periodo}</span>
                        </div>
                        <div>
                            <strong>Ano:</strong>
                            <span>${historico.ano}</span>
                        </div>
                        <div>
                            <strong>Fecha de envio:</strong>
                            <span>${historico.fec_his}</span>
                        </div>
                        <div>
                            <strong>Nombre de archivo:</strong>
                            <span>${historico.nom_archivo}</span>
                        </div>
                        <div>
                            <strong>Estado:</strong>
                            <span><span class="label ${estadosArchivo.get(historico.cod_estado).get("clase")}">
                                    ${estadosArchivo.get(historico.cod_estado).get("nombre")}</span>
                            </span>
                        </div> 
                    </div>
                    <div class="panel-body">
                        <form:form method="post" action="historico_aprobar" > 
                          
                            <input type="hidden" name="cod_his" value="${historico.cod_his}" />
                            <button type="submit" name="estado" value="3" class="btn btn-success">Aprobar Temporal</button>
                            <c:if test="${historico.cod_estado=='1' || historico.cod_estado=='2'}" > 
                                <button type="submit" name="estado" value="3" class="btn btn-success">Aprobar</button>
                                <button type="submit" name="estado" value="5" class="btn btn-danger">Rechazar</button>
                            </c:if> 
                            <c:if test="${historico.cod_estado=='3' || historico.cod_estado=='4'}" > 
                                <button type="submit" name="estado" value="6" class="btn btn-warning">Revertir </button> 
                            </c:if> 
                        </form:form> 
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
    </div>