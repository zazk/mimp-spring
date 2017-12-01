var dominioSite="localhost:8084";
var rutaCartografia='http://181.65.173.175:8080/mimp.gis/assetsvisor/carto/';

var map;
var limites;
var markers = [];
var texReservado=["la","del", "de", "como", "a", "y", "con", "los", "o", "su","atendida","en","-"];
var infowindow ;
var flgVraem=false;
var prvVraem="0302,0306,0504,0505,0809,0905,0907,1202,1201,1206";
var dstVraem="030604,030219,030202,030606,030208,030605,050508,050505,050407,050406,050408,050402,050502,050509,050504,050501,050510,050409,050410,050507,050503,050506,050401,080902,080907,080910,080909,090714,090509,090507,090503,090705,090510,090709,090701,090713,090718,090711,090702,090703,090704,090706,090710,090715,090717,090716,090707,120604,120606,120203,120608,120124,120135";
var servShow="'SER001','SER004','SER002','SER007','SER009','SER008','SER010','SER011','SER015','SER012','SER013','SER017','SER016','SER018','SER023','SER022','SER019'";
var queryRep="";
var distinctColsRep=[];


var repConf=[
    
    {
     codServ:'SER001',
     reportes:[             
          {
            tipo:'r2',
            titulo:' Poblaci\u00f3n Atendida por Casos de Violencia y Acciones Preventivas',            
            variables:['NUM_BEN_EJEC1','NUM_BEN_EJEC2'],
            alias:['Atendidas','Informadas']
          },      
          {
             tipo:'r2',
             titulo:' Ejecuci\u00f3n presupuestal por Casos de Violencia y Acciones Preventivas',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'], 
             alias:[' Inversi\u00f3n Ejecutada','PIM']
          },      
          {
              tipo:'r3',                  
             titulo:' Casos Atendidos por Sexo',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },      
          {
             
             tipo:'r4',                  
             titulo:' Casos Atendidos por Grupos de Edad',
             variables:['NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD7','NUM_GRUP_EDAD8'], 
             alias:['0-5','6-11','12-17','18-25','26-35','36-45','46-59','60-M\u00e1s'] 
          },      
          {
             tipo:'r3',                  
             titulo:' Casos Atendidos por Tipo de Violencia Familiar y/o Sexual',
             variables:['NUM_BEN_EJEC3','NUM_BEN_EJEC4','NUM_BEN_EJEC5'],
             alias:['Psicol\u00f3gica','F\u00edsica','Sexual'] 
          }      
     ]
 
    },
    
    {
      codServ:'SER004',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' Casos Atendidoos en el Servicio l\u00ednea 100',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendidas']
          },
          {
             tipo:'r2',                  
             titulo:' Ejecuci\u00F3n Presupuestal',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' Casos Atendidos en el Servicio de L\u00ednea 100 por Sexo',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' Casos Atendidos en el Programa de L\u00ednea 100 por Grupo de Edad',
             variables:['NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD7','NUM_GRUP_EDAD8'] ,
             alias:['0-5','6-11','12-17','18-25','26-35','36-45','46-59','60-M\u00e1s'] 
          },
          {
             tipo:'r3',                  
             titulo:' Casos Atendidos por Tipo de Violencia Familiar y/o Sexual',
             variables:['NUM_BEN_EJEC3','NUM_BEN_EJEC4','NUM_BEN_EJEC5','NUM_INDICADOR1'], 
             alias:['Psicol\u00f3gica','F\u00edsica','Sexual','Otras'] 
          }
      ]
    }    
    
    ,
    
    {
      codServ:'SER002',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' CASOS ATENDIDOS EN LOS CENTROS DE ATENCI\u00F3N INSTITUCIONAL FRENTE A LA VIOLENCIA FAMILIAR',
             variables:['NUM_BEN_EJEC1_H'],
             alias:['Atendidas']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL POR CASOS ATENDIDOS EN EL CENTRO DE ATENCI\u00F3N INSTITUCIONAL',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          
          {
             tipo:'r4',                  
             titulo:' CASOS ATENDIDOS EN EL CENTRO DE ATENCI\u00F3N INSTITUCIONAL POR GRUPO DE EDAD',
             variables:['NUM_GRUP_EDAD4_H','NUM_GRUP_EDAD5_H','NUM_GRUP_EDAD6_H','NUM_GRUP_EDAD7_H','NUM_GRUP_EDAD8_H'] ,
             alias:['18-25','26-35','36-45','46-59','60-M\u00e1s'] 
          }
      ]
    },
    
    {
      codServ:'SER007',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL DE NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL DE NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL DE NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO DE EDAD EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL DE NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES',
             variables:['NUM_GRUP_EDAD0','NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD8'] ,
             alias:['< 1','1-5','6-11','12-17','18-M\u00e1s','26-59','18-25','60-M\u00e1s'] 
          }
      ]
    }
    
    ,
    
    {
      codServ:'SER009',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS CON DISCAPACIDAD',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS CON DISCAPACIDAD',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS CON DISCAPACIDAD',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS CON DISCAPACIDAD',
             variables:['NUM_GRUP_EDAD0','NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD8'] ,
             alias:['< 1','1-5','6-11','12-17','18-M\u00e1s','26-59','18-25','60-M\u00e1s'] 
          }
      ]
    }
    
    ,
    
    {
      codServ:'SER008',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS ADULTAS MAYORES',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS ADULTAS MAYORES',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS ADULTAS MAYORES',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO DE EDAD EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS ADULTAS MAYORES',
             variables:['NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD8'] ,
             alias:['18-M\u00e1s','26-59','60-M\u00e1s'] 
          }
      ]
    }
    
    ,
    
    {
      codServ:'SER010',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE DESARROLLO INTEGRAL DE LA FAMILIA',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE DESARROLLO INTEGRAL DE LA FAMILIA',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE DESARROLLO INTEGRAL DE LA FAMILIA POR SEXO',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN EL CENTRO DE DESARROLLO INTEGRAL DE LA FAMILIA',
             variables:['NUM_GRUP_EDAD0','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD8'] ,
             alias:['< 1','1-11','12-17','18-25','26-59','18-25','60-M\u00e1s'] 
          }
      ]
    }
    
    
    ,
    
    {
      codServ:'SER011',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN INABIF EN ACCI\u00F3N',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN INABIF EN ACCI\u00F3N',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN INABIF EN ACCI\u00F3N',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN INABIF EN ACCI\u00F3N',
             variables:['NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD8'] ,
             alias:['0-11','12-17','18-25','26-59','18-25','60-M\u00e1s'] 
          }
      ]
    }
    
     
    ,
    
    {
      codServ:'SER015',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE REFERENCIA',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE REFERENCIA',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE REFERENCIA',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN EL CENTRO DE REFERENCIA',
             variables:['NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD6'] ,
             alias:['0-5','6-8','9-11','12-14','15-17','18 A\u00F1os'] 
          }
      ]
    }
    
      
    ,
    
    {
      codServ:'SER012',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONA ADULTA MAYOR',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONA ADULTA MAYOR',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONA ADULTA MAYOR',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONA ADULTA MAYOR',
             variables:['NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD7','NUM_GRUP_EDAD8'] ,
             alias:['50-59','60-70','71-80','81-M\u00e1s'] 
          }
      ]
    }
    
    
    ,
    
    {
      codServ:'SER013',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' POBLACI\u00F3N ATENDIDA EN HOSPEDER\u00EDA',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN HOSPEDER\u00EDA',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR SEXO EN HOSPEDER\u00EDA',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' POBLACI\u00F3N ATENDIDA POR GRUPO GRUPO DE EDAD EN HOSPEDER\u00EDA',
             variables:['NUM_GRUP_EDAD5','NUM_GRUP_EDAD6','NUM_GRUP_EDAD7','NUM_GRUP_EDAD8'] ,
             alias:['50-59','60-70','71-80','81-M\u00e1s'] 
          }
      ]
    }
    
    
    ,
    
    {
      codServ:'SER017',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ADOPTADOS SEG\u00FAN PROCEDENCIA DEL CENTRO ATENCI\u00F3N',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN ADOPCI\u00F3N',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ADOPTADOS POR SEXO',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ADOPTADOS POR GRUPO GRUPO DE EDAD',
             variables:['NUM_GRUP_EDAD0','NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3'] ,
             alias:['<1','1-5','6-12','17-37'] 
          }
      ]
    }
    
    
     
    ,
    
    {
      codServ:'SER016',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ATENDIDOS EN APERTURA DE INVESTIGACI\u00F3N TUTELAR',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN ADOPCI\u00F3N',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ATENDIDOS EN APERTURA DE INVESTIGACI\u00F3N TUTELAR POR SEXO',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' NI\u00F1AS, NI\u00F1OS Y ADOLESCENTES ATENDIDOS EN APERTURA DE INVESTIGACI\u00F3N TUTELAR POR GRUPO GRUPO DE EDAD',
             variables:['NUM_GRUP_EDAD0','NUM_GRUP_EDAD1','NUM_GRUP_EDAD2','NUM_GRUP_EDAD3'] ,
             alias:['<1','1-5','6-12','17-37'] 
          }
      ]
    }
              
    ,
    
    {
      codServ:'SER018',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' PERSONAS INSCRITAS EN EL REGISTRO DE PERSONAS CON DISCAPACIDAD',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          },
          {
             tipo:'r2',                  
             titulo:' EJECUCI\u00F3N PRESUPUESTAL EN ADOPCI\u00F3N',
             variables:['NUM_INV_EJEC','NUM_INV_PIM'],
             alias:[' Inversi\u00f3n Ejecutada','PIM'] 
          },
          {
            
             tipo:'r3',                  
             titulo:' PERSONAS INSCRITAS EN EL REGISTRO DE PERSONAS CON DISCAPACIDAD POR SEXO',
             variables:['NUM_BEN_EJEC1_H','NUM_BEN_EJEC1_M'],
             alias:['Hombres','Mujeres'] 
          },
          {
             tipo:'r4',                  
             titulo:' PERSONAS INSCRITAS EN EL REGISTRO DE PERSONAS CON DISCAPACIDAD POR GRUPO GRUPO DE EDAD',
             variables:['NUM_GRUP_EDAD2','NUM_GRUP_EDAD3','NUM_GRUP_EDAD4','NUM_GRUP_EDAD5','NUM_GRUP_EDAD8'] ,
             alias:['0-11','12-17','18-25','26-59','60-M\u00e1s'] 
          }
      ]
    }
    
    
              
    ,
    
    {
      codServ:'SER023',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' PERSONAS ATENDIDAS EN LA SOCIEDAD DE BENEFICENCIA P\u00FABLICA',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          }
      ]
    }
    
     ,
    
    {
      codServ:'SER022',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' PERSONAS ATENDIDAS EN EL CENTRO DE ATENCI\u00F3N RESIDENCIAL PARA PERSONAS ADULTAS MAYORES',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          }
      ]
    }
    
     ,
    
    {
      codServ:'SER019',
      reportes:[
          {
             tipo:'r2',                  
             titulo:' NI\u00F1AS Y  NI\u00F1OS ATENDIDOS EN EL SERVICIO JUGUEMOS',
             variables:['NUM_BEN_EJEC1'],
             alias:['Atendida-Total']
          }
      ]
    }
    
];




