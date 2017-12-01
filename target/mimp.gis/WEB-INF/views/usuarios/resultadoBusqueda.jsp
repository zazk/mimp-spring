<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
    function deleteUsuario() {
        if (confirm("Desea eliminar este usuario")) {
            return true;
        }
        return false;
    }
</script>
<div class="container">
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <th>Login</th>
            <th style="width: 150px;"></th>
            <th style="width: 150px;"></th>
        </tr>
        <c:forEach var="usuario" items="${resultado}">
            <tr>
                <td>${usuario.NoUsuario}</td>
                <td>${usuario.login}</td>
                <td><a href="<c:url value='/pages/usuarios/edit?CoUsuario=${usuario.CoUsuario}'/>">Editar usuario</a></td>
                <td><a href="<c:url value='/pages/usuarios/delete?CoUsuario=${usuario.CoUsuario}'/>" onclick="if (deleteUsuario()) { return true; } else { return false; }">Eliminar usuario</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
