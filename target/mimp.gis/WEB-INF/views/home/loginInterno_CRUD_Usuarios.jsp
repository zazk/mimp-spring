<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login Interno - Usuarios</title>
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
    
    <div class="row">                    
        <div class="col-md-12 form-group ">
            <label>idUsuarios</label>
            <input id="idUsuarios" type="number">
            
            <label>Empleado</label>
            <input id="idEmpleado" type="number">
            
            <label>Rol</label>
            <input id="idRolUser" type="number">
            
            <label>Usuario</label>
            <input id="usuario" type="email">
            
            <label>Password</label>
            <input id="password" type="password">

            <label>Estado</label>
            <input id="estado" type="number">        
        </div>
    </div>
</div>

	<script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-1.11.1.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/assetsvisor/js/jquery-ui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/assetsvisor/js/bootstrap.js'/>"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {
                $('#respuesta').html('Ready');
                var logininterno = $('#logininterno');
                var create = $('#create');
                var read = $('#read');
                var update = $('#update');
                var eliminar = $('#eliminar');              
                
                var idUsuarios = $('#idUsuarios');
                var idEmpleado = $('#idEmpleado');
                var idRolUser = $('#idRolUser');
                var usuario = $('#usuario');
                var password = $('#password');
                var estado = $('#estado');            
               
               logininterno.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data = {
                                usuario: usuario.val(),
                                password: password.val()
                              };
                   
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
                   $('#respuesta').html('Cargando...');
                   var data =
                           {
                            idEmpleado: idEmpleado.val(),
                            idRolUser: idRolUser.val(),
                            usuario: usuario.val(),
                            password: password.val(),
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
                   $('#respuesta').html('Cargando...');
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
                                    return 'ID: ' + e.IDUSUARIOS + ' - ' + e.EMPLEADO + ' - DEPENDENCIA: ' + e.DEPENDENCIA +
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
                   $('#respuesta').html('Cargando...');
                   var data = {
                                idUsuarios: idUsuarios.val(),
                                idEmpleado: idEmpleado.val(),
                                idRolUser: idRolUser.val(),
                                usuario: usuario.val(),
                                password: password.val(),
                                estado: estado.val()
                              };
                   
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
                   $('#respuesta').html('Cargando...');
                   var data = {
                                idUsuarios: idUsuarios.val(),
                              };
                   
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