var lib = {};

lib.clear = function(){
     $("#myTab").empty().next(".tab-content").eq(0).empty();
}


lib.toggle_mp = function(el){
  $(el).addClass("active").siblings().removeClass("active");
  $(".cont-celds").eq(0).attr("id",$(el).data("class"));
  setTimeout(function(){ 
    $(window).trigger("resize");
    google.maps.event.trigger(map, 'resize');
  },50);  
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


lib.createTab = function(id, name, type, callback){
    var $tb = $("#tab-content-"+id), cl;
     if($tb.length == 0){

     switch(type){
      case "PROMUEVE":
        cl = "blue";
      break;
      case "BRINDA":
      cl = "red";
      break;
      case "SUPERVISA":
      cl = "purple";
      break;
      default:
      cl = "";
      break;
    }
    
    var $lk = $('<li><a id="tab-'+id+'" href="#tab-content-'+id+'">'+ name +'<i class="led-act '+cl+'"></i></a></li>');
    $("#myTab").append($lk);
    $tb = $('<div id="tab-content-'+ id +'" class="tab-pane"></div>');
    $(".cn-data .tab-content").eq(0).append($tb);
 
    $lk.find("a").on("click", function(e){
      e.preventDefault();
      $(this).tab('show');

      setTimeout(function(){  $(window).trigger("resize"); },10); 
    });
    
     $("#myTab").find("a").eq(0).trigger("click");
    
  }
  
  if(typeof callback == "function") callback($tb);

}


lib.graph = function(jsn){
   
   
    Highcharts.setOptions({ colors: lib.shuffle([ "#ff5050", "#5b9bd5", "#7f7f7f"])});
   //Highcharts.setOptions({ colors: lib.shuffle([ "#4572a7", "#aa4643", "#89a54e","#80699b","#3d96ae","#db843d","#92a8cd","#a47d7c","#b5ca92"])});
   if(!$(".cn-data").eq(0).is(":visible")) $(".cn-data").eq(0).show();

      lib.createTab(jsn.tabId, jsn.tabTitle, jsn.typeserv, function($tb){
         var $dv_gr = $('<div class="new_graphic" style="height:'+ (jsn.height || 380)+'px; margin: 0 auto 15px;"></div>');
        $tb.append($dv_gr);

        $dv_gr.highcharts(jsn, function(o){ $(o.container).children("svg").children("text:last-child").hide();});
      });
}



lib.grid = function(jsn){
        
     if(!$(".cn-data").eq(0).is(":visible")) $(".cn-data").eq(0).show();

     lib.createTab(jsn.tabId, jsn.tabTitle, jsn.typeserv, function($tb){
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
    
        
        $.each(repConf, function(n, item) {  
           
            var rpts=item.reportes;             
            $.each(rpts, function(i, itemRpt) {  
                  var fieldsRep= itemRpt.variables;   
                  $.each(fieldsRep, function(j, itemFields) {  
                           var field=itemFields;                           
                           if (distinctColsRep.indexOf(field, 0)=== -1 ){                         
                              distinctColsRep.push(field);                           
                            }
                  });
            });
        });
                
       
        
    
      //--------------------------------------------------------------------------------------
      
      
      //alert('kasjdhskjd');
    
      $(".jtooltip").tooltip();
    
      lib.confhome();
      
      lib.clear();
     
      reporteNacional();
      
      

      cargaMapa();
      
      
      
      
       $('#btnHomeApp').on('click', function() {
           //$("#ddlDepartamnto option[0]").attr("selected",true);
           $('#ddlDepartamnto> option[value="00"]').attr('selected', 'selected');
             
           $("#ddlProvincia")
               .find('option')
               .remove()
               .end()               
              ;
              
              $("#ddlDistrito")
               .find('option')
               .remove()
               .end()                
              ;
              clearMarkers();
              clearLeyenda();
              clearToc();
              limites.setMap(null);  
              reporteNacional();
              zoomFull();
      });
            
      $('#btnLogin').on('click', function() {
          goLogin();
      });
    
      $('#ddlDepartamnto').on('change', function() {
          
          if (this.value==="00"){
              $("#ddlProvincia")
               .find('option')
               .remove()
               .end()               
              ;
              
              $("#ddlDistrito")
               .find('option')
               .remove()
               .end()                
              ;
              clearMarkers();
              clearLeyenda();
              clearToc();
              limites.setMap(null);  
              reporteNacional();
              zoomLimitePolitico(this.value);
              //zoomFull();
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
                                
    //zoomLimitePolitico("14");
                                                                
    $('#alistaServBrind').trigger('click');
    $('#alistaServProm').trigger('click');
    $('#alistaServSup').trigger('click');
    
     
    google.maps.event.addListenerOnce(map, 'idle', function(){
       //loaded fully  
       //alert('cargo todo');
       //zoomLimitePolitico("vraem");
    });
    
    //zoomLimitePolitico(14);
    
}

function zoomLimitePolitico(ubigeo){
    //alert(ubigeo);
    limites.setMap(null);    
    //limites.setMap(null);
    //ubigeo='peru';
    
    //var ruta='http://64.64.14.79/~gmap/limites/'+ubigeo+ ".kml";    
    
    var ruta = rutaCartografia+"/limites/"+ubigeo+ ".kml";  
    //alert(ruta);
    limites  = new google.maps.KmlLayer(ruta,{
	  	preserveViewport: false
    });
    limites.setMap(map);
    //limites.setMap(null);
        
    google.maps.event.addListenerOnce(limites, 'status_changed', function(){       
       //alert(limites.getStatus());       
    });
    
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
      case "BRINDA":
        $("#listaServBrind").append("<li>" + texto_ + "</li>");
        break;
      case "PROMUEVE":
        $("#listaServProm").append("<li>" + texto_ + "</li>");
        break;
      case "SUPERVISA":
        $("#listaServSup").append("<li>" + texto_ + "</li>");
        break;
     }
    
}

function addLeyenda(rIcono, texto, codServ,nomServ){
    
   // alert('adleyebnda'); 
    
   cnt="<div id='i"+codServ+"' class='item-static'><i class='cm-icon'><img class='jtooltip' data-toggle='tooltip' data-placement='top' title='' data-original-title='"+capitalizarTexto(nomServ)+"' src='"+rIcono+"' ></i> "+texto+"</div>";
   $("#divLeyenda").append(cnt); 
   $(".jtooltip").tooltip();
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

function creaInfoWindows(marker, nombCA, dirCA,telfCA, ref ,codCA){
    //alert(codCA);
    google.maps.event.addListener(marker, 'click', (function(marker, nombCA, dirCA,telfCA,codCA ) {
	                        return function() {
                                  //cont="<div><strong>Nombre CAcc :</strong> "+nombCA+"<hr><br> <strong>Direcci&oacute;n :</strong> "+dirCA+"<hr> <BR> <strong>Telefono:</strong> " + telfCA +   "<hr><br> <strong> Fachada :</strong> <br> <img src='http://64.64.14.79/~gmap/icons/fachada.png'></div>";
                                  var rutaImg="http://"+dominioSite+"/mimp.gis/files/centrosatencion/"+codCA.toUpperCase()+".jpg";
                                  cont="<table><colgroup><col width='130px;'></colgroup><tr><td style='vertical-align:top'><strong>Centro de Atenci&oacute;n :</strong> </td> <td > "+ capitalizarTexto(nombCA)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Direcci&oacute;n :</strong></td> <td> "+ capitalizarTexto(dirCA)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Referencia :</strong></td> <td> "+ capitalizarTexto(ref)+"</td></tr>";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Telefono:</strong> </td> <td> " + capitalizarTexto(telfCA) +   "</td></tr> ";
                                  cont+="<tr> <td style='vertical-align:top'> <strong>Fachada </strong> </td> <td></td> </tr>";
                                  cont+="<tr> <td colspan='2'> <img width='280px' onerror='errLoadImg(this);' src='"+rutaImg+"'></td></tr></table>";
	                          infowindow.setContent(cont);
	                          infowindow.open(map, marker);
	                        };
	                  })(marker,nombCA, dirCA,telfCA,codCA));
}

function errLoadImg(obj){
    //alert(obj.src);
    obj.src="http://"+dominioSite+"/mimp.gis/files/centrosatencion/none.jpg";
}

function getCentrosDepa(ubigeo){              
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param=""  ;		          
          
          if (flgVraem){
            param="q=select CA.COD_CA, CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV,SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from SYS.GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_PROV in (0302,0306,0504,0505,0809,0905,0907,1202,1201,1206)";   
          }else{
            param="q=select CA.COD_CA, CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV,SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from SYS.GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DEPAR=" + ubigeo  ;		             
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
                   codCA=datos[i].COD_CA; 
                   
                   rutIcono=DefineIconServ(codServ);      
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                          distinct.push(codServ);                                                   
                          var latlng = new google.maps.LatLng(lon_,lat_);                   
                          var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: capitalizarTexto(titulo),
				     	      icon: rutIcono
		          });     
                          
                          creaInfoWindows(marker, nombCA, dirCA,telfCA, refCA,codCA);                                                                                                      
                          
                          markers.push([marker, "i"+codServ]);                             
                          addLeyenda(rutIcono,siglasServ,codServ,nomServ);
                          addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);                          
                   }                                                        
              });                                          
                        
          });
    
          
}

