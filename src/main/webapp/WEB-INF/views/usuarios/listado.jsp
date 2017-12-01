<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <h2 class="inline"> Usuarios</h2>
        <p>Usuarios con acceso al Panel de administración con sus diferentes privilegios.</p> 
    </div>
    <!-- Start .content-inner -->
    <div class="content-inner">
        <!-- Start .row -->
         <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="panel panel-default plain" id="jst_0"> 
                    <div class="panel-body">
                        <c:if test="${sessionScope.ingreso_correcto == true}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Correcto!</strong> El usuario se registro correctamente
                        </div>
                        <%  
                            request.getSession().removeAttribute("ingreso_correcto");
                        %>
                        </c:if>
                        <c:if test="${sessionScope.actualizacion_correcta == true}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Correcto!</strong> El usuario se actualizo correctamente
                        </div>
                        <%  
                            request.getSession().removeAttribute("actualizacion_correcta");
                        %>
                        </c:if>
                        <div class="table-responsive"><table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="per5">
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><div class="icheckbox_minimal-teal" style="position: relative;"><input class="check-all" type="checkbox" value="option1" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </th>
                                    <th class="per40">Empleado</th>
                                    <th class="per40">Cargo</th>
                                    <th class="per15">Privilegio</th>
                                    <th class="per15">Servicio</th>
                                    <th class="per15">Departamento</th>
                                    <th class="per15">Editar</th>
                                    <th class="per15">Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${usuarios}" var="usuario">
                                <tr>
                                    <td>
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><div class="icheckbox_minimal-teal" style="position: relative;"><input class="check" type="checkbox" value="option2" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </td>
                                    <td>${usuario.nombre} ${usuario.apellidos}</td>
                                    <td>${usuario.cargo}</td>
                                    <td>${usuario.descrip}</td>
                                    <td>${usuario.nom_serv}</td>
                                    <td>${usuario.nom_dep}</td>
                                    <td><a href="<c:url value='/pages/usuarios/editar'/>?idusuario=${usuario.idusuario}">Editar</a></td>
                                    <td><a href="<c:url value='/pages/usuarios/eliminar'/>?idusuario=${usuario.idusuario}">Eliminar</a></td>
                                </tr>
                                
                                </c:forEach>
                                
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