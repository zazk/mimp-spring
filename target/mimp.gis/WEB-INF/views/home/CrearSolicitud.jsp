<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Crear Solicitud</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Solicitud</h5>
            <button id="listarOrgano" type="button" class="btn btn-success">Listar Organo</button>
            <button id="listarUOrganica" type="button" class="btn btn-success">Listar UOrganica</button>
            <button id="crearsolicitud" type="button" class="btn btn-success">Crear Solicitud</button>
            <br><br>
            <p id="respuesta"></p>
        </div>    
    </div>
    
    <div class="row">                    
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Órgano</label>
                 <input class="form-control" id="idOrgano" type="number">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">UOrgánica</label>
                 <input class="form-control" id="idUOrganica" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">TipoHorario</label>
                 <input class="form-control" id="idThorarios" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Fe_Ini</label>
                 <input class="form-control" id="fe_inicio" type="date">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Fe_Fin</label>
                 <input class="form-control" id="fe_termino" type="date">
             </div>
        </div>       
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">NºPosis</label>
                <input class="form-control" id="nroPuestos" type="number" maxlength="2">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Sueldo</label>
                 <input class="form-control" id="montoMes" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Peruano?</label>
                 <input class="form-control" id="nacPeruana" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">TipReq</label>
                 <input class="form-control" id="fe_termino" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Sustento</label>
                 <input class="form-control" id="sustentoNac" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Usu_Crea</label>
                 <input class="form-control" id="usu_crea" type="number">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Puesto</label>
                 <input class="form-control" id="idperfilPuesto" type="number" value="1">
             </div>
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
                $('#respuesta').html('Ready');
                var crearsolicitud = $('#crearsolicitud');
                var listarOrgano = $('#listarOrgano');
                var listarUOrganica = $('#listarUOrganica');
                
                var idUOrganica = $('#idUOrganica');
                var idperfilPuesto = $('#idperfilPuesto');
                var montoMes = $('#montoMes');
                var nroPuestos = $('#nroPuestos');
                var fe_inicio = $('#fe_inicio');
                var fe_termino = $('#fe_termino');
                var nacPeruana = $('#nacPeruana');
                var sustentoNac = $('#sustentoNac');
                var usu_crea = $('#usu_crea');
                var idThorarios = $('#idThorarios');
                
        
               crearsolicitud.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                   var data = {
                                idUOrganica: idUOrganica.val(),
                                idperfilPuesto: idperfilPuesto.val(),
                                montoMes: montoMes.val(),
                                nroPuestos: nroPuestos.val(),
                                fe_inicio: fe_inicio.val(),
                                fe_termino: fe_termino.val(),
                                nacPeruana: nacPeruana.val(),
                                sustentoNac: sustentoNac.val(),
                                usu_crea: usu_crea.val(),
                                idThorarios: idThorarios.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'crearsolicitud',
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
               });//Crear solicitud
               
               listarOrgano.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'listarOrgano',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    return e.DESCRIPCION;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//listarOrgano
               
               listarUOrganica.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'listarUOrganica',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    return e.DESCRIPCION;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//listarUOrganica
               
            });//Jquery

        </script>
</body>
</html>