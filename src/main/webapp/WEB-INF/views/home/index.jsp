<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Geomimp</title>
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/bootstrap.css'/>">
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/font-awesome.css'/>">
	<link rel="stylesheet" href="<c:url value='/assetsvisor/css/geomimp.css'/>">
        <link rel="stylesheet" href="<c:url value='/assetsvisor/css/perfect-scrollbar.min.css'/>">
</head>
<body class="container">
    <div class="row">
        <div class="col-md-12">            
            <h1>Prueba POST</h1>
            
            <button id="buscar" type="button" class="btn btn-success">Buscar</button>
            <button id="create" type="button" class="btn btn-success">Create</button>
            <button id="read" type="button" class="btn btn-success">Read</button>
            <button id="update" type="button" class="btn btn-success">Update</button>
            <button id="eliminar" type="button" class="btn btn-success">Delete</button>

            
            <p id="respuesta"></p>
        </div>    
    </div>
    <div class="row">
        <div class="col-md-2">
            <label>COUSUARIO</label>
            <input id="cousuario">
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
                var create = $('#create');
                var read = $('#read');
                var update = $('#update');
                var eliminar = $('#eliminar');
                var id = $('#cousuario');
               
               buscar.click(function()
               {
                   var cod = id.val();
                   var data={CoUsuario: cod};
                   
                   $.ajax({
			type: 'POST',			
			url: 'buscar',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                'Usuario: ' + resultado.map(function(e)
                                {
                                    return e.NOMBRE;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });
               
               create.click(function()
               {
                   var cod = id.val();
                   var data={CoUsuario: cod};
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'create',
                        data: data,
			success: function(resultado)
                        {
                            console.log('antes de resultado');
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                'Usuario: ' + resultado.map(function(e)
                                {
                                    return e.NOMBRE;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });
               
               read.click(function()
               {
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'read',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                'Usuario: ' + resultado.map(function(e)
                                {
                                    return e.NOMBRE;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });
               
               update.click(function()
               {             
                   var cod = id.val();                 
                   var data={CoUsuario: cod};
                   
                   $.ajax({
			type: 'POST',			
			url: 'update',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                'Usuario: ' + resultado.map(function(e)
                                {
                                    return e.NOMBRE;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });
               
               eliminar.click(function()
               {
                   var cod = id.val();                                     
                   var data={CoUsuario: cod};
                   
                   $.ajax({
			type: 'POST',			
			url: 'delete',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                'Usuario: ' + resultado.map(function(e)
                                {
                                    return e.NOMBRE;
                                }).join(', ')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });               
               
            });//Jquery

        </script>
</body>
</html>