function getCentrosProv(ubigeo){          
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param="q=select CA.COD_CA,CA.REF_CA, CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV, SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from SYS.GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_PROV=" + ubigeo  ;		          
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
                   codCA=datos[i].COD_CA; 
                   
                   rutIcono=DefineIconServ(codServ);
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);  
                         addLeyenda(rutIcono,siglasServ,codServ,nomServ);
                         addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);
                   }
                                                                            
                   var latlng = new google.maps.LatLng(lon_,lat_);                                                         
                   var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: capitalizarTexto(titulo),
				     	      icon: rutIcono
		   });  
                   
                   creaInfoWindows(marker, nombCA, dirCA,telfCA,refCa,codCA);
                   
                   markers.push([marker, "i"+codServ]);                             
                   
              });
                            
                        
          });
    
          
}

function getCentrosDist(ubigeo){          
          clearMarkers();
          clearLeyenda();
          clearToc();
          
          var param="q=select CA.COD_CA,CA.REF_CA,CA.DIR_CA,CA.TELEF_CA,SM.SIGLAS_SERV, SM.TIP_SERV, SM.NOM_SERV, CA.COD_SERV,NOM_CA,COORD_X, COORD_Y from SYS.GEO_CENTRO_ATENCION CA INNER JOIN GEO_SERVICIOS_MIMP SM ON SM.COD_SERV=CA.COD_SERV  where COORD_X is not null and  COORD_Y is not null and  COD_DIST=" + ubigeo  ;		          
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
                   codCA=datos[i].COD_CA; 
                   
                   rutIcono=DefineIconServ(codServ);
                   
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);  
                         addLeyenda(rutIcono,siglasServ,codServ,nomServ);
                         addToc(tipServ, codServ, siglasServ, nomServ,rutIcono);
                   }
                   
                   var latlng = new google.maps.LatLng(lon_,lat_);
                   
                   var marker = new google.maps.Marker({
				     	      position: latlng,
				     	      map: map,
				     	      title: capitalizarTexto(titulo),
				     	      icon: rutIcono  
		   });  
                   
                   creaInfoWindows(marker, nombCA, dirCA,telfCA,refCA,codCA);
                   
                   markers.push([marker, "i"+codServ]);                             
                   
              });
                            
                        
          });
    
          
}

function DefineIconServ(codServ){
    //dominioSite
    //http://181.65.173.175:8080/mimp.gis/assetsvisor/img/print.png
    var rutaIcon="http://"+dominioSite+"/mimp.gis/assetsvisor/img/iconosSrv/"+codServ.toUpperCase()+".png";
    //alert(rutaIcon);
    return rutaIcon;
    
    /*
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
   
   return rutaIcon;*/
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

function numberWithCommas(x) {
    if(isNaN(x)){
        return 0;
    }
    
    if (x===null){
        return 0;
    }
    //return x;
    var n=x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    return n.toString().replace(".", ",");
}

function reporteNacional(){    
     
    var varQuery="";
    $.each(distinctColsRep, function(j, item) {  
             varQuery+=","+"SUM(R."+item+") AS " + item;
    });
        
    //var param="q=SELECT S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,SUM(R.NUM_INV_EJEC) AS NUM_INV_EJEC , SUM(R.NUM_INV_PIM) AS NUM_INV_PIM,    SUM(R.NUM_BEN_EJEC1) AS NUM_BEN_EJEC1  FROM GEO_REPORTE_SERVICIOS R  INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO  WHERE R.COD_SERV IN ("+servShow+") GROUP BY S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ,R.PERIODO ORDER BY R.ANO , R.COD_SERV ASC";                
    var param="q=SELECT S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD";
    param+=varQuery;    
    param+=" FROM GEO_REPORTE_SERVICIOS R  INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO GROUP BY S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO ,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.ANO , R.COD_SERV ASC";
      //alert(param);  
    var h=new Date();	
    var jqxhr22=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                   
        $.each(repConf, function(n, itemConfServ) {           
            
               redirReporteHist(itemConfServ.codServ, itemConfServ.reportes, datos, "Peru:");            
           
        });
                            
    });  
           
    //==================================================================================================================================
        
    var txtSelect=$("#ddlDepartamnto option:selected").html();    
    
    lib.clear();    
        
    //var param="q=SELECT S.TIP_SERV, S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,R.PERIODO,R.ANO, sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC    from GEO_REPORTE_SERVICIOS R   INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV    INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.DEPART_CA";
    var param="q=SELECT S.TIP_SERV, S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,R.PERIODO,R.ANO ,E.NOM_ENTIDAD";
    param+=varQuery;
    //param+=" from GEO_REPORTE_SERVICIOS R   INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV    INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.DEPART_CA";
    param+=" from GEO_REPORTE_SERVICIOS R  INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD  INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV    WHERE R.ANO = (SELECT MAX(ANO) FROM  V_ULTIMOS_REPORTES)  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DEPART_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.DEPART_CA";
    var h=new Date();	
    // alert(param);    
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) {                                         
        $.each(repConf, function(n, item) {             
              redirReporteAll(item.codServ, item.reportes, datos, "Peru:", "Departamento","DEPART_CA");                                                         
        }); //fin
    });
        
     
    
    
    //++++++++++++++++OLD+++++++++++++++
    
    
    
}

