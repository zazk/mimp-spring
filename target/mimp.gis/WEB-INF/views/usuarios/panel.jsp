<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
    <div class="content-wrapper">
        <div id="page-hea ding" class="page-header">
            <h2 class="inline"> Panel</h2>
            <p>Estado de Presentación de las Áreas..</p> 
        </div>
        <!-- Start .content-inner -->
        <div class="content-inner">
            <!-- Start .row -->
             <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="panel panel-default plain" id="jst_0"> 
                        <div class="panel-body">
                            <a href="<c:url value='/pages/usuarios/enviar'/>" class="btn btn-success">Enviar información</a>
                            <div class="table-responsive"><table class="table">
                                <thead>
                                    <tr>
                                        <th class="per5">
                                            <label class="checkbox">
                                                <div class="icheckbox_minimal-teal" style="position: relative;"><div class="icheckbox_minimal-teal" style="position: relative;"><input class="check-all" type="checkbox" id="inlineCheckbox1" value="option1" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                            </label>
                                        </th>
                                        <th class="per35">Área</th>
                                        <th class="per15">Fecha de Actualización</th>
                                        <th class="per15">Aprobación</th>
                                        <th class="per15">Periodo</th>
                                        <th class="per20">Usuario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${entidades}" var="entidad">
                                        
                                    <tr>
                                        <td>
                                            <label class="checkbox">
                                                <div class="icheckbox_minimal-teal" style="position: relative;"><div class="icheckbox_minimal-teal" style="position: relative;"><input class="check" type="checkbox" value="option2" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div>
                                            </label>
                                        </td>
                                        <td><a href="<c:url value='/pages/usuarios/historico'/>?cod_entidad=${entidad.cod_entidad}">${entidad.nom_entidad}</a></td>
                                        <td>${entidad.fec_his}</td>
                                        <td><span class="label ${estadosArchivo.get(entidad.cod_estado).get("clase")}">${estadosArchivo.get(entidad.cod_estado).get("nombre")}</span></td>
                                        <td>${entidad.nom_periodo}</td>
                                        <td>${entidad.nombre} ${entidad.apellidos}</td>
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

