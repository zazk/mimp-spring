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
        <div class="col-12">
            <h1>Pruebas GET/POST</h1>
            
            <h5>Registrar Perfil</h5>
        </div>    
    </div>
    
    <hr><br>
    <!-- 1 -->
    <div class="row">
        <div class="col-12">
            <button id="readOrgano" type="button" class="btn btn-success">Read Organo</button>
            <button id="readUOrganica" type="button" class="btn btn-success">Read UOrganica</button>
            <button id="createRequisionPuesto" type="button" class="btn btn-success">Create RequisionPuesto</button>
            <br><br>
            <p id="respuesta"></p>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Órgano</label>
                 <input class="form-control" id="idOrgano" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">UOrgánica</label>
                 <input class="form-control" id="idUOrganica" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">TipoHorario</label>
                 <input class="form-control" id="idThorarios" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Fe_Ini</label>
                 <input class="form-control" id="fe_inicio" type="date">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Fe_Fin</label>
                 <input class="form-control" id="fe_termino" type="date">
             </div>
        </div>       
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">NºPosis</label>
                <input class="form-control" id="nroPuestos" type="number" maxlength="2">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Sueldo</label>
                 <input class="form-control" id="montoMes" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Peruano?</label>
                 <input class="form-control" id="nacPeruana" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">TipReq</label>
                 <input class="form-control" id="fe_termino" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Sustento</label>
                 <input class="form-control" id="sustentoNac" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Usu_Crea</label>
                 <input class="form-control" id="usu_crea" type="number">
             </div>
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Puesto</label>
                 <input class="form-control" id="idperfilPuesto" type="number" value="1">
             </div>
        </div>
    </div>
    
    <hr><br>
    <!-- 2 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createPuesto" type="button" class="btn btn-success">Create Puesto</button>
            <button id="readPuesto" type="button" class="btn btn-success">Read Puesto</button>
            
            <br><br>
            
            <p id="respuestaPuesto"></p>
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idPerfilPuesto</label>
                 <input class="form-control" id="idPerfilPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">NomPuesto</label>
                 <input class="form-control" id="nomPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Misión</label>
                 <input class="form-control" id="misionPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">C.Externa</label>
                 <input class="form-control" id="coord_Externa" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">C.Interna</label>
                 <input class="form-control" id="coord_Interna" type="text">             
            </div>
        </div>
    </div>
    
    <hr><br>
    <!-- 3 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createFuncionPuesto" type="button" class="btn btn-success">Create Función Puesto</button>
            <button id="readFuncionPuesto" type="button" class="btn btn-success">Read Funcion Puesto</button>
            <button id="updateFuncionPuesto" type="button" class="btn btn-success">Update Función Puesto</button>
            <button id="deleteFuncionPuesto" type="button" class="btn btn-success">Delete Funcion Puesto</button>
            
            <br><br>
            <p id="respuestaFuncionPuesto"></p>
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">idFunPuesto</label>
                 <input class="form-control" id="idFuncionPuesto" type="text">             
            </div>              
        </div>
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Desc.</label>
                 <input class="form-control" id="descripcionPuesto" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">F</label>
                 <input class="form-control" id="valFrecuencia_F" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">CE</label>
                 <input class="form-control" id="valConErrado_CE" type="text">             
            </div>              
        </div>
        
        <div class="col-4 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">CM</label>
                 <input class="form-control" id="valComplejidad_CM" type="text">             
            </div>              
        </div>                
        

    </div>
    
    <hr><br>    
    <!-- 4 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createFormAcademica" type="button" class="btn btn-success">Create Form Académica</button>
            <button id="readFormAcademica" type="button" class="btn btn-success">Read Form Académica</button>
            <button id="readNivelEducativo" type="button" class="btn btn-success">Read NivelEducativo</button>
            <button id="readGradoEstudios" type="button" class="btn btn-success">Read GradoEstudios</button>
            <button id="readCarrera" type="button" class="btn btn-success">Read Carrera</button>
            
            <br><br>
            <p id="respuestaFormAcademica"></p>
        </div>            
                
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Lvl Edu.</label>
                 <input class="form-control" id="idNivelEdu" type="number">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">¿Completo?</label>
                <input class="form-control" id="nivelCompleto" type="text" maxlength="1">             
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">GradoEstu</label>
                 <input class="form-control" id="idGradoEstu" type="number">
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Maestria</label>
                <input class="form-control" id="idMaestria" type="number">
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">¿Completo?</label>
                <input class="form-control" id="nivMaestria" type="text" maxlength="1">
            </div>              
        </div>
        
                <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Doctorado</label>
                <input class="form-control" id="idDoctorado" type="number">
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">¿Completo?</label>
                <input class="form-control" id="nivDoctorado" type="text" maxlength="1">
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">¿Colegiatura?</label>
                <input class="form-control" id="colegiatura" type="text" maxlength="1">
            </div>              
        </div>
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">¿Habilitado?</label>
                <input class="form-control" id="habilitacion" type="text" maxlength="1">
            </div>              
        </div>                
        
        <div class="col-3 form-group">
             <div class="input-group">
                <label class="input-group-addon col-5">Carreras</label>
                <input class="form-control" id="idCarreras" type="text" maxlength="30">
            </div>              
        </div>
        
        
    </div>

    <hr><br>
    <!-- 5 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createConocimientOtros" type="button" class="btn btn-success">Create ConocimientOtros</button>
            <button id="readConocimientOtros" type="button" class="btn btn-success">Read ConocimientOtros</button>
            <button id="updateConocimientOtros" type="button" class="btn btn-success">Update ConocimientOtros</button>
            <button id="deleteConocimientOtros" type="button" class="btn btn-success">Delete ConocimientOtros</button>
            <button id="readListaCursos" type="button" class="btn btn-success">Read ListaCursos</button>
            <button id="readNivelConoc" type="button" class="btn btn-success">Read NivelConoc</button>
            
            <br><br>
            <p id="respuestaConocimientOtros"></p>
        </div>            
        
        <div class="col-3 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idConocOtros</label>
                <input class="form-control" id="idConocOtros" type="number">  
            </div>
        </div>
        
        <div class="col-3 form-group">
            <div class="input-group">                
                <label class="input-group-addon col-5">idListaCursoso</label>
                <input class="form-control" id="idListaCursoso" type="number">  
            </div>
        </div>
        
        <div class="col-3 form-group">
            <div class="input-group">                
                <label class="input-group-addon col-5">idNivelConoc</label>
                <input class="form-control" id="idNivelConoc" type="number">  
            </div>
        </div>        
    </div>
    
    <hr><br>
    <!-- 6 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createConocimientoTec" type="button" class="btn btn-success">Create ConocimientoTec</button>
            <button id="readConocimientoTec" type="button" class="btn btn-success">Read ConocimientoTec</button>
            <button id="updateConocimientoTec" type="button" class="btn btn-success">Update ConocimientoTec</button>
            <button id="deleteConocimientoTec" type="button" class="btn btn-success">Delete ConocimientoTec</button>
            
            <br><br>
            <p id="respuestaConocimientoTec"></p>
        </div>            

        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idConocTec</label>
                <input class="form-control" id="idConocTec" type="number">  
            </div>
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">Cursos</label>
                <input class="form-control" id="idListaCursosT" type="number">  
            </div>
        </div>        
    </div>
    
    <hr><br>
    <!-- 7 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createConocimientoEspec" type="button" class="btn btn-success">Create ConocimientoEspec</button>
            <button id="readConocimientoEspec" type="button" class="btn btn-success">Read ConocimientoEspec</button>
            <button id="updateConocimientoEspec" type="button" class="btn btn-success">Update ConocimientoEspec</button>
            <button id="deleteConocimientoEspec" type="button" class="btn btn-success">Delete ConocimientoEspec</button>
            
            <br><br>
            <p id="respuestaConocimientoEspec"></p>
        </div>            

        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idConocesP</label>
                <input class="form-control" id="idConocesP" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">tipoConocim</label>
                <input class="form-control" id="tipoConocim" type="text" maxlength="1">  
            </div>            
        </div>
        
                <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idListaCursose</label>
                <input class="form-control" id="idListaCursose" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">nroHoras</label>
                <input class="form-control" id="nroHoras" type="number">  
            </div>            
        </div>
        
                <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">sustentar</label>
                <input class="form-control" id="sustentar" type="number">  
            </div>            
        </div>
        
        
    </div>
        
    <hr><br>
    <!-- 8 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createExpeRequerida" type="button" class="btn btn-success">Create ExpeRequerida</button>
            <button id="readExpeRequerida" type="button" class="btn btn-success">Read ExpeRequerida</button>
            <button id="updateExpeRequerida" type="button" class="btn btn-success">Update ExpeRequerida</button>
            <button id="deleteExpeRequerida" type="button" class="btn btn-success">Delete ExpeRequerida</button>
            
            <br><br>
            <p id="respuestaExpeRequerida"></p>
        </div>                  
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idExpeRequerida</label>
                <input class="form-control" id="idExpeRequerida" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">expeGral</label>
                <input class="form-control" id="expeGral" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">expesPublico</label>
                <input class="form-control" id="expesPublico" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">expeEspecifica</label>
                <input class="form-control" id="expeEspecifica" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">observac</label>
                <input class="form-control" id="observac" type="number">  
            </div>            
        </div>
    </div>

    <hr><br>
    <!-- 9 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createExpNivelReq" type="button" class="btn btn-success">Create ExpNivelReq</button>
            <button id="readExpNivelReq" type="button" class="btn btn-success">Read ExpNivelReq</button>
            <button id="updateExpNivelReq" type="button" class="btn btn-success">Update ExpNivelReq</button>
            <button id="deleteExpNivelReq" type="button" class="btn btn-success">Delete ExpNivelReq</button>
            
            <br><br>
            <p id="respuestaExpNivelReq"></p>
        </div>            
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idExpeRequerida</label>
                <input class="form-control" id="idExpeRequerida" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idNivelReque</label>
                <input class="form-control" id="idNivelReque" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idNivelLaboral</label>
                <input class="form-control" id="idNivelLaboral" type="number">  
            </div>            
        </div>
    </div>
    
    <hr><br>
    <!-- 10 -->
    <div class="row">
        <div class="col-12 form-group">
            <button id="createCompetencias" type="button" class="btn btn-success">Create Competencias</button>
            <button id="readCompetencias" type="button" class="btn btn-success">Read Competencias</button>
            <button id="updateCompetencias" type="button" class="btn btn-success">Update Competencias</button>
            <button id="deleteCompetencias" type="button" class="btn btn-success">Delete Competencias</button>
            <button id="readDiccompetencias" type="button" class="btn btn-success">Read DicCompetencias</button>
            
            <br><br>
            <p id="respuestaCompetencias"></p>
        </div>            
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idCompetencias</label>
                <input class="form-control" id="idCompetencias" type="number">  
            </div>            
        </div>
        
        <div class="col-4 form-group">
            <div class="input-group">
                <label class="input-group-addon col-5">idDiccompetenc</label>
                <input class="form-control" id="idDiccompetenc" type="number">  
            </div>            
        </div><br><br><br><br><br><br><br><br><br><br><br><br>
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
                $('#respuestaPuesto').html('Ready');
                $('#respuestaFuncionPuesto').html('Ready');
                $('#respuestaFormAcademica').html('Ready');
                $('#respuestaConocimientOtros').html('Ready');
                $('#respuestaConocimientoTec').html('Ready');
                $('#respuestaConocimientoEspec').html('Ready');
                $('#respuestaExpeRequerida').html('Ready');
                $('#respuestaExpNivelReq').html('Ready');
                $('#respuestaCompetencias').html('Ready');
                
                //Botones
                var createRequisionPuesto = $('#createRequisionPuesto');
                var readOrgano = $('#readOrgano');
                var readUOrganica = $('#readUOrganica');
                //
                var createPuesto = $('#createPuesto');
                var readPuesto = $('#readPuesto');                
                //
                var createFuncionPuesto = $('#createFuncionPuesto');
                var readFuncionPuesto = $('#readFuncionPuesto');
                var updateFuncionPuesto = $('#updateFuncionPuesto');
                var deleteFuncionPuesto = $('#deleteFuncionPuesto');
                var readNivelEducativo = $('#readNivelEducativo');
                var readGradoEstudios = $('#readGradoEstudios');
                var readCarrera = $('#readCarrera');
                //                
                var createFormAcademica = $('#createFormAcademica');
                var readFormAcademica = $('#readFormAcademica');
                //
                var createConocimientOtros = $('#createConocimientOtros');
                var readConocimientOtros = $('#readConocimientOtros');
                var updateConocimientOtros = $('#updateConocimientOtros');
                var deleteConocimientOtros = $('#deleteConocimientOtros');
                var readListaCursos = $('#readListaCursos');
                var readNivelConoc = $('#readNivelConoc');
                //
                var createConocimientoTec = $('#createConocimientoTec');
                var readConocimientoTec = $('#readConocimientoTec');
                var updateConocimientoTec = $('#updateConocimientoTec');
                var deleteConocimientoTec = $('#deleteConocimientoTec');
                //
                var createConocimientoEspec = $('#createConocimientoEspec');            
                var readConocimientoEspec = $('#readConocimientoEspec');
                var updateConocimientoEspec = $('#updateConocimientoEspec');
                var deleteConocimientoEspec = $('#deleteConocimientoEspec');
                //
                var createExpeRequerida = $('#createExpeRequerida');            
                var readExpeRequerida = $('#readExpeRequerida');
                var updateExpeRequerida = $('#updateExpeRequerida');
                var deleteExpeRequerida = $('#deleteExpeRequerida');
                //
                var createExpNivelReq = $('#createExpNivelReq');
                var readExpNivelReq = $('#readExpNivelReq');
                var updateExpNivelReq = $('#updateExpNivelReq');
                var deleteExpNivelReq = $('#deleteExpNivelReq');
                //
                var createCompetencias = $('#createCompetencias');
                var readCompetencias = $('#readCompetencias');
                var updateCompetencias = $('#updateCompetencias');
                var deleteCompetencias = $('#deleteCompetencias');
                var readDiccompetencias = $('#readDiccompetencias');                
                
                //Inputs
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
                //
                var idPerfilPuesto = $('#idPerfilPuesto');
                var nomPuesto = $('#nomPuesto');
                var misionPuesto = $('#misionPuesto');
                var coord_Externa = $('#coord_Externa');
                var coord_Interna = $('#coord_Interna');
                //                
                var idFuncionPuesto = $('#idFuncionPuesto');
                var descripcionPuesto = $('#descripcionPuesto');
                var valFrecuencia_F = $('#valFrecuencia_F');
                var valConErrado_CE = $('#valConErrado_CE');
                var valComplejidad_CM = $('#valComplejidad_CM');
                //
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
                //
                var idConocOtros = $('#idConocOtros');
                var idListaCursoso = $('#idListaCursoso');
                var idNivelConoc = $('#idNivelConoc');
                //
                var idConocTec = $('#idConocTec');
                var idListaCursosT = $('#idListaCursosT');
                //
                var idConocesP = $('#idConocesP');
                var tipoConocim = $('#tipoConocim');
                var idListaCursose = $('#idListaCursose');
                var nroHoras = $('#nroHoras');
                var sustentar = $('#sustentar');
                //
                var idExpeRequerida = $('#idExpeRequerida');
                var expeGral = $('#expeGral');
                var expesPublico = $('#expesPublico');
                var expeEspecifica = $('#expeEspecifica');
                var observac = $('#observac');                
                //
                var idExpeRequerida = $('#idExpeRequerida');
                var idNivelReque = $('#idNivelReque');
                var idNivelLaboral = $('#idNivelLaboral');
                //
                var idCompetencias = $('#idCompetencias');
                var idDiccompetenc = $('#idDiccompetenc');

               createRequisionPuesto.click(function()
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
			url: 'createRequisionPuesto',
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
               
               readOrgano.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readOrgano',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    return 'IDORGANO: ' + e.IDORGANO + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//readOrgano
               
               readUOrganica.click(function()
               {
                   $('#respuesta').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readUOrganica',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuesta').html(
                                resultado.map(function(e)
                                {
                                    return 'IDUORGANICA: ' + e.IDUORGANICA + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuesta").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//readUOrganica

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
                                        return 'IDPERFIL: ' + e.IDPERFILPUESTO + ' - Puesto: ' + e.NOMPUESTO + ' - Misión: ' + e.MISIONPUESTO ;
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
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readFuncionPuesto';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto:  idPerfilPuesto.val()};
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
                                        return 'IDPERFIL: ' + e.IDPERFILPUESTO + ' - ID: ' + e.IDFUNCIONPUESTO + ' - Descripción: ' + e.DESCRIPCIONPUESTO ;
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
                                idPerfilPuesto: idPerfilPuesto.val(),
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
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readFormAcademica';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
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
                                    if(e.IDFORMACADEMICA)
                                        return  'IDPERFIL: ' + e.IDPERFILPUESTOA + ' - ID: ' + e.IDFORMACADEMICA + ' - Nivel Educativo: ' + e.NIVELEDUCATIVO + ' - Grado de Estudios: ' + e.GRADOESTUDIOS;
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
               
               readNivelEducativo.click(function()
               {
                   $('#respuestaFormAcademica').html('Cargando...');
                   var data ={};        
                   
                   $.ajax({
			type: 'GET',			
			url: 'readNivelEducativo',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaFormAcademica').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDNIVELEDUCATIVO)
                                        return  'IDNIVELEDUCATIVO: ' + e.IDNIVELEDUCATIVO  + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaFormAcademica").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read NivelEducativo
               
               readGradoEstudios.click(function()
               {
                   $('#respuestaFormAcademica').html('Cargando...');
                   var data ={};        
                   
                   $.ajax({
			type: 'GET',			
			url: 'readGradoEstudios',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaFormAcademica').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDGRADOESTUDIOS)
                                        return  'IDGRADOESTUDIOS: ' + e.IDGRADOESTUDIOS + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaFormAcademica").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read GradoEstudios
               
               readCarrera.click(function()
               {
                   $('#respuestaFormAcademica').html('Cargando...');
                   var data ={};        
                   
                   $.ajax({
			type: 'GET',			
			url: 'readCarrera',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaFormAcademica').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDCARRERA)
                                        return  'IDCARRERA: ' + e.IDCARRERA + ' - DESCRIPCION: ' + e.DESCRIPCION + ' - ESPECIALIDAD: ' + e.ESPECIALIDAD;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaFormAcademica").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Carrera
 
               createConocimientOtros.click(function()
               {                                      
                   $('#respuestaConocimientOtros').html('Cargando...');
                   
                   var data = {             
                                idPerfilPuesto: idPerfilPuesto.val(),
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
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readConocimientOtros';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
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
                                        return 'IDPERFIL: ' + e.IDPERFILPUESTOO + ' - ID: '+ e.IDCONOCOTROS + '- Curso: ' + e.CURSO + ' - Nivel: ' + e.NIVEL ;
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
               
               readListaCursos.click(function()
               {
                   $('#respuestaConocimientOtros').html('Cargando...');
                   var data ={};        
                   
                   $.ajax({
			type: 'GET',			
			url: 'readListaCursos',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaConocimientOtros').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDLISTACURSOS)
                                        return  'IDLISTACURSOS: ' + e.IDLISTACURSOS + ' - DESCRIPCION: ' + e.DESCRIPCION + ' - TIPO: ' + e.TIPOCONOCIMOTROS;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read ListaCursos
               
               readNivelConoc.click(function()
               {
                   $('#respuestaConocimientOtros').html('Cargando...');
                   var data ={};        
                   
                   $.ajax({
			type: 'GET',			
			url: 'readNivelConoc',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaConocimientOtros').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDNIVELCONOC)
                                        return  'IDNIVELCONOC: ' + e.IDNIVELCONOC + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaConocimientOtros").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read NivelConoc
               
               createConocimientoTec.click(function()
               {                                      
                   $('#respuestaConocimientoTec').html('Cargando...');
                   
                   var data = {
                                idPerfilPuesto: idPerfilPuesto.val(),
                                idListaCursosT: idListaCursosT.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createConocimientoTec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoTec').html(
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
                            $("#respuestaConocimientoTec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create ConocimientosTec
               
               readConocimientoTec.click(function()
               {
                   $('#respuestaConocimientoTec').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readConocimientoTec';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
                       url = 'readConocimientoTecId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaConocimientoTec').html(
                                resultado.map(function(e)
                                {
                                    if(e.CURSO)
                                        return  'IDPERFIL: ' + e.IDPERFILPUESTOT + ' - ID: '+ e.IDCONOCTEC + '- Curso: ' + e.CURSO;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaConocimientoTec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read ConocimientosTec
               
               updateConocimientoTec.click(function()
               {                                      
                   $('#respuestaConocimientoTec').html('Cargando...');
                                            
                   var data = {
                                idConocTec: idConocTec.val(),
                                idListaCursosT: idListaCursosT.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateConocimientoTec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoTec').html(
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
                            $("#respuestaConocimientoTec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update ConocimientosTec
 
               deleteConocimientoTec.click(function()
               {                                      
                   $('#respuestaConocimientoTec').html('Cargando...');
                   
                   var data = {
                                idConocTec: idConocTec.val()                           
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteConocimientoTec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoTec').html(
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
                            $("#respuestaConocimientoTec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete ConocimientosTec
 
               createConocimientoEspec.click(function()
               {                                      
                   $('#respuestaConocimientoEspec').html('Cargando...');
                   
                   var data = {
                                idPerfilPuesto: idPerfilPuesto.val(),
                                tipoConocim: tipoConocim.val(),
                                idListaCursose: idListaCursose.val(),
                                nroHoras: nroHoras.val(),
                                sustentar: sustentar.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createConocimientoEspec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoEspec').html(
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
                            $("#respuestaConocimientoEspec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create ConocimientoEspec
               
               readConocimientoEspec.click(function()
               {
                   $('#respuestaConocimientoEspec').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readConocimientoEspec';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
                       url = 'readConocimientoEspecId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaConocimientoEspec').html(
                                resultado.map(function(e)
                                {
                                    if(e.CURSO)
                                        return  'IDPERFIL: ' + e.IDPERFILPUESTOE + '- ID: '+ e.IDCONOCESP + '- Curso: ' + e.CURSO;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaConocimientoEspec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read ConocimientoEspec
               
               updateConocimientoEspec.click(function()
               {                                      
                   $('#respuestaConocimientoEspec').html('Cargando...');
                                            
                   var data = {
                                idConocesP: idConocesP.val(),
                                tipoConocim: tipoConocim.val(),
                                idListaCursose: idListaCursose.val(),
                                nroHoras: nroHoras.val(),
                                sustentar: sustentar.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateConocimientoEspec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoEspec').html(
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
                            $("#respuestaConocimientoEspec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update ConocimientoEspec
 
               deleteConocimientoEspec.click(function()
               {                                      
                   $('#respuestaConocimientoEspec').html('Cargando...');
                   
                   var data = {
                                idConocesP: idConocesP.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteConocimientoEspec',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaConocimientoEspec').html(
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
                            $("#respuestaConocimientoEspec").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete ConocimientoEspec
               
               createExpeRequerida.click(function()
               {                                      
                   $('#respuestaExpeRequerida').html('Cargando...');
                   
                   var data = {
                                idPerfilPuesto: idPerfilPuesto.val(),
                                expeGral: expeGral.val(),
                                expesPublico: expesPublico.val(),
                                expeEspecifica: expeEspecifica.val(),
                                observac: observac.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createExpeRequerida',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpeRequerida').html(
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
                            $("#respuestaExpeRequerida").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create ExpeRequerida
               
               readExpeRequerida.click(function()
               {
                   $('#respuestaExpeRequerida').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readExpeRequerida';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
                       url = 'readExpeRequeridaId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaExpeRequerida').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDEXPEREQUERIDA)
                                        return 'IDPERFIL: ' + e.IDPERFILPUESTOX + ' - ID: '+ e.IDEXPEREQUERIDA + ' - EXPEGRAL: ' + e.EXPEGRAL + ' - EXPESPUBLICO: ' + e.EXPESPUBLICO + ' - EXPESPUBLICO: ' + e.EXPESPUBLICO + ' - EXPEESPECIFICA: ' + e.EXPEESPECIFICA;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaExpeRequerida").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read ExpeRequerida
               
               updateExpeRequerida.click(function()
               {                                      
                   $('#respuestaExpeRequerida').html('Cargando...');
                                            
                   var data = {
                                idExpeRequerida: idExpeRequerida.val(),
                                expeGral: expeGral.val(),
                                expesPublico: expesPublico.val(),
                                expeEspecifica: expeEspecifica.val(),
                                observac: observac.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateExpeRequerida',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpeRequerida').html(
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
                            $("#respuestaExpeRequerida").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update ExpeRequerida
 
               deleteExpeRequerida.click(function()
               {                                      
                   $('#respuestaExpeRequerida').html('Cargando...');
                   
                   var data = {
                                idExpeRequerida: idExpeRequerida.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteExpeRequerida',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpeRequerida').html(
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
                            $("#respuestaExpeRequerida").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete ExpeRequerida
               
               createExpNivelReq.click(function()
               {                                      
                   $('#respuestaExpNivelReq').html('Cargando...');
                   
                   var data = {                       
                                idExpeRequerida: idExpeRequerida.val(),
                                idNivelLaboral: idNivelLaboral.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createExpNivelReq',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpNivelReq').html(
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
                            $("#respuestaExpNivelReq").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create ExpNivelReq
               
               readExpNivelReq.click(function()
               {
                   $('#respuestaExpNivelReq').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idExpeRequerida.val() === "")
                   {
                       data={};
                       url = 'readExpNivelReq';
                   }                                           
                   else
                   {
                       data={idExpeRequerida: idExpeRequerida.val()};
                       url = 'readExpNivelReqId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaExpNivelReq').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDNIVELREQUE)
                                        return  'IDEXPEREQUERIDA: ' + e.IDEXPEREQUERIDA + ' - ID: '+ e.IDNIVELREQUE + ' - NIVEL LABORAL: ' + e.NIVELLABORAL;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaExpNivelReq").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read ExpNivelReq
               
               updateExpNivelReq.click(function()
               {                                      
                   $('#respuestaExpNivelReq').html('Cargando...');
                                            
                   var data = {
                                idNivelReque: idNivelReque.val(),
                                idNivelLaboral: idNivelLaboral.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateExpNivelReq',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpNivelReq').html(
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
                            $("#respuestaExpNivelReq").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update ExpNivelReq
 
               deleteExpNivelReq.click(function()
               {                                      
                   $('#respuestaExpNivelReq').html('Cargando...');
                   
                   var data = {
                                idNivelReque: idNivelReque.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteExpNivelReq',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaExpNivelReq').html(
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
                            $("#respuestaExpNivelReq").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete ExpNivelReq
               
               createCompetencias.click(function()
               {                                      
                   $('#respuestaCompetencias').html('Cargando...');
                   
                   var data = {
                                idPerfilPuesto: idPerfilPuesto.val(),
                                idDiccompetenc: idDiccompetenc.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'createCompetencias',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaCompetencias').html(
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
                            $("#respuestaCompetencias").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Create Competencias
               
               readCompetencias.click(function()
               {
                   $('#respuestaCompetencias').html('Cargando...');
                   
                    var data;
                    var url;
                                      
                   if(idPerfilPuesto.val() === "")
                   {
                       data={};
                       url = 'readCompetencias';
                   }                                           
                   else
                   {
                       data={idPerfilPuesto: idPerfilPuesto.val()};
                       url = 'readCompetenciasId';
                   }                       
                   
                   $.ajax({
			type: 'GET',			
			url: url,
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaCompetencias').html(
                                resultado.map(function(e)
                                {
                                    if(e.IDCOMPETENCIAS)
                                        return  'IDPERFIL: ' + e.IDPERFILPUESTOC + ' - ID: '+ e.IDCOMPETENCIAS + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                    else
                                        return 'Error: ' + e.error + ' - Mensaje: ' + e.mensaje ;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaCompetencias").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Read Competencias
               
               updateCompetencias.click(function()
               {                                      
                   $('#respuestaCompetencias').html('Cargando...');
                                            
                   var data = {
                                idCompetencias: idCompetencias.val(),
                                idDiccompetenc: idDiccompetenc.val()
                              };
                   
                   
                   $.ajax({
			type: 'POST',			
			url: 'updateCompetencias',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaCompetencias').html(
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
                            $("#respuestaCompetencias").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Update Competencias
 
               deleteCompetencias.click(function()
               {                                      
                   $('#respuestaCompetencias').html('Cargando...');
                   
                   var data = {
                                idCompetencias: idCompetencias.val()
                              };
                   
                   console.log('Data: ' + data);
                   $.ajax({
			type: 'POST',			
			url: 'deleteCompetencias',
                        data: data,
			success: function(resultado)
                        {                                                        
                            $('#respuestaCompetencias').html(
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
                            $("#respuestaCompetencias").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//Delete Competencias
               
               readDiccompetencias.click(function()
               {
                   $('#respuestaCompetencias').html('Cargando...');
                   var data={};
                   
                   $.ajax({
			type: 'GET',			
			url: 'readDiccompetencias',
                        data: data,
			success: function(resultado)
                        {
                            console.log(resultado);
                            
                            $('#respuestaCompetencias').html(
                                resultado.map(function(e)
                                {
                                    return 'ID: ' + e.IDDICCOMPETENCIAS + ' - DESCRIPCION: ' + e.DESCRIPCION;
                                }).join('<br>')
                            );
			},
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#respuestaCompetencias").html("jqXHR: " + jqXHR + "<br>textStatus: " + textStatus + "<br>errorThrown: " + errorThrown);
                        }
                    });
               });//readDiccompetencias
            });//Jquery

        </script>
</body>
</html>