function reporteDepartamental(ubigeo){
    
     var varQuery="";
    $.each(distinctColsRep, function(j, item) {  
             varQuery+=","+"SUM(R."+item+") AS " + item;
    });
    
    var txtSelect=$("#ddlDepartamnto option:selected").html();  
    lib.clear();
       
    var param="";
    if (flgVraem){       
       //param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  R.UBIGEO IN ("+dstVraem+") group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.ANO ASC " ;
       param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
       param+=varQuery;
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  R.UBIGEO IN ("+dstVraem+") group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.ANO ASC " ;
    }else{
       //param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  SUBSTR(R.UBIGEO,0,2)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.ANO ASC " ;
       param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
       param+=varQuery;
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  SUBSTR(R.UBIGEO,0,2)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.ANO ASC " ;
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
                 
        //$.each(distinct, function(i, servicio) {
        $.each(repConf, function(n, itemConfServ) {           
            
               redirReporteHist(itemConfServ.codServ, itemConfServ.reportes, datos, txtSelect+":");      
            
            /*
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Personas Atendidas</th>', '</tr>','</thead>' ];
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
            var tipServ="";
            var periodo="";
            
             
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     tipServ=datos[x].TIP_SERV;
                     periodo=datos[x].PERIODO;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO + "<br>" + capitalizarTexto(periodo), datos[x].NUM_BEN_EJEC1]);
                     dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[x].ANO+'</td>');
                     dataTblHistorico.push('<td>'+numberWithCommas(datos[x].NUM_BEN_EJEC1)+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[x].NUM_BEN_EJEC1;
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO + "<br>" + capitalizarTexto(periodo));
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[x].ANO+'</td>');
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_PIM)+'</td>');                                 
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_EJEC)+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[x].NUM_INV_PIM;
                     sumDevengados+=datos[x].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataPie,350,tipServ);
             
              dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+numberWithCommas(sumTHistoria)+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
             
             generaTabla(servicio,labelTab,txtSelect+":Poblaci\xf3n atendida en "+capitalizarTexto(nomServicio),dataTblHistorico,tipServ); 
            
             
             //Generar hostorico mim devngas
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),categorias,dataPIM,dataDev,tipServ);
             
              dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+numberWithCommas(sumPIM)+'</td>');              
              dataTblDevengados.push('<td>'+numberWithCommas(sumDevengados)+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
             generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados,tipServ); 
              
               */
      
        });
                            
    });  
      
            
    //=====================================================reporteDepartamental All==========================================================================
    
      
    
    
    var param="" ;		          
    if (flgVraem){
       //param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where UBIGEO IN ("+dstVraem+") and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV, R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
       param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ";
       param+=varQuery;
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where UBIGEO IN ("+dstVraem+") and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV, R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
    }else{
       //param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  SUBSTR(R.UBIGEO,0,2)="+ubigeo+"  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
       param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ";
       param+=varQuery;    
       //param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  SUBSTR(R.UBIGEO,0,2)="+ubigeo+"  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where  SUBSTR(R.UBIGEO,0,2)="+ubigeo+"  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.PROVIN_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.PROVIN_CA" ;		           
    }
    
    //alert(param);
    
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
        //$.each(distinct, function(i, servicio) {              
        
         $.each(repConf, function(n, item) { 
            
              redirReporteAll(item.codServ, item.reportes, datos, txtSelect+":", "Provincia","PROVIN_CA");  
               
               
               /*
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Provincia</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Provincia</th>', '<th>0 a 5 a\u00f1os</th>','<th>6 a 11 a\u00f1os</th>','<th>12 a 17 a\u00f1os</th>','<th>18 a 25 a\u00f1os</th>','<th>26 a 35 a\u00f1os</th>','<th>36 a 45 a\u00f1os</th>','<th>46 a 59 a\u00f1os</th>','<th>60 a m\u00e1s </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Provincia</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Sexual</th>', '</tr>','</thead>' ];
               
              var titulo="";
              var nomTab="";
               var nomServicio="";
               var tipServ="";
               var periodo="";
               var ano="";
              
              data.push('<tbody>');  
                          
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;                   
                   nomServicio=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   periodo=datos[i].PERIODO;
                   ano=datos[i].ANO;

                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].PROVIN_CA+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_H)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_M)+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].PROVIN_CA+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD1)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD2)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD3)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD4)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD5)+'</td>');
	           dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD6)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD7)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD8)+'</td>');
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
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC3)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC4)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC5)+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+numberWithCommas(sumT)+'</td>');
              data.push('<td>'+numberWithCommas(sumH)+'</td>');
              data.push('<td>'+numberWithCommas(sumM)+'</td>');
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
                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo"+ "<br>"+ano+"("+capitalizarTexto(periodo)+")",dataPie,tipServ);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data,tipServ);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr1)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr2)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr3)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr4)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr5)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr6)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr7)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr8)+'</td>');
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
                ['60 a m\u00e1s', sumGr8]
              ]; 
                            
              if (sumTGr>1){
                  var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaBarra(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+" , por grupos de edad"+ "<br>"+ano+"("+capitalizarTexto(periodo)+")",dataBar,300,tipServ);              
                  generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+" , por grupos de edad",dataGrupoEdad,tipServ);
              }
                                                       
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumPsicol)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumFisica)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumMental)+'</td>');              
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
                   ['Sexual  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                   if (nomTab==="SER001"){
                      generaPie(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual , por tipo de violencia"+ "<br>"+ano+"("+capitalizarTexto(periodo)+")",dataPieViol,tipServ);                                          
                      generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual , por tipo de violencia",dataTipoViole,tipServ);                       
                   }
               }
               
             contServ+=1;  
              */ 
               
        });
        
                           
                                          
    });
        
     
}

