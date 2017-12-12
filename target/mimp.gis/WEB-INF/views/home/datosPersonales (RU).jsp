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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
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
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">ID</label>
                 <input class="form-control" id="idCandidato" type="number">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Documento</label>
                 <input class="form-control" id="idTipoIdentidad" type="number">
             </div>
        </div>
            
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Nro Doc.</label>
                 <input class="form-control" id="nroIdentidad" type="number">
             </div>
        </div>

        <div class="col-md-3 form-group">
             <div class="input-group">
            <label class="input-group-addon col-md-5">Ap. Paterno</label>
             <input class="form-control" id="ap_paterno" type="text">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
            <label class="input-group-addon col-md-5">Ap. Materno</label>
             <input class="form-control" id="ap_materno" type="text">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Nombres</label>
                 <input class="form-control" id="nombres" type="text">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Estado Civil</label>
                 <input class="form-control" id="idEstadoCivil" type="number">
             </div>
        </div> 
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Sexo</label>
                 <input class="form-control" id="sexo" type="text">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Fec nac</label>
                 <input class="form-control" id="fe_nacimiento" type="date">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Direccion</label>
                 <input class="form-control" id="direccion_ca" type="text">
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Distrito</label>
                 <input class="form-control" id="idDistrito" type="number">
             </div>
        </div> 
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Ruc</label>
                 <input class="form-control" id="ruc" type="number">
             </div>
        </div>   
            
        <div class="col-md-3 form-group">
             <div class="input-group">
            <label class="input-group-addon col-md-5">Correo</label>
             <input class="form-control" id="correo_usuario" type="email">
             </div>
        </div>

        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Clave</label>
                 <input class="form-control" id="clave_usuario" type="password">        
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Tel Fijo</label>
                 <input class="form-control" id="tel_fijo" type="number">        
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Tel Celu</label>
                 <input class="form-control" id="tel_celu" type="number">        
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Lic. FFAA</label>
                 <input class="form-control" id="lic_FFAA" type="text">        
             </div>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Discapac</label>
                 <input class="form-control" id="discapac" type="text">        
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
                var buscar = $('#buscar');
                var actualizarcandidato = $('#actualizarcandidato');
                
                var idCandidato = $('#idCandidato');
                var idTipoIdentidad = $('#idTipoIdentidad');
                var nroIdentidad = $('#nroIdentidad');
                var ap_paterno = $('#ap_paterno');
                var ap_materno = $('#ap_materno');
                var nombres = $('#nombres');
                var idEstadoCivil = $('#idEstadoCivil');
                var sexo = $('#sexo');
                var fe_nacimiento = $('#fe_nacimiento');
                var direccion_ca = $('#direccion_ca');
                var direccion_nro = $('#direccion_nro');
                var idDistrito = $('#idDistrito');
                var ruc = $('#ruc');
                var brevete_nro = $('#brevete_nro');
                var brevete_cat = $('#brevete_cat');
                var correo_usuario = $('#correo_usuario');
                var clave_usuario = $('#clave_usuario');
                var tel_fijo = $('#tel_fijo');
                var tel_celu = $('#tel_celu');
                var lic_FFAA = $('#lic_FFAA');
                var discapac = $('#discapac');
                var idNacionalidad = $('#idNacionalidad');
                var fotoRuta = $('#fotoRuta');              

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
                                idCandidato: idCandidato.val(),
                                idTipoIdentidad: idTipoIdentidad.val(),
                                nroIdentidad: nroIdentidad.val(),
                                ap_paterno: ap_paterno.val(),
                                ap_materno: ap_materno.val(),
                                nombres: nombres.val(),
                                idEstadoCivil: idEstadoCivil.val(),
                                sexo: sexo.val(),
                                fe_nacimiento: fe_nacimiento.val(),
                                direccion_ca: direccion_ca.val(),
                                direccion_nro: direccion_nro.val(),
                                idDistrito: idDistrito.val(),
                                ruc: ruc.val(),
                                brevete_nro: brevete_nro.val(),
                                brevete_cat: brevete_cat.val(),
                                correo_usuario: correo_usuario.val(),
                                clave_usuario: clave_usuario.val(),
                                tel_fijo: tel_fijo.val(),
                                tel_celu: tel_celu.val(),
                                lic_FFAA: lic_FFAA.val(),
                                discapac: discapac.val(),
                                idNacionalidad: idNacionalidad.val(),
                                fotoRuta: fotoRuta.val()
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
               });//Actualizar Candidato
            });//Jquery

        </script>
</body>
</html>