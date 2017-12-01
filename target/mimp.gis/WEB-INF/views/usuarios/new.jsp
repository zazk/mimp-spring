<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
    <h1>Nuevo Usuario</h1><br/><br/>
    <form:form action="new" method="post" commandName="persona"  cssClass="form-horizontal">
        <div class="row">
            <div class="control-group">
                <label>Tipo de Usuario:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Nombres:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                 <label>Apellidos:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                 <label>Sexo:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Tipo Documento:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Numero Documento:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Fecha de Nacimiento:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Telefono:</label>
                <div class="controls">
                    <form:input path="NoUsuario"></form:input>
                    <form:errors path="NoUsuario" cssstyle="color:red"></form:errors>
                </div>
                
                
            </div>
            
            <div class="control-group">
                <label>E-Mail:</label>
                <div class="controls">
                    <form:input path="login"></form:input>
                    <form:errors path="login" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Confirmar-Mail:</label>
                <div class="controls">
                    <form:input path="login"></form:input>
                    <form:errors path="login" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Contraseña:</label>
                <div class="controls">
                    <form:input path="login"></form:input>
                    <form:errors path="login" cssstyle="color:red"></form:errors>
                </div>
                
                <label>Confirmar Contraseña:</label>
                <div class="controls">
                    <form:input path="login"></form:input>
                    <form:errors path="login" cssstyle="color:red"></form:errors>
                </div>
                
            </div>
                
        </div>
        <div class="actions" style="margin-left: 160px;">
            <input name="" value="Crear Usuario" type="submit" class="btn btn-primary">            
             <a href="<c:url value='/pages/usuarios/login'/>" class="btn btn-primary">Cancelar</a>   
             
        </div>                
    </form:form>
</div>