function reporteProvincial(ubigeo){
    
 //alert(ubigeo);
     var varQuery="";
    $.each(distinctColsRep, function(j, item) {  
             varQuery+=","+"SUM(R."+item+") AS " + item;
    });
    
    
    var txtSelect=$("#ddlProvincia option:selected").html(); 

    lib.clear();
    
    var param="";
    if (flgVraem){
        //param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where SUBSTR(R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+") group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.ANO ASC " ;
        param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
        param+=varQuery;
        param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where SUBSTR(R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+") group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.ANO ASC " ;
    }else{
        //param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,4)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.ANO ASC " ;
        param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
        param+=varQuery;
        param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,4)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD  ORDER BY R.COD_SERV,R.ANO ASC " ;
    }
    //alert(param);
    
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
                 
        //$.each(distinct, function(i, servicio) { 
         $.each(repConf, function(n, itemConfServ) {           
            
               redirReporteHist(itemConfServ.codServ, itemConfServ.reportes, datos, txtSelect+":"); 
               
            /*                 
            
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Personas Atendidas</th>', '</tr>','</thead>' ];
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
            var tipServ="";
            var periodo="";
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     tipServ=datos[x].TIP_SERV;
                     periodo=datos[x].PERIODO;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO + "<br>" + capitalizarTexto(periodo), datos[x].NUM_BEN_EJEC1]);
                      dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[x].ANO+'</td>');
                     dataTblHistorico.push('<td>'+numberWithCommas(datos[x].NUM_BEN_EJEC1)+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[x].NUM_BEN_EJEC1;
                     
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO+"<br>"+capitalizarTexto(periodo));
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[x].ANO+'</td>');
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_PIM)+'</td>');                                 
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_EJEC)+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[x].NUM_INV_PIM;
                     sumDevengados+=datos[x].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataPie,350,tipServ);
              dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+numberWithCommas(sumTHistoria)+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
              
              generaTabla(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataTblHistorico,tipServ); 
              
              //barra pim deveng
    
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),categorias,dataPIM,dataDev,tipServ);
              
              dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+numberWithCommas(sumPIM)+'</td>');              
              dataTblDevengados.push('<td>'+numberWithCommas(sumDevengados)+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
              generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados,tipServ); 
              
              */
        });
                            
    });  
      
            
    //=============================================reporteProvincial  All ===========================================================================================
    
      
    
    
    
    
    var param="";
    //alert(flgVraem);
    if (flgVraem){       
       //param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV  INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where SUBSTR (R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+")  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.DISTR_CA"; 
       param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ";
       param+=varQuery;
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV  INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where SUBSTR (R.UBIGEO,0,4)="+ubigeo+" and R.UBIGEO IN ("+dstVraem+")  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.DISTR_CA"; 
    }else{       
       //param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV  INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,4)="+ubigeo+"  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO ORDER BY R.COD_SERV,R.DISTR_CA"; 
       param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ";
       param+=varQuery;
       param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV  INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,4)="+ubigeo+"  and R.ANO= (SELECT MAX(ANO) FROM GEO_REPORTE_SERVICIOS )   GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.PERIODO,R.ANO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.DISTR_CA"; 
    }
    //alert(param);
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);                           
                   }
        });
                        
        //$.each(distinct, function(i, servicio) {              
        $.each(repConf, function(n, item) { 
            
              redirReporteAll(item.codServ, item.reportes, datos, txtSelect+":", "Distrito","DISTR_CA");  
       
             /* var servicio=item.codServ;
              alert(servicio);  
              
              
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Distrito</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Distrito</th>', '<th>0 a 5 a\u00f1os</th>','<th>6 a 11 a\u00f1os</th>','<th>12 a 17 a\u00f1os</th>','<th>18 a 25 a\u00f1os</th>','<th>26 a 35 a\u00f1os</th>','<th>36 a 45 a\u00f1os</th>','<th>46 a 59 a\u00f1os</th>','<th>60 a m\u00e1s </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Distrito</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Sexual</th>', '</tr>','</thead>' ];
               
              var titulo="";
              var nomTab="";
               var nomServicio="";
               var tipServ="";
               var periodo="";
               var ano="";
              
              data.push('<tbody>');  
            
              
              $.each(datos, function(i, item) {  
                 alert(datos[i].COD_SERV + " VS " + servicio);
                 if(datos[i].COD_SERV===servicio){
                     alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                  tipServ= datos[i].TIP_SERV;
                  periodo=datos[i].PERIODO;
                  ano=datos[i].ANO;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].DISTR_CA+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_H)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_M)+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD1)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD2)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD3)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD4)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD5)+'</td>');
	           dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD6)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD7)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD8)+'</td>');
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
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC3)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC4)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC5)+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }else{
                     alert('no');
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+numberWithCommas(sumT)+'</td>');
              data.push('<td>'+numberWithCommas(sumH)+'</td>');
              data.push('<td>'+numberWithCommas(sumM)+'</td>');
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

                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo"+ "<br>"+ano + "("+capitalizarTexto(periodo)+")",dataPie,tipServ);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data,tipServ);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr1)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr2)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr3)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr4)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr5)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr6)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr7)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr8)+'</td>');
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
                ['60 a m\u00e1s', sumGr8]
              ]; 
              
              
              if (sumTGr>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }
                  generaBarra(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad"+ "<br>"+ano + "("+capitalizarTexto(periodo)+")",dataBar,300,tipServ);              
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataGrupoEdad,tipServ);
              }
             
              
              
              
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumPsicol)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumFisica)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumMental)+'</td>');              
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
                   ['Sexual  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                 if (nomTab==="SER001"){
                   generaPie(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia"+ "<br>"+ano + "("+capitalizarTexto(periodo)+")",dataPieViol,tipServ);                                          
                   generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataTipoViole,tipServ);                     
                 }  
               }
             */  
               
        });
        
                           
                                          
    });
   
}

