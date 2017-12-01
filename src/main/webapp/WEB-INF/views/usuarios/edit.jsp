<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
    <h1>Editar Usuario</h1><br/><br/>
    <form:form action="edit" method="post" commandName="usuario" cssClass="form-horizontal">
        <div class="row">
            <form:hidden path="CoUsuario"></form:hidden>
            <div class="control-group">
                <label>Nombre:</label>
                <div class="controls">
                    <form:input path="nousuario"></form:input>
                    <form:errors path="nousuario" cssstyle="color:red"></form:errors>
                </div>
            </div>
            <div class="control-group">
                <label>Login:</label>
                <div class="controls">
                    <form:input path="login"></form:input>
                    <form:errors path="login" cssstyle="color:red"></form:errors>
                </div>
            </div>
                
        </div>
        <div class="actions" style="margin-left: 160px;">
            <input name="" value="Editar" type="submit" class="btn btn-primary">
            <a style="margin-left: 5px;" href="<c:url value='/pages/usuarios/index'/>">Cancelar</a>
        </div>
    </form:form>
</div>
