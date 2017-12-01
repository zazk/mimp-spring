var dominioSite="181.65.173.175:8080";

var map;
var limites;
var markers = [];
var texReservado=["la","del", "de", "como", "a", "y", "con", "los", "o", "su","atendida","en"];
var infowindow ;
var flgVraem=false;
var prvVraem="0302,0306,0504,0505,0809,0905,0907,1202,1201,1206";
var dstVraem="030202,030219,030208,030601,030604,030605,030606,050402,050401,050408,050406,050407,050502,050503,050504,050505,050506,050509,050501,050507,050508,080902,080907,080910,080909,090503,090510,090509,090702,090703,090704,090705,090706,090707,090709,090710,090701,090711,090713,090714,090715,090716,090717,090718,120203,120124,120135,120604,120606,120608";
var servShow="'SER001','SER002','SER007','SER009','SER008','SER010','SER017','SER016','SER012','SER013','SER015'";


var lib = {};

lib.clear = function(){
     $("#myTab").empty().next(".tab-content").eq(0).empty();
}


lib.toggle_mp = function(el){
  $(el).addClass("active").siblings().removeClass("active");
  $(".cont-celds").eq(0).attr("id",$(el).data("class"));
  $(window).trigger("resize");
}

lib.shuffle = function(array){
 var currentIndex = array.length, temporaryValue, randomIndex ;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}       

/*lib.createTab = function(id, name, callback){
  var $tb = $("#tab-content-"+id);
  if($tb.length == 0){
    var $lk = $('<li><a id="tab-'+id+'" href="#tab-content-'+id+'">'+ name +'</a></li>');
    $("#myTab").append($lk);
    $tb = $('<div id="tab-content-'+ id +'" class="tab-pane active"></div>');
    $(".cn-data .tab-content").eq(0).append($tb);

    $lk.find("a").on("click", function(){
      $(this).tab('show');
    }).trigger("click");

  } 
  if(typeof callback == "function") callback($tb);
}*/


lib.createTab = function(id, name, callback){
  var $tb = $("#tab-content-"+id);
  if($tb.length == 0){
    var $lk = $('<li><a id="tab-'+id+'" href="#tab-content-'+id+'">'+ name +'</a></li>');
    $("#myTab").append($lk);
    $tb = $('<div id="tab-content-'+ id +'" class="tab-pane active"></div>');
    $(".cn-data .tab-content").eq(0).append($tb);

    $lk.find("a").on("click", function(e){
      e.preventDefault();
      $(this).tab('show');
    }).trigger("click");

  } 
  if(typeof callback == "function") callback($tb);
}

lib.graph = function(jsn){
   
   
   Highcharts.setOptions({ colors: lib.shuffle([ "#ff5050", "#5b9bd5", "#7f7f7f"])});
   
   if(!$(".cn-data").eq(0).is(":visible")) $(".cn-data").eq(0).show();

      lib.createTab(jsn.tabId, jsn.tabTitle, function($tb){
         var $dv_gr = $('<div class="new_graphic" style="height:'+ (jsn.height || 380)+'px; margin: 0 auto 15px;"></div>');
        $tb.append($dv_gr);

        $dv_gr.highcharts(jsn, function(o){ $(o.container).children("svg").children("text:last-child").hide();});
      });
}

lib.grid = function(jsn){
  
    
        
     if(!$(".cn-data").eq(0).is(":visible")) $(".cn-data").eq(0).show();

     lib.createTab(jsn.tabId, jsn.tabTitle, function($tb){
        var $dd = $('<div class="dr-table"><div class="header-table"><i class="fa fa-table"></i>&nbsp;  '+jsn.title+' <i class="fa fa-angle-double-down"></i></div><div class="cnt-tb"><table class="beutif">'+jsn.data.join('')+'</table></div></div>'); 
        $dd.find(".header-table").on("click", function(){
            $dd.find(".cnt-tb").slideToggle();
          //  $(".cn-data").eq(0).perfectScrollbar("update");
        });

       $tb.append($dd);

     });
}

lib.confhome = function(){

        //wind dragable
       $(".box-drag").draggable({ handle: ".to-drag", containment: "parent" });

       // scrollbars
        $('.cn-nav, .cn-data').on("mouseenter", function(){
            $(this).perfectScrollbar("update");
        }).perfectScrollbar();

        //dropbow
        $('.dropdown-toggle').dropdown();


        //tab
        $('#myTab a').click(function (e) {
          e.preventDefault();
          $(this).tab('show');
        });

        //menu slideToggle
        $('#da-main-nav .tg-it').on("click", function(e){
            e.preventDefault();
            var $o = $(this), $li = $o.parent("li");
            if(!$li.hasClass("active")){
                 $o.next().slideDown("fast", function(){
                       $li.addClass("active");
                       $o.parents(".cn-nav").perfectScrollbar("update");
                 });
              
            }else{
                 $o.next().slideUp("fast", function(){
                     $li.removeClass("active");
                      $o.parents(".cn-nav").perfectScrollbar("update");
                 });
                
            }
        })
}




$(document).on("ready", function(){
    
      lib.confhome();
      
      lib.clear();
     
      reporteNacional();

      cargaMapa();
            
      $('#btnLogin').on('click', function() {
          goLogin();
      });
    
      $('#ddlDepartamnto').on('change', function() {
          
          if (this.value==="00"){
              reporteNacional();
              return;
          }
          
          //alert(this.value);
          zoomLimitePolitico(this.value);
          
          if (this.value==="vraem"){
              flgVraem=true;
          }else{
              flgVraem=false;
          }
          //alert(flgVraem);
          
          cargarProvincias(this.value);
          getCentrosDepa(this.value);  
          reporteDepartamental(this.value);
      });
      
      $('#ddlProvincia').on('change', function() {          
          zoomLimitePolitico(this.value);
          cargarDistritos(this.value);
          getCentrosProv(this.value);
          reporteProvincial(this.value);
      });
      
      $('#ddlDistrito').on('change', function() {          
          zoomLimitePolitico(this.value);
          //cargarCCPP(this.value);
          getCentrosDist(this.value);
          reporteDistrital(this.value);
      });
      
      $('#toolZoomAll').on('click', function() {
          zoomFull();
         
      });
      
      $('#toolZoomIn').on('click', function() {          
         zoomIn();
      });
      
      $('#toolZoomOut').on('click', function() {          
         zoomOut();
      });
      
      $('#toolPrint').on('click', function() {          
         printMap();
      });
      
      limites=  new google.maps.KmlLayer(null);

});

function goLogin(){
    window.open("http://181.65.173.175:8080/mimp.gis/pages/usuarios/panel");
   // alert('glogin');
}
              
function cargaMapa(){
            //alert('cargaMapa');
            
    var mapOptions = {
	mapTypeId: google.maps.MapTypeId.ROADMAP ,
        zoom: 5,
        center: new google.maps.LatLng(-8.559, -73.655)
    };
    map = new google.maps.Map(document.getElementById('gmap'),
      mapOptions);   
      
   infowindow = new google.maps.InfoWindow({
	                           maxWidth: 300
	                        });
                                
                                
   $('#alistaServBrind').trigger('click');
   $('#alistaServProm').trigger('click');
   $('#alistaServSup').trigger('click');
}

