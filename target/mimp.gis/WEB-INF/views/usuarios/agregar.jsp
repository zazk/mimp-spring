<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 

<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Agregar nuevo Usuario</h2>
        
    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
        <div class="row">                     
            <div class="col-lg-12 col-md-12 ">
                <div class="panel panel-primary plain" id="jst_0">
                    <div class="panel-heading panel-heading-wrap">
                        <div class="btn-group-gen"> 
                            <a href="<c:url value='/pages/usuarios/listado'/>" class="btn btn-primary ">Regresar</a>   
                        </div> 
                        <h3 class="pull-left">Ingresar la información del usuario</h3>
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
                        <form role="form" action="<c:url value='/pages/usuarios/ingresar'/>" method="post">
                          <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input type="text" class="form-control" id="usuario" name="usuario" placeholder="Ingresar usuario" maxlength="18">
                          </div>
                          <div class="form-group">
                            <label for="clave">Clave</label>
                            <input type="password" class="form-control" id="clave" name="clave" placeholder="Ingresar clave" maxlength="100">
                          </div>
                          <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar nombre" maxlength="18">
                          </div>
                          <div class="form-group">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Ingresar apellidos" maxlength="18">
                          </div>
                          <div class="form-group">
                            <label for="emailx">Email</label>
                            <input type="text" class="form-control" id="email" name="email" placeholder="Ingresar email" maxlength="200">
                          </div>
                          <div class="form-group">
                            <label for="cod_depar">Departamento</label>
                            <select id="cod_depar" name="cod_depar" class="form-control dd-child " data-child="#cod_servicio">
                                <option value="">Seleccionar...</option>
                                <c:forEach items="${departamentos}" var="departamento">
                                <option value="${departamento.cod_depar}">${departamento.nom_dep}</option>
                                </c:forEach>
                            </select>
                          </div>
                          <div class="form-group">
                            <label for="cod_servicio">Servicio</label>
                            <select id="cod_servicio" class="form-control" name="cod_servicio">
                                <option value="">Seleccionar...</option>
                            </select>
                          </div> 
                          <hr />
                          <div class="form-group">
                            <label for="idperfil">Perfil</label>
                            <select id="idperfil" class="form-control" name="idperfil">
                                <c:forEach items="${perfiles}" var="perfil">
                                <option value="${perfil.idperfil}">${perfil.descrip}</option>
                                </c:forEach>
                            </select>
                          </div> 
                          <div class="form-group">
                            <label for="cargo">Cargo</label>
                            <input type="text" class="form-control" id="cargo" name="cargo" placeholder="Ingresar cargo" maxlength="100">
                          </div>
                          <button type="submit" class="btn btn-default">Ingresar</button>
                        </form>
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
</div>