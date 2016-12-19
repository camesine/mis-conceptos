<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"  />
        <title>PERFIL</title>

        <script type="text/javascript" >
            $(document).ready(function(e) {

                $("#BtnEditarUsuario").on("click", function() {


                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/usuario/editar" />',
                        data: {id: ${usuario.id}, correo: $("#TxtCorreo").val(), nombre: $("#TxtNombre").val(), apellido: $("#TxtApellido").val(), pass: $("#TxtPass").val()}
                    }).success(function(response) {

                        if (response == "false") {

                            $("#MensajeError").css("display", "block");
                        } else {
                            $(location).attr("href", "<c:url value="/usuario/perfil" />");
                        }

                    });
                })

                $('#CerrarError').click(function() {

                    $('#MensajeError').fadeOut("fast", function() {
                    });

                });

            });

            function EliminarContenido(idContenido) {

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/contenido/eliminar" />',
                    data: {id: idContenido}
                }).success(function(response) {
                    if (response == "true") {
                        $("#" + idContenido).remove();
                    }

                });
            }

            function EditarContenido(idContenido) {

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/contenido/editarNombre" />',
                    data: {nombre: $('#contenidos').find("input[id=" + idContenido + "]").val(), id: idContenido, usuario_id: ${usuario.id}},
                })
                        .success(function(response) {
                            console.log(response);
                        });

            }

            function VerInforme(idContenido) {
                window.open("<c:url value="/contenido/informe" /> " + "?contenido=" + idContenido, "_blank");
            }


        </script>


    </head>

    <body>

    <header>
        <ul>
            <li><a href="<c:url value="/usuario/plataforma" />">INICIO</a></li>
           
            <ul style="float:right;list-style-type:none;">
                <li><a href="#">TUTORIAL</a></li>
                <li><a class="active" href="logout">CERRAR SESION</a></li>
            </ul>
        </ul>
    </header>

    <div id="Contenedor">

        <section id="datosUsuario" >
            <h1>DATOS PERSONALES</h1>
            <form>

                <div id="LblForm" ><label>CORREO ELECTRONICO</label></div>
                <input type='text' name='TxtCorreo' id='TxtCorreo' value='${usuario.correo}' placeholder='Email'>
                <div id="LblForm" ><label>NOMBRE</label></div>
                <input type="text" name="TxtNombre" id="TxtNombre" value="${usuario.nombre}" placeholder="Nombre">
                <div id="LblForm" ><label>APELLIDO</label></div>
                <input type="text" name="TxtApellido" id="TxtApellido" value="${usuario.apellido}" placeholder="Apellido">
                <div id="LblForm" ><label>CONTRASEÑA</label></div>
                <input type="password" name="TxtPass" id="TxtPass"  value="" placeholder="Contraseña">

            </form>
            <input type="submit" id="BtnEditarUsuario" name="commit" value="CONFIRMAR">

        </section>

        <section id="contenidos" >
            <h1>MIS CONTENIDOS</h1>
            <table>

                <c:forEach items="${usuario.contenidos}" var="contenido">

                    <tr id="${contenido.id}" ><td class="TDnombreContenido"><input type="text" id="${contenido.id}" onblur="EditarContenido(${contenido.id})" value="${contenido.nombre}" /></td><td><button onclick="VerInforme(${contenido.id})" >INFORME</button></td><td></td><td><button onclick="EliminarContenido(${contenido.id})" >ELIMINAR</button></td></tr>

                </c:forEach>

            </table>

        </section>
        <div id="MensajeError"><div id="TusResultados">ERROR</div><div id="CerrarError" >X</div>
            <p></p>
        </div>

    </div>


    <footer class="footer-distributed">
        <div class="footer-left">

            <h3>Company<span>logo</span></h3>

            <p class="footer-links">
                <a href="#">INICIO</a>
                ·
                <a href="#">PERFIL</a>
                ·
                <a href="#">NOSOTROS</a>
                ·
                <a href="#">CERRAR SESION</a>

            </p>

            <p class="footer-company-name">Mis conceptos&copy; 2016</p>



        </div>

        <div class="footer-right">

            <p>Contacto</p>

              <div id="contacto_form" >

            <input type="text" id="email_contacto" placeholder="Email" value="${correo}"  />
            <textarea id="mensaje_contacto" placeholder="Mensaje"></textarea>
            <button id="EnviarContacto">ENVIAR</button>
            <div id="Enviado" ><p>Envio exitoso!</p></div>
        </div>

        </div>

    </footer>



</body>
</html>