function zoomLimitePolitico(ubigeo){
    limites.setMap(null);    
    //limites.setMap(null);
    
    
    var ruta='http://64.64.14.79/~gmap/kml/limites/'+ubigeo+ ".kml";    
    limites  = new google.maps.KmlLayer(ruta,{
	  	preserveViewport: false
    });
    limites.setMap(map);
    //limites.setMap(null);
}

function cargarProvincias(ubigeo){
                                  
          //var where="";
          var param="";
          
          if (flgVraem){
            var param="q=SELECT * FROM GEO_PROVINCIA WHERE COD_PROV IN (0302,0306,0504,0505,0809,0905,0907,1202,1201,1206) ";                        
          }else{            
            var param="q=select * from GEO_PROVINCIA where COD_DEPAR = '"+ubigeo+"'";            
          }
                              
	  var h=new Date();	             
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 	                                 
              
              var listProvincias="<option value=00> ------.------- </option>";
              
              $.each(datos, function(i, item) {
                   listProvincias+="<option value="+datos[i].COD_PROV+"> "+ capitalizarTexto(datos[i].NOM_PROV)+" </option>";                   
              });
                            
              $("#ddlProvincia")
               .find('option')
               .remove()
               .end()
               .append(listProvincias)
              ;
              
              $("#ddlDistrito")
               .find('option')
               .remove()
               .end()                
              ;
              
          });
          
          
}

function cargarDistritos(ubigeo){
    
          var where="";
          var param="";	
                   
          if (flgVraem){
            var where=" where COD_PROV = '"+ubigeo+"' and COD_DIST IN ("+ dstVraem +")";
            var param="q=select * from GEO_DISTRITO " + where;	          
          }else{
            var where=" where COD_PROV = '"+ubigeo+"'";
            var param="q=select * from GEO_DISTRITO " + where;	               
          }
         	          
	  var h=new Date();	
          
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 	                                               
              var listDistritos="<option value=00> ------.------- </option>"; 
              
              $.each(datos, function(i, item) {
                   listDistritos+="<option value="+datos[i].COD_DIST+"> "+capitalizarTexto(datos[i].NOM_DIST)+" </option>";                   
              });
                            
              $("#ddlDistrito")
               .find('option')
               .remove()
               .end()
               .append(listDistritos)
              ;              
          });
    
}

function zoomCCPP(xy){
    
}

function get_SER012(ubigeo){       
    
          var param="q=select SUM(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M, SUM(NUM_BEN_EJEC1_H) AS NUM_BEN_EJEC1_H  from GEO_REPORTE_SERVICIOS WHERE COD_SERV='SER012' AND SUBSTR(UBIGEO,0,2)=15"  ;		          
	  var h=new Date();	
          
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 	                                               
              
              
              $.each(datos, function(i, item) {
                   listDistritos+="<option value="+datos[i].COD_DIST+"> "+datos[i].NOM_DIST+" </option>";                   
              });
                            
              $("#ddlDistrito")
               .find('option')
               .remove()
               .end()
               .append(listDistritos)
              ;              
          });
    
}

function clearLeyenda(){
        
    $("#divLeyenda").html("");
    $("#divLeyenda").append("<div class='title-leyend to-drag'>SERVICIOS</div>");
    
}

function clearToc(){
    $("#listaServBrind").html("");
    $("#listaServProm").html("");
    $("#listaServSup").html("");
    
}

function addToc(tipoServ, codServ, siglasServ, nombServ, rutaImg){
     //<i class='cm-icon'><img src='"+  rutaImg +"' alt=''></i>    
     texto_="<div class='rw-t'><input type='checkbox' checked onClick=clickCapas('i"+codServ+"',this);> <i class='cm-icon'><img src='"+  rutaImg +"' alt=''></i>    "+siglasServ+" </div>";    
            
     switch(tipoServ) {
      case "BRINDA EL MIMP":
        $("#listaServBrind").append("<li>" + texto_ + "</li>");
        break;
      case "PROMUEVE EL MIMP":
        $("#listaServProm").append("<li>" + texto_ + "</li>");
        break;
      case "SUPERVISA":
        $("#listaServSup").append("<li>" + texto_ + "</li>");
        break;
     }
    
}

function addLeyenda(rIcono, texto, codServ){
   cnt="<div id='i"+codServ+"' class='item-static'><i class='cm-icon'><img src='"+rIcono+"' alt=''></i> "+texto+"</div>";
   $("#divLeyenda").append(cnt); 
   
}

function maysPrimera(string){     
    return string.charAt(0).toUpperCase() + string.slice(1); 
} 

function capitalizarTexto(texto){
     texto=$.trim(texto).toLowerCase();
     arrText=texto.split(" ");
     var textMayuscula="";
     for (i = 0; i < arrText.length ; i++) {
         if($.trim(arrText[i])!==""){
             if(texReservado.indexOf(arrText[i], 0)=== -1){
                 textMayuscula+= maysPrimera(arrText[i]) + " ";
             }else{
                 textMayuscula+=arrText[i] + " ";
             }             
         }         
     }     
     return textMayuscula;    
}

function creaInfoWindows(marker, nombCA, dirCA,telfCA, ref){
    google.maps.event.addListener(marker, 'click', (function(marker, nombCA, dirCA,telfCA ) {
	                        return function() {
                                  //cont="<div><strong>Nombre CAcc :</strong> "+nombCA+"<hr><br> <strong>Direcci&oacute;n :</strong> "+dirCA+"<hr> <BR> <strong>Telefono:</strong> " + telfCA +   "<hr><br> <strong> Fachada :</strong> <br> <img src='http://64.64.14.79/~gmap/icons/fachada.png'></div>";
                                  cont="<table><colgroup><col width='130px;'></colgroup><tr><td style='vertical-align:top'><strong>Centro de Atenci&oacute;n :</strong> </td> <td > "+ capitalizarTexto(nombCA)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Direcci&oacute;n :</strong></td> <td> "+ capitalizarTexto(dirCA)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Referencia :</strong></td> <td> "+ capitalizarTexto(ref)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Telefono:</strong> </td> <td> " + capitalizarTexto(telfCA) +   "</td></tr> ";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Fachada </strong> </td> <td></td> </tr>";
                                  cont+="<tr> <td colspan='2'> <img src='http://64.64.14.79/~gmap/icons/fachada.png'></td></tr></table>";
	                          infowindow.setContent(cont);
	                          infowindow.open(map, marker);
	                        };
	                  })(marker,nombCA, dirCA,telfCA));
}

