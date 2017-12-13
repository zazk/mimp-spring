<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Aprobaciones</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Aprobaciones</h5>
        </div>    
    </div>
    
    <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-12">
            <button id="readAappRequisicion" type="button" class="btn btn-success">Read AappRequisicion</button>
            <button id="updateAappRequisicion" type="button" class="btn btn-success">Update AappRequisicion</button>
            <br><br>
            <p id="respuesta"></p>
        </div>
                
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idAappRequisicion</label>
                 <input class="form-control" id="idAappRequisicion" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idRequisicionP</label>
                 <input class="form-control" id="idRequisicionP" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">estado</label>
                 <input class="form-control" id="estado" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idUserJefe</label>
                 <input class="form-control" id="idUserJefe" type="number">             
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
                var readAappRequisicion = $('#readAappRequisicion');
                var updateAappRequisicion = $('#updateAappRequisicion');
                //Input
                var idAappRequisicion = $('#idAappRequisicion');
                var idRequisicionP = $('#idRequisicionP');
                var estado = $('#estado');
                var idUserJefe = $('#idUserJefe');
               
               readAappRequisicion.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readAappRequisicion',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            var fecha;
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    fecha =(e.FEC_APROBA === null)?'AUN NO SE APRUEBA':e.FEC_APROBA;
                                    return '' + e.IDREQUISICIONP + ' - ' + e.DEPENDENCIA +
                                            ' - ' + e.PUESTO + ' - ' + e.NROPUESTOS +
                                            ' - ' + e.MONTOMES + '-  ' + e.JEFEORGANO +
                                            ' - ' + e.DIRECTOREJECUTIVO + ' - ' + e.CERTIFICACION +
                                            ' - ' + fecha +
                                            ' - ' + e.TIPOREQUERIMIENTO +
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
               });//read AappRequisicion
               
               updateAappRequisicion.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idAappRequisicion: idAappRequisicion.val(),
                                idRequisicionP: idRequisicionP.val(),
                                estado: estado.val(),
                                idUserJefe: idUserJefe.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateAappRequisicion',
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
               });//Update AappRequisicion
            });//Jquery

        </script>
</body>
</html>