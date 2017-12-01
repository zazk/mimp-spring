<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
    <body class="login-page">
        <!-- Start #login -->
        <div id="login" class="animated bounceIn">
            <img id="logo" src="<c:url value='/assets/img/logo.png'/>" alt="appstart Logo">
            <!-- Start .login-wrapper -->
            <tiles:insertAttribute name="body" />
            <!-- End #.login-wrapper -->
        </div>
        <!-- End #login -->
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
        <!-- Bootstrap plugins -->
        <script src="<c:url value='/assets/js/bootstrap/bootstrap.js'/>"></script>
        <!-- Form plugins -->
        <script src="<c:url value='/assets/plugins/forms/icheck/jquery.icheck.js'/>"></script>
        <script src="<c:url value='/assets/plugins/forms/validation/jquery.validate.js'/>"></script>
        <script src="<c:url value='/assets/plugins/forms/validation/additional-methods.min.js'/>"></script>
        <!-- Init plugins olny for this page -->
        <script src="<c:url value='/assets/js/pages/login.js'/>"></script>
    </body>
</html>