function getCentrosDepa(ubigeo){              
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param=""  ;		          
          
          if (flgVraem){
            param="q=select CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV,SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_PROV in (0302,0306,0504,0505,0809,0905,0907,1202,1201,1206)";   
          }else{
            param="q=select CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV,SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DEPAR=" + ubigeo  ;		             
          }
          
	  var h=new Date();	
          
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
              //alert(datos.length);
                                          
              var distinct=[];                            
              $.each(datos, function(i, item) {                                                                           
                   lat_=datos[i].COORD_X;
		   lon_=datos[i].COORD_Y;
                   titulo=datos[i].NOM_CA;                   
                   codServ=datos[i].COD_SERV;
                   nomServ=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   siglasServ=datos[i].SIGLAS_SERV;                   
                   nombCA=datos[i].NOM_CA;   
                   dirCA=datos[i].DIR_CA;   
                   telfCA=datos[i].TELEF_CA; 
                   refCA=datos[i].REF_CA; 
                   
                   rutIcono=DefineIconServ(codServ);      
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                          distinct.push(codServ);                                                   
                          var latlng = new google.maps.LatLng(lon_,lat_);                   
                          var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: titulo,
				     	      icon: rutIcono
		          });     
                          
                          creaInfoWindows(marker, nombCA, dirCA,telfCA, refCA);                                                                                                      
                          
                          markers.push([marker, "i"+codServ]);                             
                          addLeyenda(rutIcono,siglasServ,codServ);
                          addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);                          
                   }                                                        
              });                                          
                        
          });
    
          
}

function getCentrosProv(ubigeo){          
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param="q=select CA.REF_CA, CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV, SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_PROV=" + ubigeo  ;		          
	  var h=new Date();	
          
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
              
              var distinct=[];
                            
              $.each(datos, function(i, item) {                                     
                                      
                   lat_=datos[i].COORD_X;
		   lon_=datos[i].COORD_Y;
                   titulo=datos[i].NOM_CA;
                   codServ=datos[i].COD_SERV;
                   nomServ=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   siglasServ=datos[i].SIGLAS_SERV;
                   nombCA=datos[i].NOM_CA;   
                   dirCA=datos[i].DIR_CA;   
                   telfCA=datos[i].TELEF_CA;  
                   refCa=datos[i].REF_CA; 
                   
                   rutIcono=DefineIconServ(codServ);
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);  
                         addLeyenda(rutIcono,siglasServ,codServ);
                         addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);
                   }
                                                                            
                   var latlng = new google.maps.LatLng(lon_,lat_);                                                         
                   var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: titulo,
				     	      icon: rutIcono
		   });  
                   
                   creaInfoWindows(marker, nombCA, dirCA,telfCA,refCa);
                   
                   markers.push([marker, "i"+codServ]);                             
                   
              });
                            
                        
          });
    
          
}

function getCentrosDist(ubigeo){          
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param="q=select CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV, SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DIST=" + ubigeo  ;		          
	  var h=new Date();	
          
	  var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
               
              var distinct=[];
              
              $.each(datos, function(i, item) {
                                      
                   lat_=datos[i].COORD_X;
		   lon_=datos[i].COORD_Y;
                   titulo=datos[i].NOM_CA;
                   codServ=datos[i].COD_SERV;                   
                   nomServ=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   siglasServ=datos[i].SIGLAS_SERV;
                   nombCA=datos[i].NOM_CA;   
                   dirCA=datos[i].DIR_CA;   
                   telfCA=datos[i].TELEF_CA; 
                   refCA=datos[i].REF_CA; 
                   
                   rutIcono=DefineIconServ(codServ);
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);  
                         addLeyenda(rutIcono,siglasServ,codServ);
                         addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);
                   }
                   
                   var latlng = new google.maps.LatLng(lon_,lat_);
                   
                   var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: titulo,
				     	      icon: rutIcono  
		   });  
                   
                   creaInfoWindows(marker, nombCA, dirCA,telfCA,refCA);
                   
                   markers.push([marker, "i"+codServ]);                             
                   
              });
                            
                        
          });
    
          
}

function DefineIconServ(codServ){
    
    rutaIcon="http://64.64.14.79/~gmap/icons/yellow-dot.png";
    
    
    switch(codServ) {
    case "SER001":
        rutaIcon="http://64.64.14.79/~gmap/icons/CEM.png"; 
        break;
    case "SER002":
        rutaIcon="http://64.64.14.79/~gmap/icons/CAI.png";
        break;
        
    case "SER003":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;
        
    case "SER004":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;
    
    case "SER005":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;
        
    case "SER006":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;
    
    case "SER007":
        rutaIcon="http://64.64.14.79/~gmap/icons/USPNNA.png";
        break;
        
    case "SER008":
        rutaIcon="http://64.64.14.79/~gmap/icons/USPPAM.png";
        break;
    
    case "SER009":
        rutaIcon="http://64.64.14.79/~gmap/icons/USPPD.png";
        break;           
    
    case "SER010":
        rutaIcon="http://64.64.14.79/~gmap/icons/cedif.png";
        break;
        
    case "SER011":
        rutaIcon="http://64.64.14.79/~gmap/icons/IA.png";
        break;
    
    case "SER012":
        rutaIcon="http://64.64.14.79/~gmap/icons/CARPAM.png";
        break;
        
    case "SER013":
        rutaIcon="http://64.64.14.79/~gmap/icons/CAN.png";
        break;
    
    case "SER014":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;
        
    case "SER015":
        rutaIcon="http://64.64.14.79/~gmap/icons/EC.png";
        break;
    
    case "SER016":
        rutaIcon="http://64.64.14.79/~gmap/icons/UIT.png";
        break;
        
    case "SER017":
        rutaIcon="http://64.64.14.79/~gmap/icons/UA.png";
        break;
    
    case "SER018":
        rutaIcon="http://64.64.14.79/~gmap/icons/RNPD.png";
        break;
        
    case "SER019":
        rutaIcon="http://64.64.14.79/~gmap/icons/JUGUEMOS.png";
        break;
    
    case "SER020":
        rutaIcon="http://64.64.14.79/~gmap/icons/DEMUNA.png";
        break;
        
    case "SER021":
        rutaIcon="http://64.64.14.79/~gmap/icons/CIAM.png"; 
        break;
    
    case "SER022":
        rutaIcon="http://64.64.14.79/~gmap/icons/CARPAM.png";
        break;
        
    case "SER023":
        rutaIcon="http://64.64.14.79/~gmap/icons/SBP.png";
        break;    
        
    default:
       rutaIcon="http://64.64.14.79/~gmap/icons/yellow-dot.png";
   }
   
   return rutaIcon;
}

function clearMarkers() {
  for (var i = 0; i < markers.length; i++) {
    markers[i][0].setMap(null);
  }  
  markers = [];
}

function offMarkerServ(codServ) {
  for (var i = 0; i < markers.length; i++) {
    if(markers[i][1]===codServ){
        markers[i][0].setMap(null);
    }    
  }
}

function onMarkerServ(codServ) {
  for (var i = 0; i < markers.length; i++) {    
    if(markers[i][1]===codServ){
        markers[i][0].setMap(map);
    }    
  }
}

