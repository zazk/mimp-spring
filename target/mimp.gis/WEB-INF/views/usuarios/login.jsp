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
	
</head>
<body class="container">
    <div class="row">
        <div class="col-md-12">            
            <h1>Prueba POST</h1>
            
            <button id="loginn" type="button" class="btn btn-success">Login</button>
            <button id="logout" type="button" class="btn btn-success">Logout</button>

            
            <p id="respuesta"></p>
        </div>    
    </div>
    <div class="row">
        <div class="col-md-12">
            <label>Email</label>
            <input id="idemail">
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <label>Clave</label>
            <input id="idclave">
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
                var login = $('#loginn');
                var logout = $('#logout');
                var _email = $('#idemail');
                var _clave = $('#idclave');
               
               login.click(function()
               {
                   $('#respuesta').html('Cargando..');
                   
                   var data = {
                                email: _email.val(),
                                clave: _clave.val()
                              };                   
                   $.ajax({
			type: 'POST',			
			url: 'loginprueba', 
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
               
               logout.click(function()
               {
                   $('#respuesta').html('Cargando..');
                   
                   var data = {
                                email: _email.val(),
                                clave: _clave.val()
                              };                   
                   $.ajax({
			type: 'POST',			
			url: 'logoutprueba',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);                            
                            $('#respuesta').html(resultado);
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