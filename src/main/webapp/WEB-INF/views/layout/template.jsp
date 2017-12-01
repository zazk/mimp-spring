<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Ingreso | MIMP - GIS</title>
        <!-- Mobile specific metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- Force IE9 to render in normal mode -->
        <!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
        <meta name="author" content="SuggeElson" />
        <meta name="description" content="AppStart admin template - new premium responsive admin template. This template is designed to help you build the site administration without losing valuable time.Template contains all the important functions which must have one backend system.Build on great twitter boostrap framework"
        />
        <meta name="keywords" content="admin, admin template, admin theme, responsive, responsive admin, responsive admin template, responsive theme, themeforest, 960 grid system, grid, grid theme, liquid, jquery, administration, administration template, administration theme, mobile, touch , responsive layout, boostrap, twitter boostrap"
        />
        <meta name="application-name" content="AppStart admin template" />
        <!-- Import google fonts - Heading first/ text second -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,700' rel='stylesheet' type='text/css'>
        <!-- Css files -->
        <!-- Icons -->
        <link href="<c:url value='/assets/css/icons.css'/>" rel="stylesheet" />
        <!-- Bootstrap stylesheets (included template modifications) -->
        <link href="<c:url value='/assets/css/bootstrap.css'/>" rel="stylesheet" />
        <!-- jQueryUI -->
        <link href="<c:url value='/assets/css/appstart-theme/jquery.ui.all.css'/>" rel="stylesheet" />
        <!-- Plugins stylesheets (all plugin custom css) -->
        <link href="<c:url value='/assets/css/plugins.css'/>" rel="stylesheet" />
        <!-- Main stylesheets (template main css file) -->
        <link href="<c:url value='/assets/css/main.css'/>" rel="stylesheet" />
        <!-- Custom stylesheets ( Put your own changes here ) -->
        <link href="<c:url value='/assets/css/custom.css'/>" rel="stylesheet" />
        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='/assets/img/ico/apple-touch-icon-144-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value='/assets/img/ico/apple-touch-icon-114-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value='/assets/img/ico/apple-touch-icon-72-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" href="<c:url value='/assets/img/ico/apple-touch-icon-57-precomposed.png'/>">
        <link rel="icon" href="<c:url value='/assets/img/ico/favicon.ico'/>" type="image/png">
        <!-- Windows8 touch icon ( http://www.buildmypinnedsite.com/ )-->
        <meta name="msapplication-TileColor" content="#3399cc" />
    </head>
    <body class="ovh">
        <div id="preloader">
            <div id="preloader-logo">
                <img src="<c:url value='/assets/img/preloader-logo.png'/>" alt="Logo">
            </div>
            <div id="preloader-icon">
                <i class="im-spinner icon-spin"></i>
            </div>
        </div>
        <!-- Start #header -->
        <div id="header">
            <div class="container-fluid">
                <div class="navbar">
                    <div class="navbar-header">
                        <!-- Show navigation toggle on phones -->
                        <button id="showNav" class="navbar-toggle" type="button">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <!-- Show logo for large screens and laptops -->
                        <a class="navbar-brand visible-lg visible-md" href="index.html">
                            <img src="<c:url value='/assets/img/logo.png'/>" alt="Jump start">
                        </a>
                        <!-- Show logo for small screens -->
                        <a class="navbar-brand hidden-lg hidden-md hidden-xs" href="index.html">
                            <img src="<c:url value='/assets/img/logo.png'/>" alt="Jump start">
                        </a>
                    </div>
                    <nav id="top-nav" class="navbar-no-collapse" role="navigation">
                        
                        <!-- Navbar nav -->
                        <ul class="nav navbar-nav pull-right">
                            <li class="hidden-lg hidden-md">
                                <!-- close button for search form in small screens -->
                                <button type="button" class="close closeSearchForm" aria-hidden="true">&times;</button>
                                <!-- show search button in small screens -->
                                <a class="resSearchBtn hidden-lg hidden-md" href="#"><i class="im-search3"></i></a>
                            </li>
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown">
                                    <i class="im-earth"></i>
                                    <i class="nav-notification im-circle2"></i>
                                    <span class="sr-only">Notificaciones</span>
                                </a>
                                <ul class="dropdown-menu right dropdown-notification" role="menu">
                                    <li><a href="#" class="dropdown-menu-header">Notificaciones</a>
                                    </li>
                                    <li class="divider"></li>
                                    <c:forEach items="${notResumen}" var="notificacion">
                                    <li><a href="#"><i class="${accionesUsuario.get(notificacion.idaccion).get("clase")}"></i>${notificacion.mensaje}</a>
                                    </li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a href="#" class="view-all">Ver Todos</a>
                                    </li>
                                </ul>
                            </li> 
                            <li class="dropdown">
                                <a href="#" data-toggle="dropdown">
                                    <img class="avatar" src="<c:url value='/assets/img/avatars/1.jpg'/>" width="36" height="36" alt="Jonh Doe"> <span class="avatar-name">${sessionScope.usuario.nombre}</span>
                                    <span class="caret ml5"></span>
                                </a>
                                <ul class="dropdown-menu right" role="menu">
                                    <li><a href="<c:url value='/pages/usuarios/editar'/>?idusuario=${sessionScope.usuario.IDUSUARIO}"><i class="im-user"></i> Perfil</a>
                                    </li>
                                    <li><a href="<c:url value='/pages/usuarios/logout'/>"><i class="im-switch"></i> Salir</a>
                                    </li>
                                </ul>
                            </li> 
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- End #header -->
        <!-- Start #sidebar -->
        <aside id="sidebar">
            <!-- Start .sidebar-inner -->
            <div class="sidebar-inner">
                <!-- Start .toggle-sidebar -->
                <div class="toggle-sidebar">
                    <a href="#"><i class="im-arrow7"></i></a>
                </div>
                
                <!-- End .toggle-sidebar -->
                <div class="option-buttons">
                    
                </div>
                <!-- Start .sidebar-scrollarea -->
                <div class="sidebar-scrollarea">
                    <ul id="sideNav" class="nav nav-pills nav-stacked">
                        <li><a href="<c:url value='/pages/usuarios/panel'/>"> <i class="im-screen"></i><span class="txt">Panel</span></a></li>
                        <c:if test="${sessionScope.usuario.idperfil != 1}">
                        <li><a href="<c:url value='/pages/usuarios/listado'/>"> <i class="im-users"></i><span class="txt">Usuarios</span></a></li>
                        <li><a href="<c:url value='/pages/usuarios/agregar'/>"> <i class="im-user-plus"></i><span class="txt">Agregar Usuario</span></a></li>
                        </c:if>
                        <li><a href="<c:url value='/pages/usuarios/enviar'/>"> <i class="im-screen"></i><span class="txt">Enviar Información</span></a></li>
                        <li><a href="<c:url value='/pages/usuarios/historico'/>"> <i class="im-screen"></i><span class="txt">Historial</span></a></li>

                        <li><a href="<c:url value='/pages/usuarios/mensajes'/>"> <i class="im-envelop2"></i><span class="txt">Mensajes</span><span class="label">${numMensajes}</span></a></li>
                        <li><a href="<c:url value='/pages/centrosatencion/listado'/>"> <i class="im-screen"></i><span class="txt">Centros de Atención</span></a></li>
                        <c:if test="${sessionScope.usuario.idperfil != 1}">
                        <li><a href="<c:url value='/pages/usuarios/notificaciones'/>"> <i class="im-user-plus"></i><span class="txt">Notificaciones</span></a></li>
                        </c:if>
                        <li><a href="<c:url value='/pages/usuarios/ayuda'/>"> <i class="im-info"></i><span class="txt">Ayuda</span></a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <!-- End .sidebar-scrollarea -->
            </div>
            <!-- End .sidebar-inner -->
        </aside> 
        <!-- Start #content -->
        <div id="content">
            <!-- Start .content-wrapper -->

            <tiles:insertAttribute name="body" />
            <!-- End .content-wrapper -->
            <div class="clearfix"></div>
        </div>
        <!-- End #content -->
        <!-- Javascripts -->
        <!-- Important javascript libs(put in all pages) -->
        <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script>
        window.jQuery || document.write('<script src="<c:url value='/assets/js/libs/jquery-2.1.1.min.js'/>">\x3C/script>')
        </script>
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script>
        window.jQuery || document.write('<script src="<c:url value='/assets/js/libs/jquery-ui-1.10.4.min.js'/>">\x3C/script>')
        </script>
        <!--[if lt IE 9]>
  <script type="text/javascript" src="<c:url value='/assets/js/libs/excanvas.min.js'/>"></script>
  <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <script type="text/javascript" src="<c:url value='/assets/js/libs/respond.min.js'/>"></script>
<![endif]-->
        <!-- Core plugins ( not remove ) -->
        <script src="<c:url value='/assets/js/libs/modernizr.custom.js'/>"></script>
        <!-- Bootstrap plugins -->
        <script src="<c:url value='/assets/js/bootstrap/bootstrap.js'/>"></script>
        <!-- Handle responsive view functions -->
        <script src="<c:url value='/assets/js/jRespond.min.js'/>"></script>
        <!-- Custom scroll for sidebars,tables and etc. -->
        <script src="<c:url value='/assets/plugins/core/slimscroll/jquery.slimscroll.min.js'/>"></script>
        <script src="<c:url value='/assets/plugins/core/slimscroll/jquery.slimscroll.horizontal.min.js'/>"></script>
        <!-- Highlight code blocks -->
        <script src="<c:url value='/assets/plugins/forms/tags/jquery.tagsinput.min.js'/>"></script>
        <!-- Proivde quick search for many widgets -->
        <script src="<c:url value='/assets/plugins/core/quicksearch/jquery.quicksearch.js'/>"></script>
        <!-- Other plugins ( load only nessesary plugins for every page) -->
        <script src="<c:url value='/assets/plugins/forms/icheck/jquery.icheck.js'/>"></script>
        <script src="<c:url value='/assets/js/pages/ekko-lightbox.js'/>"></script>
        <script src="<c:url value='/assets/js/jquery.appStart.js'/>"></script>
        <script src="<c:url value='/assets/js/app.js'/>"></script>
    </body>
</html>