function reporteNacional(){
        
    var param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC" ;
    var h=new Date();	
    var jqxhr22=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
        
        var distinct=[];
          
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
                 
        $.each(distinct, function(i, servicio) {    
            
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Inversion</th>', '</tr>','</thead>' ];             
             var sumTHistoria=0;
             
             var dataTblDevengados=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>PIM</th>', '<th>Ejecutado</th>', '</tr>','</thead>' ];
             var sumDevengados=0;
             var sumPIM=0;
             
             var dataPie=[];
             
             var categorias=[];             
             var dataPIM=[];
             var dataDev=[];
             
            var labelTab="";
            var nomServicio="";
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO, datos[x].NUM_BEN_EJEC1]);
                     
                     dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[i].ANO+'</td>');
                     dataTblHistorico.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[i].NUM_BEN_EJEC1;
                                  
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO);
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[i].ANO+'</td>');
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_PIM+'</td>');                                 
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[i].NUM_INV_PIM;
                     sumDevengados+=datos[i].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra y tabla Historico
             generaBarra_2(servicio,labelTab,"Peru :Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataPie,350);
             
              dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+sumTHistoria+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
             
             generaTabla(servicio,labelTab,"Peru : Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataTblHistorico); 
             
             
             //Generar Barra Inversion
                          
    
              generaBarraDbl(servicio,labelTab,"Peru :Ejecuci\xf3n Presupuestal en  "+capitalizarTexto(nomServicio),dataPie,350,categorias,dataPIM,dataDev);
              
              dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+sumPIM+'</td>');              
              dataTblDevengados.push('<td>'+sumDevengados+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
              generaTabla(servicio,labelTab,"Peru : Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados); 
              
        });
                            
    });  
      
            
    //-----
    
    var txtSelect=$("#ddlDepartamnto option:selected").html();    
    
    lib.clear();    
    
    var param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC   from GEO_REPORTE_SERVICIOS R  INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV WHERE R.COD_SERV IN ("+servShow+")  GROUP BY S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA ORDER BY R.COD_SERV,R.DEPART_CA" ;		                       
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
          
        var contServ=0;
        $.each(distinct, function(i, servicio) {              
                                                        
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Departamento</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Departamento</th>', '<th>0 a 5 anos</th>','<th>6 a 11 anos</th>','<th>12 a 17 anos</th>','<th>18 a 25 anos</th>','<th>26 a 35 anos</th>','<th>36 a 45 anos</th>','<th>46 a 59 anos</th>','<th>60 a mas </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Departamento</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Mental</th>', '</tr>','</thead>' ];
               
              var titulo="";
              var nomTab="";
              var nomServicio="";
              
              data.push('<tbody>');  
                          
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].DEPART_CA+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_H+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_M+'</td>');
                  // data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].DEPART_CA+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD1+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD2+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD3+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD4+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD5+'</td>');
	           dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD6+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD7+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD8+'</td>');
                   dataGrupoEdad.push('</tr>'); 

                   sumGr1+=datos[i].NUM_GRUP_EDAD1;
		   sumGr2+=datos[i].NUM_GRUP_EDAD2;
		   sumGr3+=datos[i].NUM_GRUP_EDAD3;
		   sumGr4+=datos[i].NUM_GRUP_EDAD4;
		   sumGr5+=datos[i].NUM_GRUP_EDAD5;
		   sumGr6+=datos[i].NUM_GRUP_EDAD6;
		   sumGr7+=datos[i].NUM_GRUP_EDAD7;
		   sumGr8+=datos[i].NUM_GRUP_EDAD8;
                                      
                   //***Por tipo de Violencia***************************
                   dataTipoViole.push('<tr>');
                   dataTipoViole.push('<td>'+datos[i].DEPART_CA+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC3+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC4+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC5+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+sumT+'</td>');
              data.push('<td>'+sumH+'</td>');
              data.push('<td>'+sumM+'</td>');
              //data.push('<td>'+sumE.toFixed(2)+'</td>');
              data.push('</tr>');                
              data.push('</tbody>');                            
              
              
              porcHombres=parseFloat(((sumH*100)/sumT).toFixed(2));
              porcMujeres=parseFloat(((sumM*100)/sumT).toFixed(2));
              
              //alert(porcHombres);
              //alert(porcMujeres);

              //porcHombres=0;
              //porcMujeres=0;
            
              dataPie=[
                   ['Hombres '+ porcHombres + "%",   sumH],
                   ['Mujeres '+ porcMujeres + "%",   sumM]                
               ];
               
               
               if (sumT>1){                  
                  var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",dataPie);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+sumGr1+'</td>');
              dataGrupoEdad.push('<td>'+sumGr2+'</td>');
              dataGrupoEdad.push('<td>'+sumGr3+'</td>');
              dataGrupoEdad.push('<td>'+sumGr4+'</td>');
              dataGrupoEdad.push('<td>'+sumGr5+'</td>');
              dataGrupoEdad.push('<td>'+sumGr6+'</td>');
              dataGrupoEdad.push('<td>'+sumGr7+'</td>');
              dataGrupoEdad.push('<td>'+sumGr8+'</td>');
              dataGrupoEdad.push('</tr>');  
              dataGrupoEdad.push('</tbody>');
              
              
              sumTGr=sumGr1+sumGr2+sumGr3+sumGr4+sumGr5+sumGr6+sumGr7+sumGr8;
              porcGr1=parseFloat(((sumGr1*100)/sumTGr).toFixed(2));
              porcGr2=parseFloat(((sumGr2*100)/sumTGr).toFixed(2));
	      porcGr3=parseFloat(((sumGr3*100)/sumTGr).toFixed(2));
	      porcGr4=parseFloat(((sumGr4*100)/sumTGr).toFixed(2));
	      porcGr5=parseFloat(((sumGr5*100)/sumTGr).toFixed(2));
	      porcGr6=parseFloat(((sumGr6*100)/sumTGr).toFixed(2));
	      porcGr7=parseFloat(((sumGr7*100)/sumTGr).toFixed(2));
	      porcGr8=parseFloat(((sumGr8*100)/sumTGr).toFixed(2));
              

              dataBar= [
                ['0 a 5 ', sumGr1],
                ['6 a 11', sumGr2],
                ['12 a 17',sumGr3],
                ['18 a 25 ', sumGr4],
                ['26 a 35', sumGr5],
                ['36 a 45', sumGr6],
                ['46 a 59', sumGr7],
                ['60 a mas', sumGr8]
              ]; 
              
              
              if (sumTGr>1){
                  var titulox="";
                  if (nomTab=="SER001"){
                      titulox=" por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaBarra(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataBar,300);              
                  generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataGrupoEdad);
              }
             
              
              
              
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+sumPsicol+'</td>');
              dataTipoViole.push('<td>'+sumFisica+'</td>');
              dataTipoViole.push('<td>'+sumMental+'</td>');              
              dataTipoViole.push('</tr>');                
              dataTipoViole.push('</tbody>');                            
              
              sumTipViol=sumPsicol+sumFisica+sumMental;
              porcPsicol=parseFloat(((sumPsicol*100)/sumTipViol).toFixed(2));
              porcFisica=parseFloat(((sumFisica*100)/sumTipViol).toFixed(2));
              porcMental=parseFloat(((sumMental*100)/sumTipViol).toFixed(2));  
              
              //alert(sumTipViol);
              
              dataPieViol=[
                   ['Psicol\xf3gica  '+ porcPsicol + "%",   sumPsicol],
                   ['F\xEDsica  '+ porcFisica + "%",   sumFisica],
                   ['Mental  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                   
                   //generaPie(contServ+"tab-"+titulo,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual en los CEM, por tipo de violencia",dataPieViol);                                          
                   //generaTabla(contServ+"tab-"+titulo,titulo,titulo+" - Por Tipo de Violencia",dataTipoViole);
                   if (nomTab==="SER001"){
                      generaPie(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataPieViol);                                          
                      generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataTipoViole);
                   } 
               }
               
               
               contServ+=1;  
               
               
        });
        
                           
                                          
    });
        
     
    
    
    //++++++++++++++++OLD+++++++++++++++
    
    
    
}

