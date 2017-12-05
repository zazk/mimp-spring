<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

            <div class="login-wrapper">
                <ul id="myTab" class="nav nav-tabs nav-justified bn">
                    <li>
                        <a href="#log-in" data-toggle="tab">Ingresar</a>
                    </li>
                    <li class="">
                        <a href="#register" data-toggle="tab">Solicitar</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content bn">
                    <div class="tab-pane fade active in" id="log-in">
                        <c:if test="${sessionScope.login_incorrecto == true}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Error!</strong> El usuario o contraseña son incorrectos
                        </div>
                        <%  
                            request.getSession().removeAttribute("login_incorrecto");
                        %>
                        </c:if>
                        <c:if test="${sessionScope.solicitud_correcta == true}">
                        <div class="alert alert-success alert-dismissible" role="alert">
                          <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <strong>Correcto!</strong> Su solicitud ha sido enviada correctamente, nuestros especialistas se pondrán en contacto con usted.
                        </div>
                        <%  
                            request.getSession().removeAttribute("solicitud_correcta");
                        %>
                        </c:if>
                        <div class="social-buttons text-center mt25"> 
                        </div> 
                        
                        <form:form action="autenticar" method="post" commandName="credential"
                                   cssClass="form-horizontal mt0" id="login-form" role="form">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="text" name="email" id="email" class="form-control left-icon" value="" placeholder="Su correo electrónico">
                                    <i class="im-user s16 left-input-icon"></i>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="password" name="clave" id="clave" class="form-control left-icon" value="" placeholder="Su Contraseña">
                                    <i class="im-lock s16 left-input-icon"></i>
                                    <!--<span class="help-block"><a href="#"><small>Se olvidó su contraseña ?</small></a></span> -->
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
                                    <!-- col-lg-12 start here -->
                                    <label class="checkbox">
                                        <input type="checkbox" name="remember" id="remember" value="option">Recordarme?
                                    </label>
                                </div>
                                <!-- col-lg-12 end here -->
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-4 mb25">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-default pull-right" type="submit">Ingresar</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div> 
                        </form:form>  
                    </div>
                    <div class="tab-pane fade" id="register">
                        <form role="form" action="<c:url value='/pages/usuarios/solicitar_ingreso'/>" class="form-horizontal mt0" id="register-form" method="post">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="email1" name="email" type="email" class="form-control left-icon" placeholder="Escriba su correo">
                                    <i class="fa fa-envelope s16 left-input-icon"></i> 
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="nombres" name="nombres" type="text" class="form-control left-icon" placeholder="Escriba sus nombres">
                                    <i class="fa fa-envelope s16 left-input-icon"></i> 
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="apellidos" name="apellidos" type="text" class="form-control left-icon" placeholder="Escriba sus apellidos">
                                    <i class="fa fa-envelope s16 left-input-icon"></i> 
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <input id="telefono" name="telefono" type="text" class="form-control left-icon" placeholder="Escriba su telefono">
                                    <i class="fa fa-envelope s16 left-input-icon"></i> 
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                            <div class="form-group mb25">
                                <div class="col-lg-12">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-default btn-block">Solicite Ingreso</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
 