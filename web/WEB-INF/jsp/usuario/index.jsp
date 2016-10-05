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

            function SeleccionOpcion(id) {


                $("#" + id).find('.Form').slideToggle();


                $('#login .FormOpcion').each(function(index, element) {
                    if ($(this).attr('id') != $("#" + id).attr('id')) {

                        $(this).find('.Form').slideToggle();
                    }
                });


            }


            $(document).ready(function() {

                $("#Registrar .Form").css("display", "none")

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
                                $('#Contenedor').append("<div id='cargando'><img id='ImgCargando'  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>");
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


                $('#BtnLogin').click(function() {

                    var datos = {
                        correo: $('#TxtCorreoLogin').val(),
                        pass: $('#TxtPassLogin').val()
                    }

                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/usuario/login" />',
                        data: datos, beforeSend: function() {
                            $('#Contenedor').append("<div id='cargando'><img id='ImgCargando'  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>");
                        }
                    })
                            .success(function(response) {


                                if (response == "true") {
                                    $(location).attr('href', '<c:url value="/usuario/plataforma" />');
                                } else {
                                    alert("ERROR")
                                }


                            });
                })



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

<!-- 
<div id="cargando"><img id="ImgCargando"  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>
         -->
        <section id="inicio" >

            <article id="welcome">
                <h1>MIS CONCEPTOS</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tristique leo vitae dui ullamcorper scelerisque. Vestibulum vitae metus cursus, tempor urna sit amet, dapibus augue. Donec interdum porta auctor. Duis malesuada arcu nec risus elementum convallis. Maecenas a pharetra odio.</p>
            </article>

            <article id="login">
                <div id="Entrar" class="FormOpcion" >
                    <div id="BtnFormEntrar"  onclick="SeleccionOpcion('Entrar')" ><h1>INICIAR SESION</h1></div>
                    <div class="Form">
                        <form>
                            <div id="LblForm" ><label>CORREO ELECTRONICO</label></div>
                            <input type="text" name="CorreoLogin" id="TxtCorreoLogin" value="" placeholder="E-mail">
                                <div id="LblForm" ><label>CONTRASEÑA</label></div>
                            <input type="password" name="PassLogin" id="TxtPassLogin" value="" placeholder="******">
                        </form>
                        <input type="submit" id="BtnLogin" name="commit" value="ENTRAR">



                    </div>
                </div>
                <div id="Registrar" class="FormOpcion" >
                    <div id="BtnFormRegistrar"  onclick="SeleccionOpcion('Registrar')" ><h1>REGISTRO</h1></div>
                    <div class="Form" >

                        <form>
                            
                            <div id="LblForm" ><label>CORREO ELECTRONICO</label></div>
                        <input type="text" name="TxtCorreoNuevo" id="TxtCorreoNuevo" value="" placeholder="Email">
                        <div id="LblForm" ><label>NOMBRE</label></div>
                        <input type="text" name="TxtNombreNuevo" id="TxtNombreNuevo" value="" placeholder="Nombre">
                        <div id="LblForm" ><label>APELLIDO</label></div>
                        <input type="text" name="TxtApellidoNuevo" id="TxtApellidoNuevo" value="" placeholder="Apellido">
                        <div id="LblForm" ><label>CONTRASEÑA</label></div>
                        <input type="password" name="TxtPassNuevo" id="TxtPassNuevo"  value="" placeholder="Contraseña">
                        <div id="LblForm" ><label>CONFIRMAR CONTRASEÑA</label></div>
                        <input type="password" name="TxtPassNuevo2" id="TxtPassNuevo2" value="" placeholder="Confirmacion contraseña">

                        </form>
                        <input type="submit" id="BtnRegistrar" name="commit" value="REGISTRAR">



                    </div>

                </div>

            </article>





        </section>



    </div>

    <footer>
        <h1>PREGUNTAME.CL</h1>
    </footer>


</body>
</html>