function reporteDepartamental(ubigeo){
    
    var txtSelect=$("#ddlDepartamnto option:selected").html();  
    lib.clear();
       
    var param="";
    if (flgVraem){       
       param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where  R.UBIGEO IN ("+dstVraem+") group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC " ;
    }else{
       param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") AND SUBSTR(R.UBIGEO,0,2)="+ubigeo+" group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC " ;
    }
    
         
    var h=new Date();	
    
    var jqxhr22=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
        
        var distinct=[];
          
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
                 
        $.each(distinct, function(i, servicio) { 
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Inversion</th>', '</tr>','</thead>' ];
             var sumTHistoria=0;
             
             
             var dataTblDevengados=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>PIM</th>', '<th>Ejecutado</th>', '</tr>','</thead>' ];
             var sumDevengados=0;
             var sumPIM=0;
             
             var dataPie=[];
             
             var categorias=[];             
             var dataPIM=[];
             var dataDev=[];
             
            var labelTab="";
            var nomServicio="";
             
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO, datos[x].NUM_BEN_EJEC1]);
                     dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[i].ANO+'</td>');
                     dataTblHistorico.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[i].NUM_BEN_EJEC1;
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO);
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[i].ANO+'</td>');
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_PIM+'</td>');                                 
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[i].NUM_INV_PIM;
                     sumDevengados+=datos[i].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataPie,350);
             
              dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+sumTHistoria+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
             
             generaTabla(servicio,labelTab,txtSelect+":Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataTblHistorico); 
            
             
             //Generar hostorico mim devngas
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataPie,350,categorias,dataPIM,dataDev);
             
              dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+sumPIM+'</td>');              
              dataTblDevengados.push('<td>'+sumDevengados+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
             generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados); 
              
        });
                            
    });  
      
            
    //-----
    
      
    
    
    var param="" ;		          
    if (flgVraem){
       param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where UBIGEO IN ("+dstVraem+") AND R.COD_SERV IN (select SM.COD_SERV from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DIST IN("+dstVraem+")) GROUP BY S.NOM_SERV, R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
    }else{
       param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") AND SUBSTR(R.UBIGEO,0,2)="+ubigeo+" AND R.COD_SERV IN (select SM.COD_SERV from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DEPAR="+ubigeo+") GROUP BY S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
    }
        
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
          
        var contServ=0;
        $.each(distinct, function(i, servicio) {              
                                                        
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Provincia</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Provincia</th>', '<th>0 a 5 anos</th>','<th>6 a 11 anos</th>','<th>12 a 17 anos</th>','<th>18 a 25 anos</th>','<th>26 a 35 anos</th>','<th>36 a 45 anos</th>','<th>46 a 59 anos</th>','<th>60 a mas </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Provincia</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Mental</th>', '</tr>','</thead>' ];
               
              var titulo="";
              var nomTab="";
               var nomServicio="";
              
              data.push('<tbody>');  
                          
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   
                   nomServicio=datos[i].NOM_SERV;

                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].PROVIN_CA+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_H+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_M+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].PROVIN_CA+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD1+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD2+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD3+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD4+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD5+'</td>');
	           dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD6+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD7+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD8+'</td>');
                   dataGrupoEdad.push('</tr>'); 

                   sumGr1+=datos[i].NUM_GRUP_EDAD1;
		   sumGr2+=datos[i].NUM_GRUP_EDAD2;
		   sumGr3+=datos[i].NUM_GRUP_EDAD3;
		   sumGr4+=datos[i].NUM_GRUP_EDAD4;
		   sumGr5+=datos[i].NUM_GRUP_EDAD5;
		   sumGr6+=datos[i].NUM_GRUP_EDAD6;
		   sumGr7+=datos[i].NUM_GRUP_EDAD7;
		   sumGr8+=datos[i].NUM_GRUP_EDAD8;
                                      
                   //***Por tipo de Violencia***************************
                   dataTipoViole.push('<tr>');
                   dataTipoViole.push('<td>'+datos[i].PROVIN_CA+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC3+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC4+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC5+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+sumT+'</td>');
              data.push('<td>'+sumH+'</td>');
              data.push('<td>'+sumM+'</td>');
              //data.push('<td>'+sumE.toFixed(2)+'</td>');
              data.push('</tr>');                
              data.push('</tbody>');                            
              
              
              porcHombres=parseFloat(((sumH*100)/sumT).toFixed(2));
              porcMujeres=parseFloat(((sumM*100)/sumT).toFixed(2));
              
              //alert(porcHombres);
              //alert(porcMujeres);

              //porcHombres=0;
              //porcMujeres=0;
            
              dataPie=[
                   ['Hombres '+ porcHombres + "%",   sumH],
                   ['Mujeres '+ porcMujeres + "%",   sumM]                
               ];
               
               
               if (sumT>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",dataPie);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+sumGr1+'</td>');
              dataGrupoEdad.push('<td>'+sumGr2+'</td>');
              dataGrupoEdad.push('<td>'+sumGr3+'</td>');
              dataGrupoEdad.push('<td>'+sumGr4+'</td>');
              dataGrupoEdad.push('<td>'+sumGr5+'</td>');
              dataGrupoEdad.push('<td>'+sumGr6+'</td>');
              dataGrupoEdad.push('<td>'+sumGr7+'</td>');
              dataGrupoEdad.push('<td>'+sumGr8+'</td>');
              dataGrupoEdad.push('</tr>');   
              dataGrupoEdad.push('</tbody>');
              
              sumTGr=sumGr1+sumGr2+sumGr3+sumGr4+sumGr5+sumGr6+sumGr7+sumGr8;
              porcGr1=parseFloat(((sumGr1*100)/sumTGr).toFixed(2));
              porcGr2=parseFloat(((sumGr2*100)/sumTGr).toFixed(2));
	      porcGr3=parseFloat(((sumGr3*100)/sumTGr).toFixed(2));
	      porcGr4=parseFloat(((sumGr4*100)/sumTGr).toFixed(2));
	      porcGr5=parseFloat(((sumGr5*100)/sumTGr).toFixed(2));
	      porcGr6=parseFloat(((sumGr6*100)/sumTGr).toFixed(2));
	      porcGr7=parseFloat(((sumGr7*100)/sumTGr).toFixed(2));
	      porcGr8=parseFloat(((sumGr8*100)/sumTGr).toFixed(2));
              

              dataBar= [
               ['0 a 5 ', sumGr1],
                ['6 a 11', sumGr2],
                ['12 a 17',sumGr3],
                ['18 a 25 ', sumGr4],
                ['26 a 35', sumGr5],
                ['36 a 45', sumGr6],
                ['46 a 59', sumGr7],
                ['60 a mas', sumGr8]
              ]; 
                            
              if (sumTGr>1){
                  var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaBarra(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+" , por grupos de edad",dataBar,300);              
                  generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+" , por grupos de edad",dataGrupoEdad);
              }
                                                       
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+sumPsicol+'</td>');
              dataTipoViole.push('<td>'+sumFisica+'</td>');
              dataTipoViole.push('<td>'+sumMental+'</td>');              
              dataTipoViole.push('</tr>');                
              dataTipoViole.push('</tbody>');                            
              
              sumTipViol=sumPsicol+sumFisica+sumMental;
              porcPsicol=parseFloat(((sumPsicol*100)/sumTipViol).toFixed(2));
              porcFisica=parseFloat(((sumFisica*100)/sumTipViol).toFixed(2));
              porcMental=parseFloat(((sumMental*100)/sumTipViol).toFixed(2));  
              
              //alert(sumTipViol);
              
              dataPieViol=[
                   ['Psicol\xf3gica  '+ porcPsicol + "%",   sumPsicol],
                   ['F\xEDsica  '+ porcFisica + "%",   sumFisica],
                   ['Mental  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                   if (nomTab==="SER001"){
                      generaPie(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual , por tipo de violencia",dataPieViol);                                          
                      generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual , por tipo de violencia",dataTipoViole);                       
                   }
               }
               
             contServ+=1;  
               
               
        });
        
                           
                                          
    });
        
     
}

