<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Cronograma</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body class="container">
    <div class="row">
        <div class="col-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Cronograma</h5>
        </div>    
    </div>
    
    <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="updateCronogramaP" type="button" class="btn btn-success">Create/Update CronogramaP</button>
            <button id="readCronogramaP" type="button" class="btn btn-success">Read CronogramaP</button>
            <button id="enviarCronogramP" type="button" class="btn btn-success">Enviar CronogramP</button>
            <br><br>
            <p id="respuesta"></p>
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idCronogramaP</label>
                 <input class="form-control" id="idCronogramaP" type="text" value="Predeterminado: 1, 1, 1" readonly="readonly">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">estadoProceso</label>
                 <input class="form-control" id="estadoProceso" type="number">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idUsuarioModi</label>
                 <input class="form-control" id="idUsuarioModi" type="number">             
            </div>              
        </div>
       
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idRequisicionP</label>
                 <input class="form-control" id="idRequisicionP" type="number">             
            </div>              
        </div>
        
        <!-- Inicia las fechas -->  
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_mtpe</label>
                 <input class="form-control" id="fInicio_mtpe" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTermino_mtpe</label>
                 <input class="form-control" id="fTermino_mtpe" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_portal</label>
                 <input class="form-control" id="fInicio_portal" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTermino_portal</label>
                 <input class="form-control" id="fTermino_portal" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_evaCurr</label>
                 <input class="form-control" id="fInicio_evaCurr" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTerm_evaCurr</label>
                 <input class="form-control" id="fTerm_evaCurr" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_evaTec</label>
                 <input class="form-control" id="fInicio_evaTec" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTerm_evaTec</label>
                 <input class="form-control" id="fTerm_evaTec" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_evaPsico</label>
                 <input class="form-control" id="fInicio_evaPsico" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTerm_evaPsico</label>
                 <input class="form-control" id="fTerm_evaPsico" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_entrev</label>
                 <input class="form-control" id="fInicio_entrev" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTerm_entrev</label>
                 <input class="form-control" id="fTerm_entrev" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fInicio_suscr</label>
                 <input class="form-control" id="fInicio_suscr" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fTerm_suscr</label>
                 <input class="form-control" id="fTerm_suscr" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fPubl_apto1</label>
                 <input class="form-control" id="fPubl_apto1" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fpubl_apto2</label>
                 <input class="form-control" id="fpubl_apto2" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fpubli_apto3</label>
                 <input class="form-control" id="fpubli_apto3" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fpublic_apto4</label>
                 <input class="form-control" id="fpublic_apto4" type="date">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">fpublic_apto5</label>
                 <input class="form-control" id="fpublic_apto5" type="date">             
            </div>              
        </div>
        
        <!-- Finaliza las fechas -->       
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">observa</label>
                 <input class="form-control" id="observa" type="text">             
            </div>              
        </div>
                
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">usu_crea</label>
                 <input class="form-control" id="usu_crea" type="number">             
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
                var readCronogramaP = $('#readCronogramaP');
                var updateCronogramaP = $('#updateCronogramaP');
                var enviarCronogramP = $('#enviarCronogramP');                

                //Input
                //var idRequisicionP = $('#idRequisicionP');
                var fInicio_mtpe = $('#fInicio_mtpe');
                var fTermino_mtpe = $('#fTermino_mtpe');
                var fInicio_portal = $('#fInicio_portal');
                var fTermino_portal = $('#fTermino_portal');
                var fInicio_evaCurr = $('#fInicio_evaCurr');
                var fTerm_evaCurr = $('#fTerm_evaCurr');
                var fInicio_evaTec = $('#fInicio_evaTec');
                var fTerm_evaTec = $('#fTerm_evaTec');
                var fInicio_evaPsico = $('#fInicio_evaPsico');
                var fTerm_evaPsico = $('#fTerm_evaPsico');
                var fInicio_entrev = $('#fInicio_entrev');
                var fTerm_entrev = $('#fTerm_entrev');
                var fInicio_suscr = $('#fInicio_suscr');
                var fTerm_suscr = $('#fTerm_suscr');
                var fPubl_apto1 = $('#fPubl_apto1');
                var fpubl_apto2 = $('#fpubl_apto2');
                var fpubli_apto3 = $('#fpubli_apto3');
                var fpublic_apto4 = $('#fpublic_apto4');
                var fpublic_apto5 = $('#fpublic_apto5');
                var observa = $('#observa');                
                var usu_crea = $('#usu_crea');
                //
                var idCronogramaP = $('#idCronogramaP');
                var estadoProceso = $('#estadoProceso');
                var idUsuarioModi = $('#idUsuarioModi');
               
               updateCronogramaP.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idRequisicionP: [{ID: "1"}, {ID: "1"}, {ID:"1"}],
                                fInicio_mtpe: fInicio_mtpe.val(),
                                fTermino_mtpe: fTermino_mtpe.val(),
                                fInicio_portal: fInicio_portal.val(),
                                fTermino_portal: fTermino_portal.val(),
                                fInicio_evaCurr: fInicio_evaCurr.val(),
                                fTerm_evaCurr: fTerm_evaCurr.val(),
                                fInicio_evaTec: fInicio_evaTec.val(),
                                fTerm_evaTec: fTerm_evaTec.val(),
                                fInicio_evaPsico: fInicio_evaPsico.val(),
                                fTerm_evaPsico: fTerm_evaPsico.val(),
                                fInicio_entrev: fInicio_entrev.val(),
                                fTerm_entrev: fTerm_entrev.val(),
                                fInicio_suscr: fInicio_suscr.val(),
                                fTerm_suscr: fTerm_suscr.val(),
                                fPubl_apto1: fPubl_apto1.val(),
                                fpubl_apto2: fpubl_apto2.val(),
                                fpubli_apto3: fpubli_apto3.val(),
                                fpublic_apto4: fpublic_apto4.val(),
                                fpublic_apto5: fpublic_apto5.val(),
                                observa: observa.val(),
                                usu_crea: usu_crea.val()
                              };                   
                   
                   console.log(data);
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateCronogramaP',
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
               });//Create CronogramaP
               
               readCronogramaP.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readCronogramaP',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    var crono = (e.IDCRONOGRAMAP)?e.IDCRONOGRAMAP:'Aún no se crea cronograma';
                                    
                                    return 'IDCRONOGRAMAP: ' + crono + '- IDREQUISICIONP: '+ e.IDREQUISICIONP;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//read CronogramaP               
               
               enviarCronogramP.click(function()
               {                                      
                   $('#respuesta').html('Cargando...');
                                            
                   var data = {
                                idCronogramaP: [{ID: "1"}, {ID: "1"}, {ID:"1"}],
                                estadoProceso: estadoProceso.val(),
                                idUsuarioModi: idUsuarioModi.val()
                              };                   
                   
                   console.log(data);
                   
                   $.ajax({
			type: 'POST',			
			url: 'enviarCronogramP',
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
               });//Enviar CronogramP
            });//Jquery

        </script>
</body>
</html>