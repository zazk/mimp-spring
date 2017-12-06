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
            <h1>Pruebas GET/POST</h1>
            
            <h5>Usuarios</h5>
            <button id="logininterno" type="button" class="btn btn-success">Login Interno</button>
            <button id="create" type="button" class="btn btn-success">Create</button>
            <button id="read" type="button" class="btn btn-success">Read</button>
            <button id="update" type="button" class="btn btn-success">Update</button>
            <button id="eliminar" type="button" class="btn btn-success">Delete</button>

            <br><br>
            <p id="respuesta"></p>
        </div>    
    </div>
    
    <!-- idUsuario, nombre, apellidos, correo, clave, rol, idDependencia,idDocumento, num_documento, estado -->
    <div class="row">                
        <div class="col-md-12 form-group ">
            <label>Usuario</label>
            <input id="usuario">
            
            <label>Contraseña</label>
            <input id="password">
        </div>
        
        <div class="col-md-12 form-group ">
            <label>idUsuario</label>
            <input id="idUsuario">
            
            <label>Nombre</label>
            <input id="nombre">
            
            <label>Apellidos</label>
            <input id="apellidos">
            
            <label>Correo</label>
            <input id="correo">
            
            <label>Clave</label>
            <input id="clave">

            <label>Rol</label>
            <input id="rol">
        
            <label>Dependencia</label>
            <input id="idDependencia">
            
            <label>Documento</label>
            <input id="idDocumento">
        
            <label>Nº Documento</label>
            <input id="num_documento">
        
            <label>Estado</label>
            <input id="estado">
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
                var logininterno = $('#logininterno');
                var create = $('#create');
                var read = $('#read');
                var update = $('#update');
                var eliminar = $('#eliminar');
                
                var usuario = $('#usuario');
                var password = $('#password');
                
                var idUsuario = $('#idUsuario');
                var nombre = $('#nombre');
                var apellidos = $('#apellidos');
                var correo = $('#correo');
                var clave = $('#clave');
                var rol = $('#rol');
                var idDependencia = $('#idDependencia');
                var idDocumento = $('#idDocumento');
                var num_documento = $('#num_documento');            
                var estado = $('#estado');
               
               logininterno.click(function()
               {             
                   var data = {
                                usuario: usuario.val(),
                                password: password.val()
                              }
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'logininterno',
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
               });//Login Interno
                              
               create.click(function()
               {
                   var data =
                           {
                            nombre: nombre.val(),
                            apellidos: apellidos.val(),
                            correo: correo.val(),
                            clave: clave.val(),
                            rol: rol.val(),
                            idDependencia: idDependencia.val(),
                            idDocumento: idDocumento.val(),
                            num_documento: num_documento.val(),
                            estado: estado.val()
                          }
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'create',
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
               });//Create
               
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
                                resultado.map(function(e)
                                {
                                    return 'ID: ' + e.IDEMPLEADO + ' - ' + e.EMPLEADO + ' - DEPENDENCIA: FALTA COORDINAR CON FREDDY' +
                                     '<BR>ROL: '+ e.ROL + ' - USUARIO: ' + e.USUARIO + ' - ESTADO: ' + e.ESTADO;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read
               
               update.click(function()
               {             
                   var data = {
                            idUsuario: idUsuario.val(),
                            nombre: nombre.val(),
                            apellidos: apellidos.val(),
                            correo: correo.val(),
                            clave: clave.val(),
                            rol: rol.val(),
                            idDependencia: idDependencia.val(),
                            idDocumento: idDocumento.val(),
                            num_documento: num_documento.val(),
                            estado: estado.val()
                          }
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'update',
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
               
               eliminar.click(function()
               {
                   var data = {
                            idUsuario: idUsuario.val(),
                            nombre: nombre.val(),
                            apellidos: apellidos.val(),
                            correo: correo.val(),
                            clave: clave.val(),
                            rol: rol.val(),
                            idDependencia: idDependencia.val(),
                            idDocumento: idDocumento.val(),
                            num_documento: num_documento.val(),
                            estado: estado.val()
                          }
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'delete',
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
               });//Delete <- es una palabra reservada del sistema por eso se puso Eliminar
               
            });//Jquery

        </script>
</body>
</html>