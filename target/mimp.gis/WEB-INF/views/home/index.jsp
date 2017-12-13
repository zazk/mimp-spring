<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Cronograma</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Cronograma</h5>
        </div>    
    </div>
    
    <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-12">
            <button id="createCertificaPpto" type="button" class="btn btn-success">Create CertificaPpto</button>
            <button id="readCertificaPpto" type="button" class="btn btn-success">Read CertificaPpto</button>
            <button id="updateCertificaPpto" type="button" class="btn btn-success">Update CertificaPpto</button>
            <br><br>
            <p id="respuesta"></p>
        </div>
                        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idCodPpto</label>
                 <input class="form-control" id="idCodPpto" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idRequisicion</label>
                 <input class="form-control" id="idRequisicion" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">nroCertificacion</label>
                 <input class="form-control" id="nroCertificacion" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fteFto</label>
                 <input class="form-control" id="fteFto" type="number">             
            </div>              
        </div>        
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idMetaPpto</label>
                 <input class="form-control" id="idMetaPpto" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">creditoPptal</label>
                 <input class="form-control" id="creditoPptal" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">observac</label>
                 <input class="form-control" id="observac" type="text">             
            </div>              
        </div>
               
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">estadoProceso</label>
                 <input class="form-control" id="estadoProceso" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">usu_crea</label>
                 <input class="form-control" id="usu_crea" type="number">             
            </div>              
        </div>
</div>

        <script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-1.11.1.min.js'/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-ui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/bootstrap.js'/>"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {
                //Respuesta de las transacciones
                $('#respuesta').html('Ready');
                
                //Botones
                var createCertificaPpto = $('#createCertificaPpto');
                var readCertificaPpto = $('#readCertificaPpto');
                var updateCertificaPpto = $('#updateCertificaPpto');

                //Input
                var idCodPpto = $('#idCodPpto');
                var idRequisicion = $('#idRequisicion');
                var nroCertificacion = $('#nroCertificacion');
                var fteFto = $('#fteFto');
                var idMetaPpto = $('#idMetaPpto');
                var creditoPptal = $('#creditoPptal');
                var observac = $('#observac');
                var estadoProceso = $('#estadoProceso');
                var usu_crea = $('#usu_crea');
               
               createCertificaPpto.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idRequisicion: idRequisicion.val(),
                                nroCertificacion: nroCertificacion.val(),
                                fteFto: fteFto.val(),
                                idMetaPpto: idMetaPpto.val(),
                                creditoPptal: creditoPptal.val(),
                                observac: observac.val(),
                                estadoProceso: estadoProceso.val(),
                                usu_crea: usu_crea.val()
                              };                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'createCertificaPpto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuesta').html(
                                'Mensaje: ' + resultado.map(function(e)
                                {
                                    return e.mensaje;
                                }).join(', ') + '<br>Error: ' + resultado.map(function(e)
                                {
                                    return e.error;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create CertificaPpto
               
               readCertificaPpto.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readCertificaPpto',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            var fecha;
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    fecha =(e.FEC_APROBA === null)?'AUN NO SE APRUEBA':e.FEC_APROBA;
                                    return '' + e.IDCODPPTO + ' - ' + e.DEPENDENCIA +
                                            ' - ' + e.PUESTO + ' - ' + e.NROPUESTOS +
                                            ' - ' + e.MONTOMES + '-  ' + e.NROCERTIFICACION +
                                            ' - ' + e.FTEFTO + ' - ' + e.META + ' - ' + e.CREDITOPPTAL +
                                            ' - ' + e.OBSERVAC + ' - ' + e.FE_CREA +
                                            ' - ' + e.ESTADO +
                                            '<br>****************************************************' +
                                            '********************************************************' +
                                            '**********************************************************';
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//read CertificaPpto
               
               updateCertificaPpto.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idCodPpto: idCodPpto.val(),
                                estadoProceso: estadoProceso.val()
                              };
                   
                   console.log(data);
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateCertificaPpto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuesta').html(
                                'Mensaje: ' + resultado.map(function(e)
                                {
                                    return e.mensaje;
                                }).join(', ') + '<br>Error: ' + resultado.map(function(e)
                                {
                                    return e.error;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown)
                        {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update CertificaPpto
            });//Jquery

        </script>
</body>
</html>