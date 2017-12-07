<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Datos Personales</title>
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/bootstrap.css'/>">
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/font-awesome.css'/>">
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/geomimp.css'/>">
        <link rel="stylesheet" href="<c:url value='/assetsvisor/css/perfect-scrollbar.min.css'/>">
</head>
<body class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Usuarios</h5>
            <button id="buscar" type="button" class="btn btn-success">Cargar Candidato</button>
            <button id="actualizarcandidato" type="button" class="btn btn-success">Actualizar Candidato</button>

            <br><br>
            <p id="respuesta"></p>
        </div>    
    </div>
    
    <div class="row">                    
        <div class=""col-md-12 form-group">
            <label>ID</label>
            <input id="idCandidato" type="number">             
                          
            <label>Tipo Identidad</label>
            <input id="idTipoIdentidad" type="number">
            
            <label>Nro Identidad</label>
            <input id="nroIdentidad" type="number">
            
            <label>Nombres</label>
            <input id="nombres" type="text">
            
            <label>Apellidos</label>
            <input id="apellidos" type="text">
            
            <label>Correo</label>
            <input id="correo_usuario" type="email">

            <label>Clave</label>
            <input id="clave_usuario" type="password">        
        </div>
    </div>
</div>

	<script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-1.11.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-ui.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assetsvisor/js/perfect-scrollbar.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/bootstrap.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/highcharts.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/modules/exporting.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assetsvisor/js/highcharts-3d.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/core.js'/>"></script>
        <script type="text/javascript" src="<c:url value='http://highcharts.com/js/testing-exporting.js'/>"></script>
        <script  src="<c:url value='https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false'/>"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {
                $('#respuesta').html('Ready');
                var buscar = $('#buscar');
                var actualizarcandidato = $('#actualizarcandidato');
                
                var idCandidato = $('#idCandidato');
                var idTipoIdentidad = $('#idTipoIdentidad');
                var nroIdentidad = $('#nroIdentidad');
                var nombres = $('#nombres');
                var apellidos = $('#apellidos');
                var correo_usuario = $('#correo_usuario');
                var clave_usuario = $('#clave_usuario');                

               buscar.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data = {
                                idCandidato: idCandidato.val()
                              };
                   
                   $.ajax({
			type: 'GET',			
			url: 'buscarcandidato',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    return 'Error: '+ e.error + '<br>Mensaje: ' + e.mensaje;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read
               
               actualizarcandidato.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data = {
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'actualizarcandidato',
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
               });//Update
            });//Jquery

        </script>
</body>
</html>