function reporteProvincial(ubigeo){
    var txtSelect=$("#ddlProvincia option:selected").html(); 

    lib.clear();
    
    var param="";
    if (flgVraem){
        param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where SUBSTR(R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+") group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC " ;
    }else{
        param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") AND  SUBSTR(R.UBIGEO,0,4)="+ubigeo+" group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC " ;
    }
    
    
    var h=new Date();	
    
    var jqxhr22=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
        
        var distinct=[];
          
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
                 
        $.each(distinct, function(i, servicio) { 
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Inversion</th>', '</tr>','</thead>' ];
             var sumTHistoria=0;
             
             var dataTblDevengados=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>PIM</th>', '<th>Ejecutado</th>', '</tr>','</thead>' ];
             var sumDevengados=0;
             var sumPIM=0;
             
             var dataPie=[];
             
             var categorias=[];             
             var dataPIM=[];
             var dataDev=[];
             
            var labelTab="";
            var nomServicio="";
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO, datos[x].NUM_BEN_EJEC1]);
                      dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[i].ANO+'</td>');
                     dataTblHistorico.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[i].NUM_BEN_EJEC1;
                     
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO);
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[i].ANO+'</td>');
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_PIM+'</td>');                                 
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[i].NUM_INV_PIM;
                     sumDevengados+=datos[i].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataPie,350);
                      dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+sumTHistoria+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
              
               generaTabla(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataTblHistorico); 
              
              //barra pim deveng
    
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataPie,350,categorias,dataPIM,dataDev);
              
              dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+sumPIM+'</td>');              
              dataTblDevengados.push('<td>'+sumDevengados+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
              generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados); 
        });
                            
    });  
      
            
    //-----
    
      
    
    
    
    
    var param="";
    if (flgVraem){
       param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where SUBSTR (R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+") AND R.COD_SERV IN (select SM.COD_SERV from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and COD_DIST IN ("+dstVraem+") and   COD_PROV="+ubigeo+") GROUP BY S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA ORDER BY R.COD_SERV,R.DISTR_CA"; 
    }else{
       param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV ("+servShow+") AND  SUBSTR(R.UBIGEO,0,4)="+ubigeo+" AND R.COD_SERV IN (select SM.COD_SERV from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_PROV="+ubigeo+") GROUP BY S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA ORDER BY R.COD_SERV,R.DISTR_CA"; 
    }
    
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);                           
                   }
        });
                        
        $.each(distinct, function(i, servicio) {              
              
              //alert(servicio);  
              
              
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Distrito</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Distrito</th>', '<th>0 a 5 anos</th>','<th>6 a 11 anos</th>','<th>12 a 17 anos</th>','<th>18 a 25 anos</th>','<th>26 a 35 anos</th>','<th>36 a 45 anos</th>','<th>46 a 59 anos</th>','<th>60 a mas </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Distrito</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Mental</th>', '</tr>','</thead>' ];
               
              var titulo="";
              var nomTab="";
               var nomServicio="";
              
              data.push('<tbody>');  
            
              
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].DISTR_CA+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_H+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_M+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD1+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD2+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD3+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD4+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD5+'</td>');
	           dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD6+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD7+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD8+'</td>');
                   dataGrupoEdad.push('</tr>'); 

                   sumGr1+=datos[i].NUM_GRUP_EDAD1;
		   sumGr2+=datos[i].NUM_GRUP_EDAD2;
		   sumGr3+=datos[i].NUM_GRUP_EDAD3;
		   sumGr4+=datos[i].NUM_GRUP_EDAD4;
		   sumGr5+=datos[i].NUM_GRUP_EDAD5;
		   sumGr6+=datos[i].NUM_GRUP_EDAD6;
		   sumGr7+=datos[i].NUM_GRUP_EDAD7;
		   sumGr8+=datos[i].NUM_GRUP_EDAD8;
                                      
                   //***Por tipo de Violencia***************************
                   dataTipoViole.push('<tr>');
                   dataTipoViole.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC3+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC4+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC5+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+sumT+'</td>');
              data.push('<td>'+sumH+'</td>');
              data.push('<td>'+sumM+'</td>');
              //data.push('<td>'+sumE.toFixed(2)+'</td>');
              data.push('</tr>');                
              data.push('</tbody>');                            
              
              
              porcHombres=parseFloat(((sumH*100)/sumT).toFixed(2));
              porcMujeres=parseFloat(((sumM*100)/sumT).toFixed(2));
              
              //alert(porcHombres);
              //alert(porcMujeres);

              //porcHombres=0;
              //porcMujeres=0;
            
              dataPie=[
                   ['Hombres '+ porcHombres + "%",   sumH],
                   ['Mujeres '+ porcMujeres + "%",   sumM]                
               ];
               
               
               if (sumT>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }

                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",dataPie);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
                 dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+sumGr1+'</td>');
              dataGrupoEdad.push('<td>'+sumGr2+'</td>');
              dataGrupoEdad.push('<td>'+sumGr3+'</td>');
              dataGrupoEdad.push('<td>'+sumGr4+'</td>');
              dataGrupoEdad.push('<td>'+sumGr5+'</td>');
              dataGrupoEdad.push('<td>'+sumGr6+'</td>');
              dataGrupoEdad.push('<td>'+sumGr7+'</td>');
              dataGrupoEdad.push('<td>'+sumGr8+'</td>');
              dataGrupoEdad.push('</tr>');  
              dataGrupoEdad.push('</tbody>');
              
              sumTGr=sumGr1+sumGr2+sumGr3+sumGr4+sumGr5+sumGr6+sumGr7+sumGr8;
              porcGr1=parseFloat(((sumGr1*100)/sumTGr).toFixed(2));
              porcGr2=parseFloat(((sumGr2*100)/sumTGr).toFixed(2));
	      porcGr3=parseFloat(((sumGr3*100)/sumTGr).toFixed(2));
	      porcGr4=parseFloat(((sumGr4*100)/sumTGr).toFixed(2));
	      porcGr5=parseFloat(((sumGr5*100)/sumTGr).toFixed(2));
	      porcGr6=parseFloat(((sumGr6*100)/sumTGr).toFixed(2));
	      porcGr7=parseFloat(((sumGr7*100)/sumTGr).toFixed(2));
	      porcGr8=parseFloat(((sumGr8*100)/sumTGr).toFixed(2));
              

              dataBar= [
                ['0 a 5 ', sumGr1],
                ['6 a 11', sumGr2],
                ['12 a 17',sumGr3],
                ['18 a 25 ', sumGr4],
                ['26 a 35', sumGr5],
                ['36 a 45', sumGr6],
                ['46 a 59', sumGr7],
                ['60 a mas', sumGr8]
              ]; 
              
              
              if (sumTGr>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaBarra(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataBar,300);              
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataGrupoEdad);
              }
             
              
              
              
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+sumPsicol+'</td>');
              dataTipoViole.push('<td>'+sumFisica+'</td>');
              dataTipoViole.push('<td>'+sumMental+'</td>');              
              dataTipoViole.push('</tr>');                
              dataTipoViole.push('</tbody>');                            
              
              sumTipViol=sumPsicol+sumFisica+sumMental;
              porcPsicol=parseFloat(((sumPsicol*100)/sumTipViol).toFixed(2));
              porcFisica=parseFloat(((sumFisica*100)/sumTipViol).toFixed(2));
              porcMental=parseFloat(((sumMental*100)/sumTipViol).toFixed(2));  
              
              //alert(sumTipViol);
              
              dataPieViol=[
                   ['Psicol\xf3gica  '+ porcPsicol + "%",   sumPsicol],
                   ['F\xEDsica  '+ porcFisica + "%",   sumFisica],
                   ['Mental  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                 if (nomTab==="SER001"){
                   generaPie(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataPieViol);                                          
                   generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataTipoViole);                     
                 }  
               }
               
               
        });
        
                           
                                          
    });
   
}

