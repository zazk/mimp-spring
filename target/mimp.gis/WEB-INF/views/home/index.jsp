<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Pre Convocatoria</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Pre Convocatoria</h5>
        </div>    
    </div>
    
    <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="readPreConvocatoria" type="button" class="btn btn-success">Read PreConvocatoria</button>
            <button id="anularConvocatoria" type="button" class="btn btn-success">Anular Convocatoria</button>            
            <br><br>
            <p id="respuesta"></p>
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idConvocatoria</label>
                 <input class="form-control" id="idConvocatoria" type="number">             
            </div>              
        </div>        
                
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idUsuarioModi</label>
                 <input class="form-control" id="idUsuarioModi" type="number">             
            </div>              
        </div>
        
    </div>
    
    <hr><br>
    
    <form id="formulario" method="post" action="createBasesP" enctype="multipart/form-data">
    <div class="row">
        <div class="col-12 form-group">
            <button id="createBasesP" type="submit" class="btn btn-success">Create BasesP</button>
        </div>        

        <div class="col-6 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idConvocatoria</label>
                 <input class="form-control" name="idConvocatoria" type="number">             
            </div>              
        </div> 
        
        <div class="col-6 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idTipoDoc</label>
                <input class="form-control" name="idTipoDoc" type="number" maxlength="2">
            </div>              
        </div>
        
        <div class="col-12 form-group">
             <div class="input-group">
                <label class="input-group-addon col-2">Ruta</label>
                <input class="form-control" name="ruta" type="file" required>
            </div>              
        </div>
    </div></form>

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
                var readPreConvocatoria = $('#readPreConvocatoria');
                var anularConvocatoria = $('#anularConvocatoria');
                var createBasesP = $('#createBasesP');

                //Input
                var idConvocatoria = $('#idConvocatoria');
                var idUsuarioModi = $('#idUsuarioModi');
               
               readPreConvocatoria.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readPreConvocatoria',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    var crono = (e.IDCRONOGRAMAP)?e.IDCRONOGRAMAP:'Aún no se crea cronograma';
                                    
                                    return  'IDCONVOCATORIA: '+ e.IDCONVOCATORIA + ' - IDCRONOGRAMAP: ' + crono +
                                            '- IDREQUISICIONP: '+ e.IDREQUISICIONP + ' - ESTADO: ' + e.ESTADO;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//read PreConvocatoria               
               
               anularConvocatoria.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idConvocatoria: [{ID: "1"}, {ID: "2"}],
                                idUsuarioModi: idUsuarioModi.val()
                              };                   
                   
                   console.log(data);
                   
                   $.ajax({
			type: 'POST',			
			url: 'anularConvocatoria',
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
               });//Anular Convocatoria            
            });//Jquery

        </script>
</body>
</html>