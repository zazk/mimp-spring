<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Notificaciones</h2>

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
                    </div>
                    <div class="panel-body">

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="per5">
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><input class="check-all" type="checkbox" value="option1" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </th>
                                    <th class="per40">Usuario</th>
                                    <th class="per40">Acción</th>
                                    <th class="per15">Mensaje</th> 
                                    <th class="per15">Fecha</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${notificaciones}" var="notificacion">
                                <tr>
                                    <td>
                                        <label class="checkbox">
                                            <div class="icheckbox_minimal-teal" style="position: relative;"><input class="check" type="checkbox" value="option2" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                        </label>
                                    </td>
                                    <td><a href="">${notificacion.nombre} ${notificacion.apellidos}</a></td>
                                    <td>${notificacion.idaccion}</td>
                                    <td>${notificacion.mensaje}</td>
                                    <td><a href="">${notificacion.fec_notificacion}</a></td>
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