function reporteDistrital(ubigeo){
    
    var varQuery="";
    $.each(distinctColsRep, function(j, item) {  
             varQuery+=","+"SUM(R."+item+") AS " + item;
    });
    
    //alert(ubigeo);
    var txtSelect=$("#ddlDistrito option:selected").html();
    lib.clear();   
    
    
    
    //var param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO, sum(R.NUM_INV_EJEC) as NUM_INV_EJEC , sum(R.NUM_INV_PIM) as NUM_INV_PIM,    sum(R.NUM_BEN_EJEC1) as NUM_BEN_EJEC1 from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO   where  SUBSTR(R.UBIGEO,0,6)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.ANO ASC " ;
    var param="q=select S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
    param+=varQuery;
    param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO   where  SUBSTR(R.UBIGEO,0,6)="+ubigeo+" group by S.TIP_SERV,S.NOM_SERV,S.SIGLAS_SERV,R.COD_SERV,R.ANO,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.ANO ASC " ;
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
                 
        //$.each(distinct, function(i, servicio) { 
        $.each(repConf, function(n, itemConfServ) {           
            
               redirReporteHist(itemConfServ.codServ, itemConfServ.reportes, datos, txtSelect+":");   
            
            /*
             var dataTblHistorico=['<thead>','<tr>', '<th>A\u00f1o</th>', '<th>Personas Atendidas</th>', '</tr>','</thead>' ];
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
             var tipServ="";
             var periodo="";
             //alert(servicio);
             $.each(datos, function(x, item) {                    
                 if(datos[x].COD_SERV===servicio){
                     
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     tipServ=datos[x].TIP_SERV;
                     periodo=datos[x].PERIODO;
                     
                     //Para Historico
                     dataPie.push([datos[x].ANO+"<br>"+capitalizarTexto(periodo), datos[x].NUM_BEN_EJEC1]);
                     dataTblHistorico.push('<tr>');
                     dataTblHistorico.push('<td>'+datos[x].ANO+'</td>');
                     dataTblHistorico.push('<td>'+numberWithCommas(datos[x].NUM_BEN_EJEC1)+'</td>');                                 
                     dataTblHistorico.push('</tr>'); 
                     sumTHistoria+=datos[x].NUM_BEN_EJEC1;
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO+"<br>"+capitalizarTexto(periodo));
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[x].ANO+'</td>');
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_PIM)+'</td>');                                 
                     dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_EJEC)+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     sumPIM+=datos[x].NUM_INV_PIM;
                     sumDevengados+=datos[x].NUM_INV_EJEC;
                 }                 
             });
            
             //Generar Barra Historico
             generaBarra_2(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataPie,350,tipServ);
             dataTblHistorico.push('<tr style="font-weight:bold;">');
              dataTblHistorico.push('<td>TOTAL</td>');
              dataTblHistorico.push('<td>'+numberWithCommas(sumTHistoria)+'</td>');              
              dataTblHistorico.push('</tr>');                
              dataTblHistorico.push('</tbody>');
              
            generaTabla(servicio,labelTab,txtSelect+":Personas Atendidas en "+capitalizarTexto(nomServicio),dataTblHistorico,tipServ); 
              
             //barra deveng
             generaBarraDbl(servicio,labelTab,txtSelect+":Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),categorias,dataPIM,dataDev,tipServ);
             
             dataTblDevengados.push('<tr style="font-weight:bold;">');
              dataTblDevengados.push('<td>TOTAL</td>');
              dataTblDevengados.push('<td>'+numberWithCommas(sumPIM)+'</td>');              
              dataTblDevengados.push('<td>'+numberWithCommas(sumDevengados)+'</td>');              
              dataTblDevengados.push('</tr>');                
              dataTblDevengados.push('</tbody>');
              
              generaTabla(servicio,labelTab,txtSelect+": Ejecuci\xf3n Presupuestal en "+capitalizarTexto(nomServicio),dataTblDevengados,tipServ); 
            
            */
            
        });
                            
    });  
      
            
    //==========================================reporteDistrital All===================================================================================================================
    
      
    
    
    //var param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.ANO,R.PERIODO,  sum(NUM_BEN_EJEC5) as NUM_BEN_EJEC5, sum(NUM_BEN_EJEC4) as NUM_BEN_EJEC4, sum(NUM_BEN_EJEC3) as NUM_BEN_EJEC3, sum( NUM_GRUP_EDAD1) as NUM_GRUP_EDAD1,sum( NUM_GRUP_EDAD2) as NUM_GRUP_EDAD2,sum( NUM_GRUP_EDAD3) as NUM_GRUP_EDAD3, sum( NUM_GRUP_EDAD4) as NUM_GRUP_EDAD4,sum( NUM_GRUP_EDAD5) as NUM_GRUP_EDAD5,sum( NUM_GRUP_EDAD6) as NUM_GRUP_EDAD6,sum( NUM_GRUP_EDAD7) as NUM_GRUP_EDAD7,sum( NUM_GRUP_EDAD8) as NUM_GRUP_EDAD8,sum(NUM_BEN_EJEC1) AS NUM_BEN_EJEC1,sum(NUM_BEN_EJEC1_H ) AS NUM_BEN_EJEC1_H,sum(NUM_BEN_EJEC1_M) AS NUM_BEN_EJEC1_M ,sum(NUM_INV_EJEC) AS NUM_INV_EJEC  from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,6)="+ubigeo+"  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.ANO,R.PERIODO ORDER BY R.COD_SERV,R.DISTR_CA";      
    var param="q=SELECT  S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.ANO,R.PERIODO,E.NOM_ENTIDAD ";
    param+=varQuery;
    param+=" from GEO_REPORTE_SERVICIOS R INNER JOIN GEO_ENTIDAD E ON E.COD_ENTIDAD=R.COD_ENTIDAD INNER JOIN GEO_SERVICIOS_MIMP S ON S.COD_SERV=R.COD_SERV   INNER JOIN V_ULTIMOS_REPORTES VUR ON VUR.COD_ENTIDAD=R.COD_ENTIDAD AND VUR.COD_SERV=R.COD_SERV AND VUR.ANO=R.ANO AND VUR.TOP_FECHA=R.FEC_ENVIO where   SUBSTR(R.UBIGEO,0,6)="+ubigeo+" and R.ANO = (SELECT MAX(ANO) FROM  V_ULTIMOS_REPORTES)  GROUP BY S.TIP_SERV,S.NOM_SERV,R.COD_SERV,S.SIGLAS_SERV,R.DISTR_CA,R.ANO,R.PERIODO,E.NOM_ENTIDAD ORDER BY R.COD_SERV,R.DISTR_CA";      
        
    var h=new Date();	
         
    var jqxhr2=$.post("http://"+dominioSite+"/mimp.gis/pages/home/info?"+h, param,function(datos) { 
                    
        var distinct=[];
            
        $.each(datos, function(n, item) {              
                   codServ=datos[n].COD_SERV;                                      
                   if (distinct.indexOf(codServ, 0)=== -1 ){
                         distinct.push(codServ);                           
                   }
        });
                        
        //$.each(distinct, function(i, servicio) {              
              
        $.each(repConf, function(n, item) { 
            
              redirReporteAll(item.codServ, item.reportes, datos, txtSelect+":", "Distrito","DISTR_CA");  
              //alert(servicio);  
              
              /*
              var sumT=0; var sumH=0; var sumM=0; var sumE=0;              
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
              var sumPsicol=0; var sumFisica=0; var sumMental=0;
              
              var data=['<thead>','<tr>', '<th>Distrito</th>', '<th>Persona Atendidas Total</th>','<th>Persona Atendidas Hombre</th>','<th>Persona Atendidas Mujer</th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>Distrito</th>', '<th>0 a 5 a\u00f1os</th>','<th>6 a 11 a\u00f1os</th>','<th>12 a 17 a\u00f1os</th>','<th>18 a 25 a\u00f1os</th>','<th>26 a 35 a\u00f1os</th>','<th>36 a 45 a\u00f1os</th>','<th>46 a 59 a\u00f1os</th>','<th>60 a m\u00e1s </th>', '</tr>','</thead>' ];
              var dataTipoViole=['<thead>','<tr>', '<th>Distrito</th>', '<th>Psicol\xf3gica</th>','<th>F\xEDsica</th>','<th>Sexual</th>', '</tr>','</thead>' ];
               
              var titulo="";  
              var nomTab="";
              var nomServicio="";
              var tipServ="";
              var periodo="";
              var ano="";
 
              data.push('<tbody>');  
            
              
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   //  alert('si');
                   titulo=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   ano=datos[i].ANO;
                   periodo=datos[i].PERIODO;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i].DISTR_CA+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_H)+'</td>');
                   data.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC1_M)+'</td>');
                   //data.push('<td>'+datos[i].NUM_INV_EJEC+'</td>');
                   data.push('</tr>');  
                   
                   sumT+=datos[i].NUM_BEN_EJEC1;
                   sumH+=datos[i].NUM_BEN_EJEC1_H;
                   sumM+=datos[i].NUM_BEN_EJEC1_M;
                   sumE+=datos[i].NUM_INV_EJEC;
        
                   
                   //*******Por grupo de edad*******************************************************                  
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i].DISTR_CA+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD1)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD2)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD3)+'</td>');
                   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD4)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD5)+'</td>');
	           dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD6)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD7)+'</td>');
		   dataGrupoEdad.push('<td>'+numberWithCommas(datos[i].NUM_GRUP_EDAD8)+'</td>');
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
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC3)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC4)+'</td>');
                   dataTipoViole.push('<td>'+numberWithCommas(datos[i].NUM_BEN_EJEC5)+'</td>');
                   dataTipoViole.push('</tr>');
                   
                   sumPsicol+=datos[i].NUM_BEN_EJEC3;
                   sumFisica+=datos[i].NUM_BEN_EJEC4;
                   sumMental+=datos[i].NUM_BEN_EJEC5;                                      
                 }
                 
              });   
        
              
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              data.push('<td>'+numberWithCommas(sumT)+'</td>');
              data.push('<td>'+numberWithCommas(sumH)+'</td>');
              data.push('<td>'+numberWithCommas(sumM)+'</td>');
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

                  generaPie(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo"+"<br>"+ano+"("+capitalizarTexto(periodo)+")",dataPie,tipServ);                                            
                  generaTabla(nomTab,titulo,txtSelect+":Casos atendidos "+capitalizarTexto(titulox)+", por sexo",data,tipServ);  
               }
               
              
              
              
              //*********Por grupo de edad********************************
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr1)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr2)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr3)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr4)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr5)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr6)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr7)+'</td>');
              dataGrupoEdad.push('<td>'+numberWithCommas(sumGr8)+'</td>');
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
                ['60 a m\u00e1s', sumGr8]
              ]; 
              
              
              if (sumTGr>1){
                   var titulox="";
                  if (nomTab=="SER001"){
                      titulox="por violencia familiar y sexual";
                  }else{
                      titulox=" en "+nomServicio;
                  }

                  generaBarra(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad"+"<br>"+ano+"("+capitalizarTexto(periodo)+")",dataBar,300,tipServ);              
                  generaTabla(nomTab,titulo,txtSelect+" :Casos atendidos "+capitalizarTexto(titulox)+", por grupos de edad",dataGrupoEdad,tipServ);
              }
             
              
              
              
              //********Por Tipo de Violencia******************************
                            
              dataTipoViole.push('<tr style="font-weight:bold;">');
              dataTipoViole.push('<td>TOTAL</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumPsicol)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumFisica)+'</td>');
              dataTipoViole.push('<td>'+numberWithCommas(sumMental)+'</td>');              
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
                   ['Sexual  '+ porcMental + "%",   sumMental]                
               ];
               
               if (sumTipViol>1){
                 if (nomTab==="SER001"){  
                     generaPie(nomTab,titulo,txtSelect+":Casos atendidos por violencia familiar y sexual, por tipo de violencia"+"<br>"+ano+"<br>"+capitalizarTexto(periodo),dataPieViol,tipServ);                                          
                     generaTabla(nomTab,titulo,txtSelect+":Casos atendidos por violencia familiar y sexual, por tipo de violencia",dataTipoViole,tipServ);
                 }
               }
               
               */
        });
        
                           
                                          
    }); 
}

