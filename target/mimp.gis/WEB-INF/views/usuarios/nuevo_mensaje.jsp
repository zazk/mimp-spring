<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 

<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Enviar mensaje</h2>

    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="<c:url value='/pages/usuarios/mensajes'/>" class="btn btn-primary ">Regresar</a>   
                        </div> 
                        <h3 class="pull-left">Ingresar la información del mensaje</h3>
                    </div>
                    <div class="panel-body"> 
                        <c:if test="${sessionScope.error_enviar == true}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Error!</strong> No se pudo enviar el mensaje
                        </div>
                        <%  
                            request.getSession().removeAttribute("error_enviar");
                        %>  
                        </c:if>
                        <form role="form" action="<c:url value='/pages/usuarios/enviar_mensaje'/>" method="post">
                          <div class="form-group">
                            <label for="usuario">Asunto</label>
                            <input type="text" class="form-control" id="usuario" name="asunto" placeholder="Ingresar asunto">
                          </div>
                          <div class="form-group">
                                <label for="exampleInputFile">Mensaje</label>
                                <textarea class="form-control" rows="3" name="contenido"></textarea>
                                <p class="help-block">Opcional. Escribir un mensaje si desea realizar una aclaración.</p>
                          </div>
                          
                          <div class="form-group">
                            <label for="cod_entidad">Entidad destino</label>
                            <select id="cod_entidad" class="form-control" name="cod_entidad">
                                <option value="TODOS">Todos</option>
                                <c:forEach items="${entidades}" var="entidad">
                                <option value="${entidad.cod_entidad}">${entidad.nom_entidad}</option>
                                </c:forEach>
                            </select>
                          </div> 
                          <button type="submit" class="btn btn-default">Enviar</button>
                        </form>
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
</div>