<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Crear Puesto</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Registrar Perfil</h5>
            <hr>
            <br>
        </div>    
    </div>
    
    <div class="row">
        <div class="col-md-12 form-group">
            <button id="createConocimientOtros" type="button" class="btn btn-success">Create ConocimientOtros</button>
            <button id="readConocimientOtros" type="button" class="btn btn-success">Read ConocimientOtros</button>
            <button id="updateConocimientOtros" type="button" class="btn btn-success">Update ConocimientOtros</button>
            <button id="deleteConocimientOtros" type="button" class="btn btn-success">Delete ConocimientOtros</button>
            
            <br><br>
            <p id="respuestaConocimientOtros"></p>
        </div>            
        
        <div class="col-md-3 form-group">
            <div class="input-group">
                <label class="input-group-addon col-md-5">idPerfil</label>
                <input class="form-control" id="idPerfilPuestoCO" type="number">  
            </div>            
        </div>
        
        <div class="col-md-3 form-group">
            <div class="input-group">
                <label class="input-group-addon col-md-5">idConocOtros</label>
                <input class="form-control" id="idConocOtros" type="number">  
            </div>
        </div>
        
        <div class="col-md-3 form-group">
            <div class="input-group">                
                <label class="input-group-addon col-md-5">idListaCursoso</label>
                <input class="form-control" id="idListaCursoso" type="number">  
            </div>
        </div>
        
        <div class="col-md-3 form-group">
            <div class="input-group">                
                <label class="input-group-addon col-md-5">idNivelConoc</label>
                <input class="form-control" id="idNivelConoc" type="number">  
            </div>
        </div>
        
    </div>
    
    <!-- 3 -->
    <div class="row">
        <div class="col-md-12 form-group">
            <button id="createFormAcademica" type="button" class="btn btn-success">Create Form Académica</button>
            <button id="readFormAcademica" type="button" class="btn btn-success">Read Form Académica</button>
            
            <br><br>
            <p id="respuestaFormAcademica"></p>
        </div>            
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">idPerfil</label>
                <input class="form-control" id="idPerfilPuestoA" type="number">             
            </div>              
        </div>
                
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Lvl Edu.</label>
                 <input class="form-control" id="idNivelEdu" type="number">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">¿Completo?</label>
                <input class="form-control" id="nivelCompleto" type="text" maxlength="1">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">GradoEstu</label>
                 <input class="form-control" id="idGradoEstu" type="number">
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Maestria</label>
                <input class="form-control" id="idMaestria" type="number">
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">¿Completo?</label>
                <input class="form-control" id="nivMaestria" type="text" maxlength="1">
            </div>              
        </div>
        
                <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Doctorado</label>
                <input class="form-control" id="idDoctorado" type="number">
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">¿Completo?</label>
                <input class="form-control" id="nivDoctorado" type="text" maxlength="1">
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">¿Colegiatura?</label>
                <input class="form-control" id="colegiatura" type="text" maxlength="1">
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">¿Habilitado?</label>
                <input class="form-control" id="habilitacion" type="text" maxlength="1">
            </div>              
        </div>                
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Carreras</label>
                <input class="form-control" id="idCarreras" type="text" maxlength="30">
            </div>              
        </div>
        
        
    </div>
    
    <hr><br>
    <!-- 2 -->
    <div class="row">
        <div class="col-md-12 form-group">
            <button id="createFuncionPuesto" type="button" class="btn btn-success">Create Función Puesto</button>
            <button id="readFuncionPuesto" type="button" class="btn btn-success">Read Funcion Puesto</button>
            <button id="updateFuncionPuesto" type="button" class="btn btn-success">Update Función Puesto</button>
            <button id="deleteFuncionPuesto" type="button" class="btn btn-success">Delete Funcion Puesto</button>
            
            <br><br>
            <p id="respuestaFuncionPuesto"></p>
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">idPerfil</label>
                 <input class="form-control" id="idPerfilPuestoFP" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">idFunPuesto</label>
                 <input class="form-control" id="idFuncionPuesto" type="text">             
            </div>              
        </div>
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Desc.</label>
                 <input class="form-control" id="descripcionPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">F</label>
                 <input class="form-control" id="valFrecuencia_F" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">CE</label>
                 <input class="form-control" id="valConErrado_CE" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">CM</label>
                 <input class="form-control" id="valComplejidad_CM" type="text">             
            </div>              
        </div>                
        

    </div>
    
        <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-md-12 form-group">
            <button id="createPuesto" type="button" class="btn btn-success">Create Puesto</button>
            <button id="readPuesto" type="button" class="btn btn-success">Read Puesto</button>
            
            <br><br>
            
            <p id="respuestaPuesto"></p>
        </div>
        
        <div class="col-md-12 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">idPerfilPuesto</label>
                 <input class="form-control" id="idPerfilPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">NomPuesto</label>
                 <input class="form-control" id="nomPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">Misión</label>
                 <input class="form-control" id="misionPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">C.Externa</label>
                 <input class="form-control" id="coord_Externa" type="text">             
            </div>              
        </div>
        
        <div class="col-md-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-md-5">C.Interna</label>
                 <input class="form-control" id="coord_Interna" type="text">             
            </div>              <br><br><br><br>
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
                $('#respuestaPuesto').html('Ready');
                $('#respuestaFuncionPuesto').html('Ready');
                $('#respuestaFormAcademica').html('Ready');
                $('#respuestaConocimientOtros').html('Ready');                
                
                var createPuesto = $('#createPuesto');
                var readPuesto = $('#readPuesto');                
                //
                var createFuncionPuesto = $('#createFuncionPuesto');
                var readFuncionPuesto = $('#readFuncionPuesto');
                var updateFuncionPuesto = $('#updateFuncionPuesto');
                var deleteFuncionPuesto = $('#deleteFuncionPuesto');
                //
                var createFormAcademica = $('#createFormAcademica');
                var readFormAcademica = $('#readFormAcademica');
                //
                var createConocimientOtros = $('#createConocimientOtros');
                var readConocimientOtros = $('#readConocimientOtros');
                var updateConocimientOtros = $('#updateConocimientOtros');
                var deleteConocimientOtros = $('#deleteConocimientOtros');
                
                
                var idPerfilPuesto = $('#idPerfilPuesto');
                var nomPuesto = $('#nomPuesto');
                var misionPuesto = $('#misionPuesto');
                var coord_Externa = $('#coord_Externa');
                var coord_Interna = $('#coord_Interna');
                
                
                var idPerfilPuestoFP = $('#idPerfilPuestoFP');
                var idFuncionPuesto = $('#idFuncionPuesto');
                var descripcionPuesto = $('#descripcionPuesto');
                var valFrecuencia_F = $('#valFrecuencia_F');
                var valConErrado_CE = $('#valConErrado_CE');
                var valComplejidad_CM = $('#valComplejidad_CM');
                
                var idPerfilPuestoA = $('#idPerfilPuestoA');
                var idNivelEdu = $('#idNivelEdu');
                var nivelCompleto = $('#nivelCompleto');
                var idGradoEstu = $('#idGradoEstu');
                var idCarreras = $('#idCarreras');
                var idMaestria = $('#idMaestria');
                var nivMaestria = $('#nivMaestria');
                var idDoctorado = $('#idDoctorado');
                var nivDoctorado = $('#nivDoctorado');
                var colegiatura = $('#colegiatura');
                var habilitacion = $('#habilitacion');
                
                var idPerfilPuestoCO = $('#idPerfilPuestoCO');
                var idConocOtros = $('#idConocOtros');
                var idListaCursoso = $('#idListaCursoso');
                var idNivelConoc = $('#idNivelConoc');
                                        
               createPuesto.click(function()
               {                                      
                   $('#respuestaPuesto').html('Cargando...');

                   var data = {
                                nomPuesto: nomPuesto.val(),
                                misionPuesto: misionPuesto.val(),
                                coord_Externa: coord_Externa.val(),
                                coord_Interna: coord_Interna.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createPuesto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaPuesto').html(
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
                            $("#respuestaPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create Puesto
               
               readPuesto.click(function()
               {
                   $('#respuestaPuesto').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readPuesto';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
                       url = 'readPuestoId'
                   }
                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaPuesto').html(
                                resultado.map(function(e)
                                {
                                    if(e.NOMPUESTO)
                                        return 'Puesto: ' + e.NOMPUESTO + ' - Misión: ' + e.MISIONPUESTO ;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Puesto
 
                createFuncionPuesto.click(function()
               {                                      
                   $('#respuestaFuncionPuesto').html('Cargando...');
                   
                   var data = {
                                idPerfilPuesto: idPerfilPuesto.val(),
                                descripcionPuesto: descripcionPuesto.val(),
                                valFrecuencia_F: valFrecuencia_F.val(),
                                valConErrado_CE: valConErrado_CE.val(),
                                valComplejidad_CM: valComplejidad_CM.val()                                
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createFuncionPuesto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaFuncionPuesto').html(
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
                            $("#respuestaFuncionPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create Funcion Puesto
               
               readFuncionPuesto.click(function()
               {                   
                   $('#respuestaFuncionPuesto').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuestoFP.val() === "")
                   {
                       data={};
                       url = 'readFuncionPuesto';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto:  idPerfilPuestoFP.val()};
                       url = 'readFuncionPuestoId';
                   }   
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaFuncionPuesto').html(
                                resultado.map(function(e)
                                {
                                    if(e.DESCRIPCIONPUESTO)
                                        return 'Descripción: ' + e.DESCRIPCIONPUESTO ;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaFuncionPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Funcion Puesto
               
                updateFuncionPuesto.click(function()
               {                                      
                   $('#respuestaFuncionPuesto').html('Cargando...');
                   
                   var data = {
                                idFuncionPuesto: idFuncionPuesto.val(),    
                                descripcionPuesto: descripcionPuesto.val(),
                                valFrecuencia_F: valFrecuencia_F.val(),
                                valConErrado_CE: valConErrado_CE.val(),
                                valComplejidad_CM: valComplejidad_CM.val()                                
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'updateFuncionPuesto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaFuncionPuesto').html(
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
                            $("#respuestaFuncionPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update Funcion Puesto

                deleteFuncionPuesto.click(function()
               {                                      
                   $('#respuestaFuncionPuesto').html('Cargando...');
                   
                   var data = {
                                idFuncionPuesto: idFuncionPuesto.val()                           
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteFuncionPuesto',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaFuncionPuesto').html(
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
                            $("#respuestaFuncionPuesto").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update Funcion Puesto
        
                createFormAcademica.click(function()
               {                                      
                   $('#respuestaFormAcademica').html('Cargando...');
                   
                   var data = {
                                idPerfilPuestoA: idPerfilPuestoA.val(),
                                idNivelEdu: idNivelEdu.val(),
                                nivelCompleto: nivelCompleto.val(),
                                idGradoEstu: idGradoEstu.val(),
                                idCarreras: idCarreras.val(),
                                idMaestria: idMaestria.val(),
                                nivMaestria: nivMaestria.val(),
                                idDoctorado: idDoctorado.val(),
                                nivDoctorado: nivDoctorado.val(),
                                colegiatura: colegiatura.val(),
                                habilitacion: habilitacion.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createFormAcademica',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaFormAcademica').html(
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
                            $("#respuestaFormAcademica").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create Formación Académica
 
               readFormAcademica.click(function()
               {
                   $('#respuestaFormAcademica').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuestoA.val() === "")
                   {
                       data={};
                       url = 'readFormAcademica';
                   }                                           
                   else
                   {
                       data={idPerfilPuestoA: idPerfilPuestoA.val()};
                       url = 'readFormAcademicaId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaFormAcademica').html(
                                resultado.map(function(e)
                                {
                                    if(e.NIVELEDUCATIVO && e.GRADOESTUDIOS)
                                        return 'Nivel Educativo: ' + e.NIVELEDUCATIVO + ' - Grado de Estudios: ' + e.GRADOESTUDIOS ;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaFormAcademica").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Puesto
 
                createConocimientOtros.click(function()
               {                                      
                   $('#respuestaConocimientOtros').html('Cargando...');
                   
                   var data = {                       
                                idListaCursoso: idListaCursoso.val(),
                                idNivelConoc: idNivelConoc.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createConocimientOtros',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientOtros').html(
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
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create Conocimiento Otros
               
               readConocimientOtros.click(function()
               {
                   $('#respuestaConocimientOtros').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuestoCO.val() === "")
                   {
                       data={};
                       url = 'readConocimientOtros';
                   }                                           
                   else
                   {
                       data={idPerfilPuestoCO: idPerfilPuestoCO.val()};
                       url = 'readConocimientOtrosId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaConocimientOtros').html(
                                resultado.map(function(e)
                                {
                                    if(e.CURSO && e.NIVEL)
                                        return 'ID: '+ e.IDCONOCOTROS + '- Curso: ' + e.CURSO + ' - Nivel: ' + e.NIVEL ;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Conocimiento Otros
               
                updateConocimientOtros.click(function()
               {                                      
                   $('#respuestaConocimientOtros').html('Cargando...');
                                            
                   var data = {
                                idConocOtros: idConocOtros.val(),
                                idListaCursoso: idListaCursoso.val(),
                                idNivelConoc: idNivelConoc.val()                                                                
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateConocimientOtros',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientOtros').html(
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
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update Conocimiento Otros
 
                deleteConocimientOtros.click(function()
               {                                      
                   $('#respuestaConocimientOtros').html('Cargando...');
                   
                   var data = {
                                idConocOtros: idConocOtros.val()                           
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteConocimientOtros',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientOtros').html(
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
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete Conocimiento Otros
 
            });//Jquery

        </script>
</body>
</html>