function redirReporteHist(servicio, reportes,datos, titulo){
  
 $.each(reportes, function(n, item) {              
            switch(item.tipo) { // R1= Historico por años, barra simple  // R2= historico por años, barra doble //  R3    
             case "r1":
                generaReporte_R2 (servicio, item, datos, titulo +  item.titulo);
                break;
             case "r2":
                generaReporte_R2 (servicio, item, datos, titulo +  item.titulo);
                break;                          
            }             
 });
  
}

function redirReporteAll(servicio, reportes,datos, titulo,titColDesc,fielColDesc){
  
 $.each(reportes, function(n, item) {
            //alert(item.tipo);
            switch(item.tipo) {               
             case "r3":
                generaReporte_R3 (servicio, item, datos, titulo +  item.titulo,titColDesc,fielColDesc);
                break;
             case "r4":
                generaReporte_R4 (servicio, item, datos, titulo +  item.titulo,titColDesc,fielColDesc);
                break;
             case "r5":
                generaReporte_R5 (servicio, item, datos, titulo +  item.titulo,titColDesc,fielColDesc);
                break;                                       
            }             
 });
  
}

function generaReporte_R1 (servicio, varsRep, datos, titulo,titColDesc,fielColDesc){
   //Reporte historico tipo barra 1 sola                   
}

function generaReporte_R2 (servicio, varsRep, datos, titulo,titColDesc,fielColDesc){
//Reporte historico tipo barra doble columna   
            //alert(titulo);
            var totalizador=0;
            var dataTblDevengados="";
            var sumDevengados=0;
            var sumPIM=0;
            var sumatorias=[];
                         
            var dataPie=[];
             
            var categorias=[];             
            var dataPIM=[];
            var dataDev=[];
            var dataEjecucion=[];
            var dataArrai=[];
             
            var labelTab="";
            var nomServicio="";
            var tipServ="";
            var periodo="";
            var entidad="" ;
            
            dataTblDevengados=['<thead>','<tr>', '<th>A\u00f1o</th>'];
            
            $.each(varsRep.variables, function(a, item) {
                   //alert(varsRep.alias[a]);
                   dataTblDevengados.push('<th>'+varsRep.alias[a]+'</th>');
                   sumatorias[a]=0;
                   
                   //formando
                   var arr_=[];
                   dataEjecucion.push(arr_);
                   
            });            
            //dataTblDevengados.push('<th>PIM</th>');
            //dataTblDevengados.push('<th>Ejecutado</th>');
            
            dataTblDevengados.push('</tr>','</thead>');
            
            var flagNew=false;
            $.each(datos, function(x, item) { 
                
                 if(datos[x].COD_SERV===servicio){
                     flagNew=true;
                     labelTab=datos[x].SIGLAS_SERV;
                     nomServicio=datos[x].NOM_SERV;
                     tipServ=datos[x].TIP_SERV;
                     periodo=datos[x].PERIODO;  
                     entidad=datos[x].NOM_ENTIDAD;
                     
                     //para Inversion  
                     categorias.push(datos[x].ANO + "<br>" + capitalizarTexto(periodo));
                     $.each(varsRep.variables, function(a, item) {                       
                          //dataEjecucion[a].push({titulo:varsRep.alias[a] , arrai:datos[x][varsRep.variables[a]]}); 
                          //alert(varsRep.alias[a]);
                          //dataArrai.push(datos[x][varsRep.variables[a]]);
                          dataEjecucion[a].push(datos[x][varsRep.variables[a]]);                     
                     }); 
                     dataPIM.push(datos[x].NUM_INV_PIM);
                     dataDev.push(datos[x].NUM_INV_EJEC);
                     
                     dataTblDevengados.push('<tr>');
                     dataTblDevengados.push('<td>'+datos[x].ANO+'</td>');
                     
                     $.each(varsRep.variables, function(a, item) {                       
                       dataTblDevengados.push('<td>'+numberWithCommas(datos[x][varsRep.variables[a]])+'</td>');                         
                     });  
                     //dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_PIM)+'</td>');                                 
                     //dataTblDevengados.push('<td>'+numberWithCommas(datos[x].NUM_INV_EJEC)+'</td>');                                 
                     dataTblDevengados.push('</tr>'); 
                     
                     $.each(varsRep.variables, function(a, item) {//
                         var valor= sumatorias[a] + datos[x][varsRep.variables[a]];                          
                         sumatorias[a]= valor;    
                         totalizador+=valor;
                     }); 
                     //sumPIM+=datos[x].NUM_INV_PIM;
                     //sumDevengados+=datos[x].NUM_INV_EJEC;
                 }                 
            });
            
            if (flagNew && totalizador>0){
                
            //alert(sumatorias[0]);
            //alert(sumatorias[1]);
            //Generar Barra grafica                               
            generaBarraDbl2(servicio,labelTab,capitalizarTexto(titulo) + "<br>" + "Fuente : " + capitalizarTexto(entidad),categorias,dataEjecucion,varsRep,dataPIM,dataDev,tipServ);
            
            //Genera Tabla Atributos
            dataTblDevengados.push('<tr style="font-weight:bold;">');
            dataTblDevengados.push('<td>TOTAL</td>');            
            $.each(varsRep.variables, function(a, item) {
                   dataTblDevengados.push('<td>'+numberWithCommas(sumatorias[a])+'</td>');      
            });             
            //dataTblDevengados.push('<td>'+numberWithCommas(sumPIM)+'</td>');              
            //dataTblDevengados.push('<td>'+numberWithCommas(sumDevengados)+'</td>');             
            dataTblDevengados.push('</tr>');                
            dataTblDevengados.push('</tbody>');
              
            generaTabla(servicio,labelTab,capitalizarTexto(titulo),dataTblDevengados,tipServ); 
             
                
            }
            
 
    
}

function generaReporte_R3 (servicio, varsRep, datos, titulo,titColDesc,fielColDesc){
    
    //reporte por sexo
             var totalizador=0;
                  
              var sumT=0;             
              var sumatorias=[];
                             
              var data=['<thead>','<tr>', '<th>'+titColDesc+'</th>'];                             
              $.each(varsRep.variables, function(a, item) {                   
                   data.push('<th>'+varsRep.alias[a]+'</th>');  
                   
                   sumatorias[a]=0;
              }); 
              data.push('</tr>','</thead>' );
               
              var tituloIn="";
              var nomTab="";
              var nomServicio="";
              var tipServ="";
              var periodo="";
              var ano="";
              var entidad="";
              
              data.push('<tbody>');  
               
              var flagNew=false;
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                     flagNew=true;
                   //  alert('si');
                   tituloIn=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   periodo=datos[i].PERIODO;
                   ano=datos[i].ANO;
                   entidad=datos[i].NOM_ENTIDAD;
                   
                   //********Por Sexo ************************************************************
                   data.push('<tr>');
                   data.push('<td>'+datos[i][fielColDesc]+'</td>');
                   
                   
                   var sumVariables=0;
                   $.each(varsRep.variables, function(a, item) {                       
                          sumVariables+=  datos[i][varsRep.variables[a]];                  
                     });
                   //data.push('<td>'+numberWithCommas(sumVariables)+'</td>');                   
                   
                   $.each(varsRep.variables, function(a, item) {                       
                       data.push('<td>'+numberWithCommas(datos[i][varsRep.variables[a]])+'</td>');                         
                     }); 
                                   
                   data.push('</tr>');  
                   
                   
                    sumT+=sumVariables;
                   
                    $.each(varsRep.variables, function(a, item) {//
                         var valor= sumatorias[a] + datos[i][varsRep.variables[a]];                          
                         sumatorias[a]= valor;   
                         totalizador+=valor;
                     }); 
                                                            
                 }
                 
              });   
              
           if (flagNew && totalizador>0){
              //Los totales
              data.push('<tr style="font-weight:bold;">');
              data.push('<td>TOTAL</td>');
              //data.push('<td>'+numberWithCommas(sumT)+'</td>');
              $.each(varsRep.variables, function(a, item) {
                   data.push('<td>'+numberWithCommas(sumatorias[a])+'</td>');      
              }); 
                           
              data.push('</tr>');                
              data.push('</tbody>');  
              
              var porcentajes=[];
              
              $.each(varsRep.variables, function(a, item) {
                    porcentajes[a]=parseFloat(((sumatorias[a]*100)/sumT).toFixed(2)); 
              });
                                          
               var dataPie=[];
               $.each(varsRep.variables, function(a, item) {                   
                    dataPie.push( [ varsRep.alias[a] + ' ' + porcentajes[a] + "%", sumatorias[a] ] );
               });
                                                            
               generaPie(nomTab,tituloIn,capitalizarTexto(titulo)+ "<br>"+ ano + "(" + capitalizarTexto(periodo) +")" + "<br>"+ "Fuente : "+ capitalizarTexto(entidad) ,dataPie,tipServ);                                            
               generaTabla(nomTab,tituloIn,capitalizarTexto(titulo),data,tipServ);  
                                       
           }
                    
             
}

