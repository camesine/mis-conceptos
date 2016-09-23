<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"  />
        <title>Preguntame.cl</title>

        <script type="text/javascript" >

            $(document).ready(function() {

                $(document).keypress(function(e) {

                    if (e.which == 13) {


                        var datos = {
                            correo: $('#TxtCorreoLogin').val(),
                            pass: $('#TxtPassLogin').val()
                        }


                        
                         $.ajax({
                         type: 'POST',
                         url: '<c:url value="/usuario/login" />',
                         data: datos,
                         beforeSend: function() {
                         console.log("cargando...");
                         }
                         }).success(function(response) {
                         
                         if (response == "true") {
                         
                         $(location).attr('href', '<c:url value="/usuario/plataforma" />');
                         } else {
                         alert("ERROR")
                         }
                         
                         
                         });


                    }
                });


                $('#BtnRegistrar').click(function() {

                    var datos = {
                        correo: $('#TxtCorreoNuevo').val(),
                        nombre: $('#TxtNombreNuevo').val(),
                        apellido: $('#TxtApellidoNuevo').val(),
                        pass: $('#TxtPassNuevo').val(),
                    }

                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/usuario/nuevo" />',
                        data: datos,
                    })
                            .success(function(response) {

                                alert(response);

                            });
                })

                /*
                 $('#BtnLogin').click(function() {
                 
                 var datos = {
                 correo: $('#TxtCorreoLogin').val(),
                 pass: $('#TxtPassLogin').val()
                 }
                 
                 $.ajax({
                 type: 'POST',
                 url: '<c:url value="/usuario/login" />',
                 data: datos,
                 })
                 .success(function(response) {
                 
                 
                 if (response == "true") {
                 $(location).attr('href', '<c:url value="/usuario/plataforma" />');
                 } else {
                 alert("ERROR")
                 }
                 
                 
                 });
                 })
                 */


            });

        </script>


    </head>

    <body>
        ${correo}
    <header>
        <ul>
            <li><a href="#">INICIO</a></li>
            <li><a href="#">NOSOTROS</a></li>
            <li><a href="#">CONTACTO</a></li>
            <ul style="float:right;list-style-type:none;">
                <li><a class="active" href="#">TUTORIAL</a></li>
            </ul>
        </ul>
    </header>

    <div id="Contenedor">


        <section id="inicio" >
            
            <article id="welcome">
                <h1>MIS CONCEPTOS</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique leo vitae dui ullamcorper scelerisque. Vestibulum vitae metus cursus, tempor urna sit amet, dapibus augue. Donec interdum porta auctor. Duis malesuada arcu nec risus elementum convallis. Maecenas a pharetra odio.</p>
            </article>
            <article id="login">

                <div id="Entrar" >

                    <h1>INICIAR SESION</h1>

                    <p><input type="text" name="CorreoLogin" id="TxtCorreoLogin" value="" placeholder="Username or Email"></p>
                    <p><input type="password" name="PassLogin" id="TxtPassLogin" value="" placeholder="Password"></p>
                    <p class="remember_me">
                        <label>
                            <input type="checkbox" name="remember_me" id="remember_me">
                            Remember me on this computer
                        </label>
                    </p>
                    <p class="submit"><input type="submit" id="BtnLogin" name="commit" value="Entrar"></p>



                </div>
                <br />
                <div id="Registrar" >

                    <h1>REGISTRAR</h1>

                    <p><input type="text" name="TxtCorreoNuevo" id="TxtCorreoNuevo" value="" placeholder="Email"></p>
                    <p><input type="text" name="TxtNombreNuevo" id="TxtNombreNuevo" value="" placeholder="Nombre"></p>
                    <p><input type="text" name="TxtApellidoNuevo" id="TxtApellidoNuevo" value="" placeholder="Apellido"></p>
                    <p><input type="password" name="TxtPassNuevo" id="TxtPassNuevo"  value="" placeholder="Contraseña"></p>
                    <p><input type="password" name="TxtPassNuevo2" id="TxtPassNuevo2" value="" placeholder="Confirmacion contraseña"></p>

                    <p class="submit"><input type="submit" id="BtnRegistrar" name="commit" value="Listo"></p>



                </div>

            </article>

        </section>



    </div>

    <footer>
        <h1>PREGUNTAME.CL</h1>
    </footer>


</body>
</html>