function reporteDistrital(ubigeo){
    
    var txtSelect=$("#ddlDistrito option:selected").html();
    lib.clear();   
    
    
    
    var param="q=select S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") AND SUBSTR(R.UBIGEO,0,6)="+ubigeo+" group by S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ORDER BY R.COD_SERV,R.ANO ASC " ;
    var h=new Date();	
    
    var jqxhr22=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
        
        var distinct=[];
          
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         //alert(codServ);
                         distinct.push(codServ);                           
                   }
        });
                 
        $.each(distinct, function(i, servicio) { 
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Inversion</th>', '</tr>','</thead>' ];
             var sumTHistoria=0;
             
             var dataTblDevengados=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>PIM</th>', '<th>Ejecutado</th>', '</tr>','</thead>' ];
             var sumDevengados=0;
             var sumPIM=0;
             
             var dataPie=[];
             
             var categorias=[];             
             var dataPIM=[];
             var dataDev=[];
             
             var labelTab="";
             var nomServicio="";
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO, datos[x].NUM_BEN_EJEC1]);
                     dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[i].ANO+'</td>');
                     dataTblHistorico.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[i].NUM_BEN_EJEC1;
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO);
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[i].ANO+'</td>');
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_PIM+'</td>');                                 
                     dataTblDevengados.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[i].NUM_INV_PIM;
                     sumDevengados+=datos[i].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataPie,350);
             dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+sumTHistoria+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
              
            generaTabla(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataTblHistorico); 
              
             //barra deveng
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataPie,350,categorias,dataPIM,dataDev);
             
             dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+sumPIM+'</td>');              
              dataTblDevengados.push('<td>'+sumDevengados+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
              generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados); 
              
        });
                            
    });  
      
            
    //-----
    
      
    
    
    var param="q=SELECT  S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV where R.COD_SERV IN ("+servShow+") AND  SUBSTR(R.UBIGEO,0,6)="+ubigeo+" AND R.COD_SERV IN (select SM.COD_SERV from  GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DIST="+ubigeo+") GROUP BY S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA ORDER BY R.COD_SERV,R.DISTR_CA";      
        
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);                           
                   }
        });
                        
        $.each(distinct, function(i, servicio) {              
              
              //alert(servicio);  
              
              
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Distrito</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Distrito</th>', '<th>0 a 5 anos</th>','<th>6 a 11 anos</th>','<th>12 a 17 anos</th>','<th>18 a 25 anos</th>','<th>26 a 35 anos</th>','<th>36 a 45 anos</th>','<th>46 a 59 anos</th>','<th>60 a mas </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Distrito</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Mental</th>', '</tr>','</thead>' ];
               
              var titulo="";  
              var nomTab="";
              var nomServicio="";
 
              data.push('<tbody>');  
            
              
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].DISTR_CA+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_H+'</td>');
                   data.push('<td>'+datos[i].NUM_BEN_EJEC1_M+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD1+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD2+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD3+'</td>');
                   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD4+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD5+'</td>');
	           dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD6+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD7+'</td>');
		   dataGrupoEdad.push('<td>'+datos[i].NUM_GRUP_EDAD8+'</td>');
                   dataGrupoEdad.push('</tr>'); 

                   sumGr1+=datos[i].NUM_GRUP_EDAD1;
		   sumGr2+=datos[i].NUM_GRUP_EDAD2;
		   sumGr3+=datos[i].NUM_GRUP_EDAD3;
		   sumGr4+=datos[i].NUM_GRUP_EDAD4;
		   sumGr5+=datos[i].NUM_GRUP_EDAD5;
		   sumGr6+=datos[i].NUM_GRUP_EDAD6;
		   sumGr7+=datos[i].NUM_GRUP_EDAD7;
		   sumGr8+=datos[i].NUM_GRUP_EDAD8;
                                      
                   //***Por tipo de Violencia***************************
                   dataTipoViole.push('<tr>');
                   dataTipoViole.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC3+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC4+'</td>');
                   dataTipoViole.push('<td>'+datos[i].NUM_BEN_EJEC5+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+sumT+'</td>');
              data.push('<td>'+sumH+'</td>');
              data.push('<td>'+sumM+'</td>');
              //data.push('<td>'+sumE.toFixed(2)+'</td>');
              data.push('</tr>');                
              data.push('</tbody>');                            
              
              
              porcHombres=parseFloat(((sumH*100)/sumT).toFixed(2));
              porcMujeres=parseFloat(((sumM*100)/sumT).toFixed(2));
              
              //alert(porcHombres);
              //alert(porcMujeres);

              //porcHombres=0;
              //porcMujeres=0;
            
              dataPie=[
                   ['Hombres '+ porcHombres + "%",   sumH],
                   ['Mujeres '+ porcMujeres + "%",   sumM]                
               ];
               
               
               if (sumT>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }

                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",dataPie);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+sumGr1+'</td>');
              dataGrupoEdad.push('<td>'+sumGr2+'</td>');
              dataGrupoEdad.push('<td>'+sumGr3+'</td>');
              dataGrupoEdad.push('<td>'+sumGr4+'</td>');
              dataGrupoEdad.push('<td>'+sumGr5+'</td>');
              dataGrupoEdad.push('<td>'+sumGr6+'</td>');
              dataGrupoEdad.push('<td>'+sumGr7+'</td>');
              dataGrupoEdad.push('<td>'+sumGr8+'</td>');
              dataGrupoEdad.push('</tr>');  
              dataGrupoEdad.push('</tbody>');
              
              sumTGr=sumGr1+sumGr2+sumGr3+sumGr4+sumGr5+sumGr6+sumGr7+sumGr8;
              porcGr1=parseFloat(((sumGr1*100)/sumTGr).toFixed(2));
              porcGr2=parseFloat(((sumGr2*100)/sumTGr).toFixed(2));
	      porcGr3=parseFloat(((sumGr3*100)/sumTGr).toFixed(2));
	      porcGr4=parseFloat(((sumGr4*100)/sumTGr).toFixed(2));
	      porcGr5=parseFloat(((sumGr5*100)/sumTGr).toFixed(2));
	      porcGr6=parseFloat(((sumGr6*100)/sumTGr).toFixed(2));
	      porcGr7=parseFloat(((sumGr7*100)/sumTGr).toFixed(2));
	      porcGr8=parseFloat(((sumGr8*100)/sumTGr).toFixed(2));
              

              dataBar= [
              ['0 a 5 ', sumGr1],
                ['6 a 11', sumGr2],
                ['12 a 17',sumGr3],
                ['18 a 25 ', sumGr4],
                ['26 a 35', sumGr5],
                ['36 a 45', sumGr6],
                ['46 a 59', sumGr7],
                ['60 a mas', sumGr8]
              ]; 
              
              
              if (sumTGr>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }

                  generaBarra(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataBar,300);              
                  generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataGrupoEdad);
              }
             
              
              
              
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+sumPsicol+'</td>');
              dataTipoViole.push('<td>'+sumFisica+'</td>');
              dataTipoViole.push('<td>'+sumMental+'</td>');              
              dataTipoViole.push('</tr>');                
              dataTipoViole.push('</tbody>');                            
              
              sumTipViol=sumPsicol+sumFisica+sumMental;
              porcPsicol=parseFloat(((sumPsicol*100)/sumTipViol).toFixed(2));
              porcFisica=parseFloat(((sumFisica*100)/sumTipViol).toFixed(2));
              porcMental=parseFloat(((sumMental*100)/sumTipViol).toFixed(2));  
              
              //alert(sumTipViol);
              
              dataPieViol=[
                   ['Psicol\xf3gica  '+ porcPsicol + "%",   sumPsicol],
                   ['F\xEDsica  '+ porcFisica + "%",   sumFisica],
                   ['Mental  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                 if (nomTab==="SER001"){  
                     generaPie(nomTab,titulo,txtSelect+":Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataPieViol);                                          
                     generaTabla(nomTab,titulo,txtSelect+":Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataTipoViole);
                 }
               }
               
               
        });
        
                           
                                          
    }); 
}



