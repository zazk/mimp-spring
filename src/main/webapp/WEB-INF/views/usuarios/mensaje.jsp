<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 

<div class="content-wrapper">
    <div id="page-heading" class="page-header">
        <div class="w-help">
            
        </div>
        <h2 class="inline">Mensaje</h2>

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
                        <h3 class="pull-left">Detalle del mensaje</h3>
                    </div>
                    <div class="panel-body"> 
                          <div class="form-group">
                            <label for="usuario">Asunto</label>
                            ${mensaje.asunto}
                          </div>
                          <div class="form-group">
                            <label for="exampleInputFile">Mensaje</label>
                            ${mensaje.contenido}
                          </div>
                    </div>
                </div> 
            </div>
        </div>
        <!-- End .row -->
    </div>
    <!-- End .content-inner -->
</div>