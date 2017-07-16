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
        <title>MIS CONCEPTOS</title>

        <script type="text/javascript" >

            // Load the SDK asynchronously
            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));



//MIS CONCEPTOS
            /*
             function SeleccionOpcion(id) {
             
             
             
             
             
             
             
             $("#" + id).find('.Form').slideToggle();
             
             
             $('#login .FormOpcion').each(function(index, element) {
             if ($(this).attr('id') != $("#" + id).attr('id')) {
             
             $(this).find('.Form').slideToggle();
             }
             });
             
             
             }
             */

            $(document).ready(function() {


                var app_id = "1829950200593943";
                var scopes = 'public_profile,email';

                window.fbAsyncInit = function() {
                    FB.init({
                        appId: app_id,
                        status: true,
                        cookie: true,
                        xfbml: true,
                        version: 'v2.8'
                    });



                    FB.getLoginStatus(function(response) {
                        statusChangeCallback(response, function() {

                        });
                    });

                };

                var statusChangeCallback = function(response, callback) {
                    console.log('statusChangeCallback');
                    console.log(response);

                    if (response.status === 'connected') {
                        getFacebookData();
                    } else {
                        callback(false);
                    }
                }

                var checkLoginState = function(callback) {
                    FB.getLoginStatus(function(response) {
                        statusChangeCallback(response, function(data) {
                            callback(data);
                        });
                    });
                }

                var getFacebookData = function() {
                    FB.api('/me', {locale: 'en_US', fields: 'name, email'}, function(response) {


                        $.ajax({
                            type: 'POST',
                            url: '<c:url value="/usuario/loginFace" />',
                            data: response, beforeSend: function() {
                                $('#Contenedor').append("<div id='cargando'><img id='ImgCargando'  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>");
                            }
                        })
                                .success(function(response) {


                                    if (response == "true") {
                                        $(location).attr('href', '<c:url value="/usuario/plataforma" />');
                                    } else {
                                        $('#Contenedor #cargando').remove();
                                        $("#MensajeError").css("display", "block");

                                    }


                                });








                    })
                }

                var facebookLogin = function() {
                    checkLoginState(function(response) {
                        if (!response) {
                            FB.login(function(response) {
                                if (response.status === 'connected') {
                                    getFacebookData();
                                }
                            }, {scope: scopes});
                        }
                    });
                }


                $('#LoginFace').click(function() {
                    facebookLogin();
                });


                $("#Registrar .Form").css("display", "none")

                $('.message a').click(function() {
                    $('#login .form div').animate({height: "toggle", opacity: "toggle"}, "slow");
                });

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
                                    $('#Contenedor #cargando').remove();
                                    $("#MensajeError").css("display", "block");

                                }


                            });
                })




                $('#CerrarError').click(function() {

                    $('#MensajeError').fadeOut("fast", function() {
                    });

                });




            });

        </script>


    </head>

    <body>
        ${correo}
    <header>
        <ul>
            <li><a href="index">INICIO</a></li>
            <li><a href="<c:url value="/plataforma/nosotros" />">QUIENES SOMOS</a></li>
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
                <p>Plataforma de estudio, que permite a los alumnos y alumnas de cualquier área o nivel educativo, esquematizar la información explícita en el material teórico de estudio que considere relevante, mediante la asociación de conceptos y las características de cada uno de estos, para posteriormente poner a prueba los conocimientos del alumno mediante evaluaciones y que este logre determinar si no ha estudiado lo suficiente, o si su método de estudio no le está siendo productivo. </p>    
            </article>

            <article id="login">
                <div class="form" style="padding: 45px;box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);" >
                    <div class="register-div" >
                    <form class="register-form">
                    
                 
                            <input type="text" name="TxtCorreoNuevo" id="TxtCorreoNuevo" value="" placeholder="Email">
               
                            <input type="text" name="TxtNombreNuevo" id="TxtNombreNuevo" value="" placeholder="Nombre">
                 
                            <input type="text" name="TxtApellidoNuevo" id="TxtApellidoNuevo" value="" placeholder="Apellido">
                
                            <input type="password" name="TxtPassNuevo" id="TxtPassNuevo"  value="" placeholder="Contraseña">
                  
                            <input type="password" name="TxtPassNuevo2" id="TxtPassNuevo2" value="" placeholder="Confirmacion contraseña">

                      
                        
                    </form>
                       <!--   <button  id="BtnRegistrar" >REGISTRAR</button> -->
                        <input type="submit" id="BtnRegistrar" name="commit" value="REGISTRAR">
                        <p class="message">Already registered? <a href="#">Sign In</a></p>
                        </div>
              <!--      <form>
                      
                        <input type="text" name="CorreoLogin" id="TxtCorreoLogin" value="" placeholder="E-mail">
                      
                        <input type="password" name="PassLogin" id="TxtPassLogin" value="" placeholder="******">
                       <button id="BtnLogin" >ENTRAR</button> 
                        <button>FACEBOOK</button>
                        <p class="message">Not registered? <a href="#">Create an account</a></p>
                    </form> -->
              <div class="login-div" >
                            <form class="login-form" >
                           
                            <input type="text" name="CorreoLogin" id="TxtCorreoLogin" value="" placeholder="E-mail">
                          
                            <input type="password" name="PassLogin" id="TxtPassLogin" value="" placeholder="******">
                        </form>
                        <input type="submit" id="BtnLogin" name="commit" value="ENTRAR">
                        <button id="LoginFace" ><img style="height: 46px" src='<c:url value="/resources/imagenes/facebook.png" />' /></button>
                        <p class="message">Not registered? <a href="#">Create an account</a></p>
                        
                   </div>
            
                </div>
                <!--
                <button id="LoginFace" >Con feibu</button>

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
                -->
            </article>





        </section>


        <div id="MensajeError"><div id="TusResultados">ERROR</div><div id="CerrarError" >X</div>
            <p></p>
        </div>
    </div>
    <footer class="footer-distributed">
        <div class="footer-left">

            <h3>Mis<span>conceptos</span></h3>

            <p class="footer-links">
                <a href="#">INICIO</a>
                ·

                <a href="#">NOSOTROS</a>

            </p>

            <p class="footer-company-name">Mis conceptos&copy; 2016</p>



        </div>

        <div class="footer-right">

            <p>Contacto</p>

              <div id="contacto_form" >

            <input type="text" id="email_contacto" placeholder="Email"/>
            <textarea id="mensaje_contacto" placeholder="Mensaje"></textarea>
            <button id="EnviarContacto">ENVIAR</button>
            <div id="Enviado" ><p>Envio exitoso!</p></div>
        </div>

        </div>

    </footer>


</body>
</html>