function generaBarra(tabId,tabTitle,titulo, datos,alto){
    lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       
        chart: {
            type: 'column'
        },
         height:alto,
        title: {
            text: titulo,
        style: {

                 font: 'normal 14px "Helvetica Neue", sans-serif',
                  color : '#222222'
               }
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -90,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            },
            labels: {  
              formatter: function() {
               return Highcharts.numberFormat(this.value,0);
              }         
            }
            
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '<b>{point.y:,.0f}</b>'
           //pointFormat: '<b>{point.percentage:.0f} %</b>'
        },
        series: [{
            name: 'Population',
            data: datos ,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
     
    });
}





function generaBarra_2(tabId,tabTitle,titulo, datos,alto){
    lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       
        chart: {
            type: 'column'
        },
         height:alto,
        title: {
            text: titulo,
        style: {

                 font: 'normal 14px "Helvetica Neue", sans-serif',
                  color : '#222222'
               }
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -90,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            },
            labels: {  
            formatter: function() {
               return Highcharts.numberFormat(this.value,0);
            }         
          }
            
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '<b>{point.y:,.0f}</b>'
        },
        series: [{
            name: 'Population',
            data: datos ,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 0,
                y: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
     
    });
}



function generaBarraDbl(tabId,tabTitle,titulo, datos,alto,categorias,dataPIM,dataDev){
    
   
    lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       
       chart: {
            type: 'column'
        },
        title: {
            text: titulo,
        style: {

                 font: 'normal 14px "Helvetica Neue", sans-serif',
                  color : '#222222'
               }
        },
        subtitle: {
            text: titulo
        },
        xAxis: {
            categories: categorias,
             labels: {
                rotation: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Nuevos Soles'
            },
            labels: {  
            formatter: function() {
               return Highcharts.numberFormat(this.value,0);
            }         
          }
            
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:,.0f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'PIM',
            data: dataPIM,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 4,
                y: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }

        }, {
            name: 'Ejecutado',
            data: dataDev,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 4,
                y: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }

        }]
     
    });
}


function generaPie(tabId,tabTitle,titulo,data){
  //'tabId':'car','tabTitle':'Titulo de CAR',
  lib.graph({'tabId':tabId,'tabTitle':tabTitle,
   chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: titulo,
             style: {

                 font: 'normal 14px "Helvetica Neue", sans-serif',
                  color : '#222222'
               }
        },
        height:250,
        legend: {
           itemStyle: {
                fontSize: '10px'
             }
       },
        tooltip: {
            pointFormat: '<b>{point.y:,.0f}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '->',
            data: data
        }]
     }); 
  
}

function generaTabla(tabId,tabTitle,titulo,datos){  
    //'tabId':'car','tabTitle':'Titulo de CAR',
    lib.grid({'tabId':tabId,'tabTitle':tabTitle,'title':titulo, 'data': datos});
}

function clickCapas(codServ,evt){    
    if (evt.checked){      
       $('#'+codServ).show();  
       onMarkerServ(codServ);
    }else{
       $('#'+codServ).hide();
       offMarkerServ(codServ);
    }    
}

function zoomFull(){
   // alert('ahuuu');
    var latlon = new google.maps.LatLng(-8.559, -73.655);
    map.setCenter(latlon);
    map.setZoom(5);
}	

function zoomIn(){
   map.setZoom(map.getZoom()+1); 
}

function zoomOut(){
   map.setZoom(map.getZoom()-1);  
}

function printMap(){
   // window.print();
    var content = window.document.getElementById('gmap') // get you map details
    var newWindow = window.open(); // open a new window
    newWindow.document.write(content.innerHTML); // write the map into the new window
    newWindow.print();
}