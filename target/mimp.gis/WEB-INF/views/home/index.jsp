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
<body class="app-home">
	<div class="wrapper">
		<div class="bar-nav">
			<div class="bar-inner">

				<span><img src="<c:url value='/assetsvisor/img/logo_geomimp.png'/>" alt=""></span>
				<div class="sep-log"></div>
				<span class="geomimp-logo"></span>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img id="btnHomeApp" src="<c:url value='/assetsvisor/img/home.png'/>" width="21px" title="Reiniciar App" alt="Reiniciar App"></a></span>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <strong>Visitante Nro. :</strong>
                                    <a href="#" title="contador de visitas "><img src="http://counter2.allfreecounter.com/private/contadorvisitasgratis.php?c=017734fcf0ce76caad3589d100dd3f14" border="0" title="contador de visitas" alt="contador de visitas"></a>
                                </span>

				<div class="btn-group bt-user pull-right">
				  <a class="btn dropdown-toggle btn-primary btn-small" data-toggle="dropdown" href="#">
				  	<i class="fa fa-user"></i>
				    Administrador
				    <span class="caret"></span>
				  </a>
				  <ul class="dropdown-menu">
				     <li><a id="btnLogin" href="#">Administrador</a></li>
				     <!--<li><a href="#">Cambiar contraseña</a></li>
				     <li class="divider"></li>
				     <li><a href="#">Cerrar Sesión</a></li>-->
				  </ul>
				</div>
                                
                                <div class="btn-group bt-user pull-right panel-move">
                    <div class="btn-group">
                        <button class="btn btn-small btn-primary active" onclick="lib.toggle_mp(this)" data-class="hybrid"><i class="fa fa-bar-chart"></i> <i class="fa fa-globe"></i></button>
                        <button class="btn btn-small btn-primary" onclick="lib.toggle_mp(this)" data-class="char-all"><i class="fa fa-bar-chart"></i></button>
                        <button class="btn btn-small btn-primary" onclick="lib.toggle_mp(this)" data-class="map-all"><i class="fa fa-globe"></i></button>
                    </div>
                </div>

			</div>
		</div>

		<div class="cont-celds">

			<div class="celd-controls celds" >
				<div class="cn-nav">
					<div class="nav-inner" id="da-sidebar">

					<!-- main menu -->

					<div id="da-main-nav" class="btn-container">
                        <ul>
                            <li class="active">
                                <a class="tg-it" href="#">
                                    <i class="fa fa-globe"></i>
                                    Ubicación Geográfica
                                    <i class="led-act"></i>
                                </a>
                                <div class="ds-tg">
									<div class="ds-inner">
	                                	<div class="box-w-list">
	                                		 <span class="label  title-search">Departamento:</span>

	                                		 <select name="Departamento" id="ddlDepartamnto">
	                                		 	<option value="00">Per&uacute;</option>
	                                		 	<option value="01">Amazonas</option>
                                                                <option value="02">Ancash</option>
								<option value="03">Apurimac</option>
	                                		 	<option value="04">Arequipa</option>
	                                		 	<option value="05">Ayacucho</option>
	                                		 	<option value="06">Cajamarca</option>                                                                
	                                		 	<option value="07">Callao</option>
	                                		 	<option value="08">Cusco</option>
	                                		 	<option value="09">Huancavelica</option>
	                                		 	<option value="10">Huanuco</option>
								<option value="11">Ica</option>
								<option value="12">Junin</option>
								<option value="13">La Libertad</option>
								<option value="14">Lambayeque</option>
								<option value="15">Lima</option>
								<option value="16">Loreto</option>
								<option value="17">Madre de Dios</option>
								<option value="18">Moquegua</option>
								<option value="19">Pasco</option>
								<option value="20">Piura</option>
								<option value="21">Puno</option>
								<option value="22">San Martin</option>
								<option value="23">Tacna</option>
								<option value="24">Tumbes</option>
								<option value="25">Ucayali</option>
                                                                <option value="vraem">VRAEM</option>
	                                		 </select>
	                                	</div>
	                                	<div class="box-w-list">
	                                		 <span class="label  title-search">Provincia:</span>
	                                		 <select name="provincia" id="ddlProvincia">
	                                		 	<option value="00">...</option>
	                                		 	
	                                		 </select>
	                                	</div>

	                                	<div class="box-w-list">
	                                		 <span class="label  title-search">Distrito:</span>
	                                		 <select name="distrito" id="ddlDistrito">
	                                		 	<option value="00">...</option>
	                                		 	
	                                		 </select>
	                                	</div>
	                                	<!--<div class="box-w-list">
	                                		 <span class="label  title-search">Centro Poblado:</span>
	                                		 <select name="c-poblado" id="">
	                                		 	<option value="00">...</option>
	                                		 	
	                                		 </select>
	                                	</div>
                                               //-->
                                	</div>
                                </div>
                            </li>
                            <li>
                                <a id="alistaServBrind" class="tg-it" href="#">
                                    Servicios que Brindamos
                                      <i class="led-act red"></i>
                                </a>
                                <ul id="listaServBrind" class="ds-tg ds-list">
                                    <!--
                                    <li><div style="font-weight: bold">1.INABIF :   </div></li>
                                    <li><div class="rw-t"><input type="checkbox">CAR USPNNA </div></li>
                                    <li><div class="rw-t"><input type="checkbox">CAR USPPAM</div></li>
                                    <li><div class="rw-t"><input type="checkbox">CAR USPPD</div></li>
                                    <li><div class="rw-t"><input type="checkbox">CEDIF</div></li>
                                    <li><div class="rw-t"><input type="checkbox">IA</div></li>
                                    <li><div style="font-weight: bold">2.CONTIGO : </div></li>  
                                    <li><div class="rw-t"><input type="checkbox"> CEM </div></li>
                                    <li><div class="rw-t"><input type="checkbox"> CAI </div></li>
                                    <li><div style="font-weight: bold">3.YACHAY :</div></li>   
                                    <li><div class="rw-t"><input type="checkbox"> EC </div></li>
                                    <li><div style="font-weight: bold">4.VIDA DIGNA : </div></li>   
                                    <li><div class="rw-t"><input type="checkbox"> CARPAM </div></li>
                                    <li><div class="rw-t"><input type="checkbox"> CAN </div></li>
                                    <li><div style="font-weight: bold">5.DGA : </div></li> 
                                    <li><div class="rw-t"><input type="checkbox"> UA </div></li>
                                    <li><div style="font-weight: bold">6.DGNNA :</div></li>   
                                    <li><div class="rw-t"><input type="checkbox"> UIT </div></li>
                                    <li><div style="font-weight: bold">7.CONADIS : </div></li> 
                                    <li><div class="rw-t"><input type="checkbox"> RNPD </div></li>
                                    //-->	
                                </ul>
                            </li>
                            <li>
                                <a  id="alistaServProm" class="tg-it" href="#">
                                    Servicios que Promovemos
                                      <i class="led-act blue"></i>
                                </a>
                                <ul id="listaServProm" class="ds-tg ds-list">
                                    <!--
                                    <li><div style="font-weight: bold">1.DGNNA :</div></li> 
                                    <li><div class="rw-t"><input type="checkbox">DEMUNA</div></li>
                                    <li><div class="rw-t"><input type="checkbox">JUGUEMOS</div></li>
                                    <li><div style="font-weight: bold">2.DGFC :</div></li> 
                                    <li><div class="rw-t"><input type="checkbox">CIAM</div></li>
                                    <li><div style="font-weight: bold">3.DGCVG:</div></li> 
                                    <li><div class="rw-t"><input type="checkbox">CASA REFUGIO</div></li>
                                    <li><div style="font-weight: bold">4.CONADIS :</div></li> 
                                    <li><div class="rw-t"><input type="checkbox">OMAPED</div></li>
                                    <li><div class="rw-t"><input type="checkbox">OREDIS</div></li>       
                                    //-->
                                </ul>
                            </li>
                            <li>
                                <a id="alistaServSup" class="tg-it" href="#">
                                    Servicios que Supervisamos
                                      <i class="led-act purple"></i>
                                </a>
                                <ul id="listaServSup" class="ds-tg ds-list">
                                    <!--
                                    <li><div style="font-weight: bold">1.DGNNA :</div></li> 
                                    <li><div class="rw-t"><input type="checkbox"> CAR</div></li>
                                    <li><div style="font-weight: bold">2.DGFC :</div></li> 
                                    <li><div class="rw-t"><input type="checkbox"> CARPAM*</div></li>
                                    <li><div class="rw-t"><input type="checkbox"> SBP**</div></li>
                                    <li><div style="font-weight: bold">3.DGCVG:</div></li> 
                                    <li><div class="rw-t"><input type="checkbox"> C.A. VIOLENCIA GENERO</div></li>
                                    //-->
                                </ul>
                            </li>
                        </ul>
                    </div>


					</div>
				</div>


				<div class="cn-data">
				<div class="cn-data-inner">

					<ul class="nav nav-tabs" id="myTab">
					  <li class="active"><a href="#cnt-graficos"> 	<i class="fa fa-pie-chart"></i>  Gráficos Estadísticos</a></li>
					  <li><a href="#cnt-resumen"><i class="fa fa-file-text"></i> Resumen</a></li>
					</ul>

					<div class="tab-content">
					  <div class="tab-pane active" id="cnt-graficos">

					       <!-- <div id="pie-chart" style=" height: 380px; margin: 0 auto"></div> -->
                                               <!-- <div id="area-chart" style=" height: 380px; margin: 0 auto"></div> -->

					  </div>
					  <div class="tab-pane" id="cnt-resumen">Resumen</div>
					</div>

				</div>

				</div>
			</div>
			<div  class="celd-map celds" >
				<div id="gmap">

			    </div>

			    <div class="btn-group map-controls">
	              <button class="btn" type="button"><i><img id="toolZoomAll" src="<c:url value='/assetsvisor/img/globe.png'/>"  alt=""></i></button>
	              <button class="btn" type="button"><i><img id="toolZoomIn" src="<c:url value='/assetsvisor/img/zoomin.png'/>" alt=""></i></button>
	              <button class="btn" type="button"><i><img id="toolZoomOut" src="<c:url value='/assetsvisor/img/zoomout.png'/>" alt=""></i></button>
	              <button class="btn" type="button"><i><img id="toolMove" src="<c:url value='/assetsvisor/img/move.png'/>" alt=""></i></button>
	              <button class="btn" type="button"><i><img id="toolInfo" src="<c:url value='/assetsvisor/img/info.png'/>" alt=""></i></button>
	              <button class="btn" type="button"><i><img id="toolPrint" src="<c:url value='/assetsvisor/img/print.png'/>" alt=""></i></button>
	            </div>


	            <div class="leyend-map box-drag">
	            	<div id="divLeyenda" class="leyend-inner">
	            		<div class="title-leyend to-drag">Servicios</div>
	            		<!--
                                <div class="item-static"><i class="cm-icon"><img src="<c:url value='/assetsvisor/img/icons/car.png'/>" alt=""></i> Texto 01</div>
	            		<div class="item-static"><i class="cm-icon"><img src="<c:url value='/assetsvisor/img/icons/omaped.png'/>" alt=""></i> Texto 02</div>
	            		<div class="item-static"><i class="cm-icon"><img src="<c:url value='/assetsvisor/img/icons/demuna.png'/>" alt=""></i> Texto 03</div>
	            		<div class="item-static"><i class="cm-icon"><img src="<c:url value='/assetsvisor/img/icons/cedif.png'/>" alt=""></i> Texto 04</div>
                                //-->
                                
	            	</div>
	            </div>
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
</body>
</html>