function generaReporte_R4 (servicio, varsRep, datos, titulo,titColDesc,fielColDesc){
    //Reporte Tipo barra de los grupos de eded
 var totalizador=0;
 
              var sumGr1=0; var sumGr2=0; var sumGr3=0; var sumGr4=0; var sumGr5=0; var sumGr6=0; var sumGr7=0; var sumGr8=0;
               var sumatorias=[];
               
              //var dataGrupoEdad=['<thead>','<tr>', '<th>Departamento</th>', '<th>0 a 5 a\u00f1os</th>','<th>6 a 11 a\u00f1os</th>','<th>12 a 17 a\u00f1os</th>','<th>18 a 25 a\u00f1os</th>','<th>26 a 35 a\u00f1os</th>','<th>36 a 45 a\u00f1os</th>','<th>46 a 59 a\u00f1os</th>','<th>60 a m\u00e1s </th>', '</tr>','</thead>' ];
              var dataGrupoEdad=['<thead>','<tr>', '<th>'+titColDesc+'</th>'];                  
              $.each(varsRep.variables, function(a, item) {                   
                   dataGrupoEdad.push('<th>'+varsRep.alias[a]+'</th>');                     
                   sumatorias[a]=0;
              });
              dataGrupoEdad.push('</tr>','</thead>' );
              
               
              var tituloIn="";
              var nomTab="";
              var nomServicio="";
              var tipServ="";
              var periodo="";
              var ano=""; 
              var entidad="";
               
              var flagNew=false;
              $.each(datos, function(i, item) {  
                  
                 if(datos[i].COD_SERV===servicio){
                   flagNew=true;
                   tituloIn=datos[i].SIGLAS_SERV;
                   nomTab=datos[i].COD_SERV;
                   nomServicio=datos[i].NOM_SERV;
                   tipServ=datos[i].TIP_SERV;
                   periodo=datos[i].PERIODO;
                   ano=datos[i].ANO;
                   entidad=datos[i].NOM_ENTIDAD;
                     
                   //Tabklas   
                   dataGrupoEdad.push('<tr>');
                   dataGrupoEdad.push('<td>'+datos[i][fielColDesc]+'</td>');                                      
                   $.each(varsRep.variables, function(a, item) {                       
                       dataGrupoEdad.push('<td>'+numberWithCommas(datos[i][varsRep.variables[a]])+'</td>');                         
                   });                                                                            
                   dataGrupoEdad.push('</tr>');
                   
                   //Para sumatorias totales
                   $.each(varsRep.variables, function(a, item) {//
                         var valor= sumatorias[a] + datos[i][varsRep.variables[a]];                          
                         sumatorias[a]= valor;    
                         totalizador+=valor;
                   });                   
                                                         
                 }
                 
              });                   
              
             if (flagNew && totalizador>0){
                 
              dataGrupoEdad.push('<tr style="font-weight:bold;">');
              dataGrupoEdad.push('<td>TOTAL</td>');
              
              var sumTGr=0;
              $.each(varsRep.variables, function(a, item) {
                   dataGrupoEdad.push('<td>'+numberWithCommas(sumatorias[a])+'</td>'); 
                   sumTGr+=sumatorias[a];
              });              
              
              dataGrupoEdad.push('</tr>');  
              dataGrupoEdad.push('</tbody>');              
             
              var dataBar=[];
              $.each(varsRep.variables, function(a, item) {                   
                    dataBar.push( [ varsRep.alias[a] , sumatorias[a] ] );
              });
                                                                            
              generaBarra(nomTab,tituloIn,capitalizarTexto(titulo)+ "<br>"+ano + "(" + capitalizarTexto(periodo)+")"+ "<br>"+ "Fuente : "+ capitalizarTexto(entidad),dataBar,300,tipServ);              
              generaTabla(nomTab,tituloIn,capitalizarTexto(titulo),dataGrupoEdad,tipServ);
                            
             }
              
                                                          
    
}

function generaReporte_R5 (servicio, varsRep, datos, titulo,titColDesc,fielColDesc){
   //Reporte Tipo  pie el ultimo     
}





function generaBarra(tabId,tabTitle,titulo, datos,alto,tipSrv){
    //alert(tipSrv);
    lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       typeserv:tipSrv,
        chart: {
            type: 'column'
        },
         height:alto,
        title: {
            text: titulo,
            style: {

                 font: 'normal 14px "Helvetica Neue", sans-serif',
                 color : '#222222'
                 ,textShadow: '1px 1px 1px rgba(0,0,0,.25)'
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
                    textShadow: '0 0 3px #fff'
                }
            }
        }]
     
    });
}





function generaBarra_2(tabId,tabTitle,titulo, datos,alto,tipSrv){
    lib.graph({'tabId':tabId,'tabTitle':tabTitle,
        typeserv:tipSrv,
        chart: {
            type: 'column',
            backgroundColor: null
        },
         height:alto,
        title: {
            text: titulo,
        style: {

                 font: 'bold 14px "Helvetica Neue", sans-serif',
                  color : '#3e576f'
               }
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'category',
            labels: {
              //  rotation: -90,
                style: {
                    fontSize: '11px',
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
            },         
            style: {
                    fontSize: '11px'
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
                y: 10, 
                style: {
                    fontSize: '12px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 2px #fff'
                }
            }
        }]
     
    });
}



function generaBarraDbl2(tabId,tabTitle,titulo, categorias,dataEjecucion,objTitulo,dataPIM,dataDev,tipSrv){
    
   //formdno
    var nSeries=[];
    $.each(dataEjecucion, function(a, subArr) {
        
           //alert(objTitulo.alias[a]);
           //alert(subArr.length);
           
           nSeries.push({
            name: objTitulo.alias[a],
            data: subArr,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px #fff'
                }
            }
          });
          
    }); 
    
   // alert("series :::"+ nSeries.length);
    
   lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       typeserv:tipSrv,
       chart: {
            type: 'column',
            backgroundColor: null
        },
        title: {
            text: titulo,
        style: {
                 font: 'bold 14px "Helvetica Neue", sans-serif',
                 color : '#3e576f'
               }
        },
        
        xAxis: {
            categories: categorias,
             labels: {
                //rotation: 0,
                style: {
                    fontSize: '11px',
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
            },
            style: {
                    fontSize: '11px'
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
        series: nSeries /*[{
            name: 'PIM',
            data: dataPIM,
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px #fff'
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
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 2px #fff'
                }
            }

        }]*/
     
    });
}


function generaBarraDbl(tabId,tabTitle,titulo, categorias,dataPIM,dataDev,tipSrv){
    
   lib.graph({'tabId':tabId,'tabTitle':tabTitle,
       typeserv:tipSrv,
       chart: {
            type: 'column',
            backgroundColor: null
        },
        title: {
            text: titulo,
        style: {

                 font: 'bold 14px "Helvetica Neue", sans-serif',
                  color : '#3e576f'
               }
        },
        
        xAxis: {
            categories: categorias,
             labels: {
                //rotation: 0,
                style: {
                    fontSize: '11px',
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
            },
            style: {
                    fontSize: '11px'
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
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px #fff'
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
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 2px #fff'
                }
            }

        }]
     
    });
}


function generaPie(tabId,tabTitle,titulo,data,tipSrv){
    //'tabId':'car','tabTitle':'Titulo de CAR',
  lib.graph({'tabId':tabId,'tabTitle':tabTitle, 
      typeserv:tipSrv,
   chart: {
            type: 'pie',
            backgroundColor: null,
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: titulo,
             style: {

                 font: 'bold 13px "Helvetica Neue", sans-serif',
                  color : '#3e576f'
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

function generaTabla(tabId,tabTitle,titulo,datos,tipSrv){  
    //'tabId':'car','tabTitle':'Titulo de CAR',
    lib.grid({'tabId':tabId,'tabTitle':tabTitle,'title':titulo, 'data': datos , 'typeserv':tipSrv});
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