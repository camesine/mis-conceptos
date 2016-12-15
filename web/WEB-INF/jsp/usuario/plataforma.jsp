<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"  />

        <script src="<c:url value="/resources/js/tinymce/tinymce.min.js" />"></script>
        <script src="<c:url value="/resources/js/tinymce/init-tinymce.js" />"></script>

        <script src="<c:url value="/resources/js/highcharts.js" />"></script>
        <script src="<c:url value="/resources/js/exporting.js" />"></script>



        <link rel="stylesheet" href="<c:url value="/resources/css/material-design-iconic-font.min.css" />"  />
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery.circliful.css" />"  />
        <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

        <script src="<c:url value="/resources/js/jquery.circliful.js" />"></script>

        <script type="text/javascript">
            mxBasePath = "<c:url value="/resources/mxGraph" />";
        </script>


        <script src="<c:url value="/resources/mxGraph/js/mxClient.js" />"></script>



        <title>MisConceptos.cl</title>

        <script type="text/javascript">

            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));

            function Cconcepto(id) {


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/definicion/buscar" />',
                    data: {concepto_id: id}
                }).success(function(response) {

                    def = "def";
                    div = $("#" + id).attr('id');
                    $("#" + id).find(".def *").remove();
                    for (var i = 0; i < response.length; i++) {

                        $("#" + id).find(".def").append(" <input type='text' onblur='EditarDefinicion(" + response[i].id + ", " + div + ")' id='" + response[i].id + "' style='float:left'  value='" + response[i].detalle + "'/><button style='float:left' onClick='EliminarDef(" + response[i].id + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");
                    }
                });

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/caracteristica/buscar" />',
                    data: {concepto_id: id}
                }).success(function(response) {

                    Car = "Car";
                    div = $("#" + id).attr('id');
                    $("#" + id).find(".Car *").remove();
                    for (var i = 0; i < response.length; i++) {

                        $("#" + id).find(".Car").append(" <input type='text' onblur='EditarCaracteristica(" + response[i].id + ", " + div + ")' id='" + response[i].id + "' style='float:left'  value='" + response[i].detalle + "'/><button style='float:left' id='" + response[i].id + "' onClick='EliminarCar(" + response[i].id + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");
                    }
                });

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/observacion/buscar" />',
                    data: {concepto_id: id}
                }).success(function(response) {

                    Obs = "Obs";
                    div = $("#" + id).attr('id');
                    $("#" + id).find(".Obs *").remove();
                    for (var i = 0; i < response.length; i++) {

                        $("#" + id).find(".Obs").append(" <input type='text' onblur='EditarObservacion(" + response[i].id + ", " + div + ")' id='" + response[i].id + "' style='float:left'  value='" + response[i].detalle + "'/><button style='float:left' id='" + response[i].id + "' onClick='EliminarObs(" + response[i].id + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");
                    }
                });


                $("#" + id).find(".rel *").remove();
                rel = "rel";
                div = $("#" + id).attr('id');


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/tiporelacion/buscar" />',
                }).success(function(response) {


                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/relacion/buscar" />',
                        data: {concepto_id: id}
                    }).success(function(relaciones) {

                        ERelacion = "";

                        for (var i = 0; i < relaciones.length; i++) {
                            opcion = "";
                            combo = "";
                            tipo = "";
                            id_relacion = "";
                            for (var j = 0; j < response.length; j++) {

                                if (relaciones[i].tipoRelacion.id == response[j].id) {
                                    opcion = opcion + ("<option selected value=" + response[j].id + ">" + response[j].nombre + "</option>");

                                } else {
                                    opcion = opcion + ("<option  value=" + response[j].id + ">" + response[j].nombre + "</option>");
                                }
                            }
                            ERelacion = ERelacion + "<select onchange='EditarRelacion1(" + relaciones[i].id + "," + id + ")' id=" + relaciones[i].id + " style='float:left'>" + opcion + "</select>";
                            id_relacion = relaciones[i].id;
                            $('#Conceptos div .titulo').each(function(index, element) {

                                if (relaciones[i].conceptoByConcepto2.id == $(this).parent().attr('id')) {
                                    combo = combo + "<option selected value=" + $(this).parent().attr('id') + ">" + $(this).text() + " </option>";

                                } else {
                                    combo = combo + "<option  value=" + $(this).parent().attr('id') + ">" + $(this).text() + " </option>";
                                }
                            });

                            ERelacion = ERelacion + "<select onchange='EditarRelacion2(" + relaciones[i].id + "," + id + ")' >" + combo + "</select> ";

                            ERelacion = ERelacion + "<button onClick='EliminarRel(" + id_relacion + ")' style='float:right' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>";
                        }
                        $("#" + div).find(".rel").html(ERelacion);
                    });





                });



                $("#" + id).find('ul').slideToggle();

                $('#Conceptos .Concepto').each(function(index, element) {
                    if ($(this).attr('id') != $("#" + id).attr('id')) {

                        $(this).find('ul').slideUp();
                    }
                });



            }



            function EliminarC(id) {
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/concepto/eliminar" />',
                    data: {id: id},
                })
                        .success(function(response) {
                            $('#Conceptos #' + id).children().remove();
                            $('#Conceptos #' + id).remove();

                        });

            }

            function EliminarDef(id) {



                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/definicion/eliminar" />',
                    data: {id: id},
                })
                        .success(function(response) {
                            $('.def').find("input[id=" + id + "]").next().remove()
                            $('.def').find("input[id=" + id + "]").remove();
                        });



            }

            function EliminarCar(id) {
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/caracteristica/eliminar" />',
                    data: {id: id},
                })
                        .success(function(response) {
                            $('.Car').find("input[id=" + id + "]").next().remove()
                            $('.Car').find("input[id=" + id + "]").remove();
                        });


            }


            function EliminarObs(id) {
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/observacion/eliminar" />',
                    data: {id: id},
                })
                        .success(function(response) {
                            $('.Obs').find("input[id=" + id + "]").next().remove()
                            $('.Obs').find("input[id=" + id + "]").remove();
                        });

            }


            function EliminarRel(id) {


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/relacion/eliminar" />',
                    data: {id: id},
                })
                        .success(function(response) {
                            $('.rel').find("select[id=" + id + "]").next().next().remove();
                            $('.rel').find("select[id=" + id + "]").next().remove();
                            $('.rel').find("select[id=" + id + "]").remove();
                        });



            }


            function SeleccionarContenido(x) {

                $('#Conceptos div').remove();
                $('#TxtMateria').val("")
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/contenido/buscar" />',
                    data: {seleccionado: x},
                })
                        .success(function(response) {
                            $('#seleccion').text(response.nombre.toUpperCase());
                            $('#TxtMateria').val(response.texto);
                            if (response.texto == null) {
                                tinymce.activeEditor.setContent("Ingresa el contenido en estudio...");

                            } else {
                                tinymce.activeEditor.setContent(response.texto);

                            }


                            $('#Contenido').attr("value", response.id);
                        });



                var etiqueta = "";
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/concepto/buscar" />',
                    data: {seleccionado: x},
                })
                        .success(function(response) {

                            for (var i = 0; i < response.length; i++) {

                                $('#Conceptos').prepend("<div id=" + response[i].id + " class='Concepto' ><div style='float:left' class='titulo' onclick='Cconcepto(" + response[i].id + ")'>" + response[i].nombre.toUpperCase() + "</div><button onClick='EliminarC(" + response[i].id + ")' class='EliminarC' ><img src='<c:url value="/resources/imagenes/error.png" />' /> </button>  <ul style='display:none'><li><input type='button' class='AgregarDefinicion' onclick='AgregarDef(" + response[i].id + ")' value='DEFINICION' /> </li><div class='def'> </div><li><input type='button' class='AgregarCaracteristica' onclick='AgregarCar(" + response[i].id + ")' value='CARACTERISTICA' /> </li><div class='Car'> </div></li> <li><input type='button' onclick='AgregarObs(" + response[i].id + ")' class='AgregarObservacion' value='OBSERVACION' /></li><div class='Obs'> </div><li><input type='button' onclick='AgregarRel(" + response[i].id + ")' class='AgregarRelacion' value='RELACION' /></li><div class='rel'> </div> </ul> </div>");
                            }

                        });

                $('#VentanaBloqueo').fadeOut("fast", function() {
                });

            }


            function EditarDefinicion(id, concepto_id) {


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/definicion/editar" />',
                    data: {id: id, detalle: $('.def').find("input[id=" + id + "]").val(), concepto_id: concepto_id},
                })
                        .success(function(response) {

                        });

            }

            function EditarCaracteristica(id, concepto_id) {


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/caracteristica/editar" />',
                    data: {id: id, detalle: $('.Car').find("input[id=" + id + "]").val(), concepto_id: concepto_id},
                })
                        .success(function(response) {

                        });

            }


            function EditarObservacion(id, concepto_id) {


                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/observacion/editar" />',
                    data: {id: id, detalle: $('.Obs').find("input[id=" + id + "]").val(), concepto_id: concepto_id},
                })
                        .success(function(response) {

                        });

            }



            function EditarRelacion1(IdRelacion, Concepto1) {
                console.log(IdRelacion);
                console.log(Concepto1);

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/relacion/editar" />',
                    data: {IdRelacion: IdRelacion, IdConcepto1: Concepto1, IdConcepto2: $(".rel  #" + IdRelacion).next().find('option:selected').val(), IdTipo: $(".rel  #" + IdRelacion).find('option:selected').val()}
                })
                        .success(function(response) {

                        });

            }

            function EditarRelacion2(IdRelacion, Concepto1) {
                console.log(IdRelacion);
                console.log(Concepto1);
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/relacion/editar" />',
                    data: {IdRelacion: IdRelacion, IdConcepto1: Concepto1, IdConcepto2: $(".rel  #" + IdRelacion).next().find('option:selected').val(), IdTipo: $(".rel  #" + IdRelacion).find('option:selected').val()}
                })
                        .success(function(response) {

                        });

            }

            function EditarContenido(Contenido) {
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/contenido/editar" />',
                    data: {id: $('#Contenido').attr('value'),
                        nombre: $('#seleccion').text(),
                        texto: Contenido,
                        usuario_id: ${usuario.id}
                    },
                })
                        .success(function(response) {

                        });
            }



            function EditorOk(selector) {

                $("#" + selector).val($("#TxtEditor").val());

                $('#Editor').fadeOut("fast", function() {
                });
                $('#Editor').find("#EditarOK").remove();
            }






        </script>

        <script type="text/javascript">

            // Program starts here. Creates a sample graph in the
            // DOM node with the specified ID. This function is invoked
            // from the onLoad event handler of the document (see below).
            function MapaConceptual(container, relaciones)
            {
                // Checks if browser is supported
                if (!mxClient.isBrowserSupported())
                {
                    // Displays an error message if the browser is
                    // not supported.
                    mxUtils.error('Browser is not supported!', 200, false);
                }
                else
                {
                    // Creates the graph inside the given container
                    var graph = new mxGraph(container);

                    // Adds rubberband selection
                    new mxRubberband(graph);

                    // Changes the default vertex style in-place
                    var style = graph.getStylesheet().getDefaultVertexStyle();
                    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
                    style[mxConstants.STYLE_GRADIENTCOLOR] = 'white';
                    style[mxConstants.STYLE_PERIMETER_SPACING] = 0;
                    style[mxConstants.STYLE_ROUNDED] = false;
                    style[mxConstants.STYLE_SHADOW] = false;




                    style = graph.getStylesheet().getDefaultEdgeStyle();
                    style[mxConstants.STYLE_ROUNDED] = false;


                    // Creates a layout algorithm to be used
                    // with the graph
                    var layout = new mxHierarchicalLayout(graph);
                    var organic = new mxFastOrganicLayout(graph);
                    organic.forceConstant = 120;

                    var parent = graph.getDefaultParent();

                    // Adds a button to execute the layout
                    var button = document.createElement('button');
                    mxUtils.write(button, 'Hierarchical');
                    mxEvent.addListener(button, 'click', function(evt)
                    {
                        layout.execute(parent);
                    });
                    //container.append(button);

                    // Adds a button to execute the layout
                    var button = document.createElement('button');
                    mxUtils.write(button, 'Organic');

                    mxEvent.addListener(button, 'click', function(evt)
                    {
                        organic.execute(parent);
                    });

                    //	$(container).append(button);

                    // Load cells and layouts the graph
                    graph.getModel().beginUpdate();
                    try
                    {
                        eval(relaciones);
                        // Executes the layout
                        layout.execute(parent);
                    }
                    finally
                    {
                        // Updates the display
                        graph.getModel().endUpdate();
                    }

                    if (mxClient.IS_QUIRKS)
                    {
                        document.body.style.overflow = 'hidden';
                        new mxDivResizer(container);
                    }
                }
            }
            ;
        </script>




        <script type="text/javascript">



            $(document).ready(function(e) {

                localStorage.clear();

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
                    // console.log('statusChangeCallback');
                    // console.log(response);

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




                var FacebookLogout = function() {
                        
                        
                        
                        
                       FB.login(function(response){
                     if(response.status === 'connected' || response.status === "unknown"){
                     console.log(response)
                     FB.logout(function(response){
                     $(location).attr('href', '<c:url value="/usuario/logout" />');
                     })   
                     }  
                     }); 
                
                 
                 
               


                }

                $('#BtnNuevoContenido').click(function() {

                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/contenido/nuevo" />',
                        data: {nombre: $('#TxtNuevaMateria').val(), usuario_id: ${usuario.id}},
                    })
                            .success(function(response) {

                                $('#ListaContenidos').prepend("<li><a id=" + response + "  onclick='SeleccionarContenido(" + response + ")' >" + ($('#TxtNuevaMateria').val()).toUpperCase() + "</a></li>");

                            });


                });

                $("#b3").click(function() {


                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/relacion/relacionConceptual" />',
                        data: {contenido_id: $('#Contenido').attr('value')},
                    })
                            .success(function(response) {
                                $("#MapaConceptual").find("svg").remove();
                                MapaConceptual(document.getElementById('MapaConceptual'), response);

                                $("#ventanaMapa").fadeIn("fast", function() {
                                });
                            });


                });


                $("#b2").click(function() {
                    window.open("<c:url value="/contenido/informe" /> " + "?contenido=" + $('#Contenido').attr('value'), "_blank");
                });





// GENERACION DE PREGUNTAS

                $("#ResultadosMenu #RepetirTest").click(function() {

                    $("#CerrarResultados").click();
                    $("#b1").click();
                });

                $("#b1").click(function() {

                    localStorage.clear();
                    $("#BtnSiguente").val("SIGUIENTE");
                    $('#Contenedor').append("<div id='cargando'><img id='ImgCargando'  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>");


                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/contenido/preguntas" />',
                        data: {seleccionado: $('#Contenido').attr('value')}, beforeSend: function() {


                        }
                    })
                            .success(function(response) {
                                var etiqueta = "";

                                for (var i = 0; i < response.length; i++) {
                                    etiqueta = etiqueta + "<div class='pregunta'><input type='hidden' class='TipoPregunta' value='SeleccionMultiple' /><input type='hidden' class='ConceptoReferenciado' value='" + response[i].conceptoReferencia + "' /><p id='Enunciado'>" + response[i].enunciado + "</p><input type='hidden' class='respuesta' value='" + response[i].respuesta + "' /> <ul class='Alternativas' ><li class='opcion'><p class='tooltip' >" + response[i].opcion1 + "<span>" + response[i].opcion1 + "</span></p></li><li  class='opcion'><p class='tooltip' >" + response[i].opcion2 + "<span>" + response[i].opcion2 + "</span></p></li><li  class='opcion'><p class='tooltip'>" + response[i].opcion3 + "<span>" + response[i].opcion3 + "</span></p></li><li  class='opcion'><p class='tooltip' >" + response[i].opcion4 + "<span>" + response[i].opcion4 + "</span></p></li></ul></div>";
                                }


                                $('#Preguntas').html(etiqueta);


                                var etiqueta = "";
                                var posicion = null;


                                $('#Preguntas .pregunta .Alternativas').each(function(index, element) {

                                    var alternativas = $(this);
                                    $(alternativas).find("li").each(function(index, element) {

                                        etiqueta = $(this).html();
                                        var contador = 0;


                                        $(alternativas).find("li").each(function(index, element) {

                                            if (etiqueta == $(this).html()) {
                                                contador = contador + 1;

                                            }

                                        });

                                        if (contador > 1) {
                                            $(this).remove();
                                        }

                                    });


                                });


                                $('#PuntajeTotal').attr("value", response.length)



                                $.ajax({
                                    type: 'POST',
                                    url: '<c:url value="/contenido/preguntasTerminosPareados" />',
                                    data: {seleccionado: $('#Contenido').attr('value')},
                                })
                                        .success(function(response) {
                                            $('#Preguntas').append(response);



                                            //COMPLETACION

                                            $.ajax({
                                                type: 'POST',
                                                url: '<c:url value="/contenido/preguntasCompletacion" />',
                                                data: {seleccionado: $('#Contenido').attr('value')},
                                            })
                                                    .success(function(response) {
                                                        $('#Preguntas').append(response);


                                                        CantidadTotal = 0;
                                                        $('#Preguntas .pregunta').each(function(index, element) {
                                                            CantidadTotal = CantidadTotal + 1;

                                                        });
                                                        i = 0;
                                                        $('#Preguntas .pregunta').each(function(index, element) {
                                                            i = i + 1;
                                                            $(this).prepend("<p id='cantidad'>" + (i) + "/" + CantidadTotal + "</p>")

                                                        });




                                                        $('#Preguntas').children(":first-child").css("display", "block");


                                                        $('.ventana').fadeIn("fast", function() {
                                                        });

                                                        $('#dialogo').find(".close").after(("<h1 id='TituloTest'>TEST DE " + $('#seleccion').text() + "</h1> "));
                                                        $('#Puntaje').attr("value", "0");

                                                        $('#Contenedor #cargando').remove();

                                                        $('#Preguntas .pregunta ul li p').each(function(index, element) {
                                                            if ($(this).text().length / 2 < 185) {
                                                                $(this).removeClass();
                                                                $(this).find("span").remove();

                                                            }
                                                        });

                                                    });




                                        });




                            });











                });

                $('body #art1').on('dblclick', 'input[type=text]', function() {

                    $("#TxtEditor").val($(this).val());
                    $("#Editor").css("display", "block");
                    $("#Editor").append("<input type='button' onclick='EditorOk(" + $(this).attr("id") + ")' class='BtnGenerar' id='EditarOK' value='OK' />");

                });


                $('#Resultados').on('click', '#VerCorrectas', function() {

                    $('#detalles').css("display", "none");
                    $('#Estadisticas').css("display", "none");
                    $('#ResultadosDetalles').css("display", "none");

                    $('#RespuestasCorrectas').fadeIn("fast", function() {
                    });

                });


                $('#Resultados').on('click', '#VerEstadisticas', function() {

                    $('#detalles').css("display", "none");
                    $('#RespuestasCorrectas').css("display", "none");
                    $('#ResultadosDetalles').css("display", "none");

                    $('#Estadisticas').fadeIn("fast", function() {
                    });

                });
                $('#Resultados').on('click', '#VerTotales', function() {

                    $('#Estadisticas').css("display", "none");
                    $('#RespuestasCorrectas').css("display", "none");
                    $('#ResultadosDetalles').css("display", "none");

                    $('#detalles').fadeIn("fast", function() {
                    });

                });

                $('#Resultados').on('click', '#DetalleResultados', function() {

                    $('#detalles').css("display", "none");
                    $('#Estadisticas').css("display", "none");
                    $('#RespuestasCorrectas').css("display", "none");

                    $('#ResultadosDetalles').fadeIn("fast", function() {
                    });

                });







                $('body').on('click', '.opcion', function() {

                    $(".Alternativas").find("li").css("background", "white");
                    $(".Alternativas").find("li").removeClass("respondido")
                    $(".Alternativas li").find("p").css("color", "black");
                    $(this).css("background", "#3498db");
                    $(this).children().css("color", "white");
                    $(this).addClass("respondido");

                });






                $('.close').click(function() {
                    $('.ventana').fadeOut("fast", function() {
                    });

                    $('#TituloTest').remove();
                    $('.pregunta').remove();

                    $('#ventanaResultados').fadeOut("fast", function() {
                    });


                });

                $('body').on('click', '.close', function() {
                    $('.ventana').fadeOut("fast", function() {
                    });

                    $('#TituloTest').remove();
                    $('.pregunta').remove();

                    $('#Editor').fadeOut("fast", function() {
                    });

                });

                $('#CerrarError').click(function() {
                    $('.ventana').fadeOut("fast", function() {
                    });

                    $('#TituloTest').remove();
                    $('.pregunta').remove();

                    $('#ventanaResultados').fadeOut("fast", function() {
                    });
                    $('#Editor').fadeOut("fast", function() {
                    });

                    $('#MensajeError').fadeOut("fast", function() {
                    });




                });



                $("#BtnCerrarSesion").click(function() {
                    FacebookLogout();
                });



                $('#CerrarResultados').click(function() {
                    $('.ventana').fadeOut("fast", function() {
                    });

                    $('#TituloTest').remove();
                    $('.pregunta').remove();

                    $('#ventanaResultados').fadeOut("fast", function() {
                    });
                    $('#Editor').fadeOut("fast", function() {
                    });

                    $("#detalles #test-circle5").html("");
                    $("#detalles #DetalleTotal ul").html("");


                    $('#detalles').css("display", "block");
                    $('#Estadisticas').css("display", "none");

                    $('#ResultadosDetalles').css("display", "none");
                    $('#TablaTotales tbody').html("");
                    $('#TablaTotalesItems tbody').html("");
                    $('#RespuestasCorrectas #Correctas').html("");
                    $('#Estadisticas #GraficoEstadisticas').html("");


                });

                $('#CerrarEditor').click(function() {
                    $('#Editor').fadeOut("fast", function() {
                    });
                    $('#Editor').find("#EditarOK").remove();

                });

                $('#CerrarMapa').click(function() {
                    $('.ventana').fadeOut("fast", function() {
                    });

                    $('#TituloTest').remove();
                    $('.pregunta').remove();

                    $('#ventanaResultados').fadeOut("fast", function() {
                    });

                    $('#ventanaMapa').fadeOut("fast", function() {
                    });


                    $('#Editor').fadeOut("fast", function() {
                    });


                });


                /*
                 $("#Texto").select(function() {
                 
                 x = (document.getSelection());
                 $('#marcado').text(x);
                 //tinymce.activeEditor.selection.getContent();
                 });
                 
                 */


                $("#btnAgregar").click(function() {


                    $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));

                    var nombre = $('#marcado').text();
                    nombre = $.trim(nombre);

                    if (!nombre == null || !nombre == "") {

                        $.ajax({
                            type: 'POST',
                            url: '<c:url value="/concepto/nuevo" />',
                            data: {concepto: nombre, contenido: $('#Contenido').attr('value')}
                        })
                                .success(function(response) {

                                    $('#Conceptos').prepend("<div id=" + response + " class='Concepto' ><div style='float:left' class='titulo' onclick='Cconcepto(" + response + ")'>" + $('#marcado').text().toUpperCase() + "</div><button onClick='EliminarC(" + response + ")' class='EliminarC' ><img src='<c:url value="/resources/imagenes/error.png" />' /> </button>  <ul style='display:none'><li><input type='button' class='AgregarDefinicion' onclick='AgregarDef(" + response + ")' value='DEFINICION' /> </li><div class='def'> </div><li><input type='button' class='AgregarCaracteristica' onclick='AgregarCar(" + response + ")' value='CARACTERISTICA' /> </li><div class='Car'> </div></li> <li><input type='button' onclick='AgregarObs(" + response + ")' class='AgregarObservacion' value='OBSERVACION' /></li><div class='Obs'> </div><li><input type='button' onclick='AgregarRel(" + response + ")' class='AgregarRelacion' value='RELACION' /></li><div class='rel'> </div> </ul> </div>");
                                });
                    } else {

                        $("#MensajeError").css("display", "block");
                    }



                });
            });
            function AgregarDef(x) {

                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                var detalle = $('#marcado').text();
                detalle = $.trim(detalle);


                if (detalle.toUpperCase().indexOf($("#" + x).find(".titulo").text()) == -1 && detalle.length < 500) {

                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/definicion/nuevo" />',
                        data: {detalle: detalle, concepto_id: x}
                    })
                            .success(function(response) {

                                def = "def";
                                div = $("#" + x).attr('id');
                                $("#" + x).find(".def").append(" <input type='text' id='" + response + "' onblur='EditarDefinicion(" + response + "," + div + ")' id='detalle' style='float:left'  value='" + $('#marcado').text() + "'/><button style='float:left' id='" + response + "' onClick='EliminarDef(" + response + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");


                            });
                } else {

                    $("#MensajeError").css("display", "block");
                }


            }



            function AgregarCar(x) {
                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                var detalle = $('#marcado').text();
                detalle = $.trim(detalle);


                if (detalle.toUpperCase().indexOf($("#" + x).find(".titulo").text()) == -1 && detalle.length < 500) {

                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/caracteristica/nuevo" />',
                        data: {detalle: detalle, concepto_id: x}
                    })
                            .success(function(response) {

                                Car = "Car";
                                div = $("#" + x).attr('id');
                                $("#" + x).find(".Car").append(" <input type='text' id='" + response + "' onblur='EditarCaracteristica(" + response + "," + div + ")' style='float:left'  value='" + $('#marcado').text() + "'/><button style='float:left' id='" + response + "' onClick='EliminarCar(" + response + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");

                            });
                } else {

                    $("#MensajeError").css("display", "block");
                }

            }



            function AgregarObs(x) {
                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                var detalle = $('#marcado').text();
                detalle = $.trim(detalle);

                if (detalle.toUpperCase().indexOf($("#" + x).find(".titulo").text()) == -1 && detalle.length < 500) {
                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/observacion/nuevo" />',
                        data: {detalle: detalle, concepto_id: x}
                    })
                            .success(function(response) {

                                Obs = "Obs";
                                div = $("#" + x).attr('id');
                                $("#" + x).find(".Obs").append(" <input type='text' id='" + response + "' onblur='EditarObservacion(" + response + "," + div + ")' style='float:left'  value='" + $('#marcado').text() + "'/><button style='float:left' id='" + response + "' onClick='EliminarObs(" + response + ")' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");

                            });
                } else {

                    $("#MensajeError").css("display", "block");
                }


            }





            function AgregarRel(x) {

                rel = "rel";
                div = $("#" + x).attr('id');
                opcion = "";
                Id_relacion = "";

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/relacion/nuevo" />',
                    data: {concepto_id: x}
                })
                        .success(function(id) {

                            $.ajax({
                                type: 'POST',
                                url: '<c:url value="/tiporelacion/buscar" />',
                            })
                                    .success(function(response) {

                                        for (var i = 0; i < response.length; i++) {

                                            opcion = opcion + ("<option value=" + response[i].id + ">" + response[i].nombre + "</option>");

                                        }

                                        $("#" + x).find(".rel").append("<select id=" + id + " onchange='EditarRelacion1(" + id + "," + div + ")' style='float:left'>" + opcion + "</select>");

                                        combo = "";
                                        $('#Conceptos div .titulo').each(function(index, element) {
                                            if (div == $(this).parent().attr('id')) {
                                                combo = combo + "<option selected value=" + $(this).parent().attr('id') + ">" + $(this).text() + " </option>";

                                            } else {
                                                combo = combo + "<option value=" + $(this).parent().attr('id') + ">" + $(this).text() + " </option>";

                                            }

                                        });

                                        $("#" + x).find(".rel").append("<select onchange='EditarRelacion2(" + id + "," + div + ")'  >" + combo + "</select> ");
                                        $("#" + x).find(".rel").append("<button onClick='EliminarRel(" + id + ")' style='float:right' class='Eliminar' ><img src='<c:url value="/resources/imagenes/error.png" />' /></button>");



                                    });








                        });




            }

        </script>


    </script>

</head>

<body>
<header>
    <ul>
        <li><a href="index">INICIO</a></li>
        <li><a href="<c:url value="/usuario/perfil" />">${fn:toUpperCase(usuario.nombre)}</a>
        <li><a class="active"  id="seleccion" >CONTENIDOS</a>
            <ul id="ListaContenidos">
                <c:forEach items="${usuario.contenidos}" var="contenido">
                    <li><a  onclick="SeleccionarContenido(${contenido.id})" >${fn:toUpperCase(contenido.nombre)}</a></li>
                    </c:forEach>
                <li><a class="active" style="float:left;cursor: pointer" id="BtnNuevoContenido">NUEVO CONTENIDO</a> 
                    <input id="TxtNuevaMateria" type="text"  /> </li>
            </ul></li>

        <li><a href="">MAPAS CONCEPTUALES</a></li>
        <li><a href="">INFORMES</a></li>


        <ul style="float:right;list-style-type:none;">

            <li><a href="#">TUTORIAL</a></li>
            <li><a class="active" id="BtnCerrarSesion"  >CERRAR SESION</a></li>

        </ul>
    </ul>





</header>
<div id="VentanaBloqueo"></div>
<div id="Contenedor">


    <section>



        <article id="art1">
            <input type="button" id="btnAgregar" value="NUEVO CONCEPTO" />

            <section class="Contenido" id="Conceptos"  >


                <input type="hidden" id="marcado"  />
                <input type="hidden" id="Contenido" >

            </section>

        </article>
        <article id="Texto">
            <input type="button" class="BtnGenerar" id="b1" value="GENERAR TEST" />
            <input type="button" class="BtnGenerar" id="b2" value="INFORME" />
            <input type="button" class="BtnGenerar" id="b3" value="MAPA CONCEPTUAL" />

            <textarea class="tinymce" id="TxtMateria"></textarea>

        </article>
    </section>


</div>

<div style="clear:both"></div>

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

        <form action="#" method="post">

            <input type="text" name="email" placeholder="Email" />
            <textarea name="message" placeholder="Mensaje"></textarea>
            <button>Send</button>

        </form>

    </div>

</footer>



<div class="ventana">

    <script type="text/javascript">
        function Siguiente() {

            var Lista = localStorage.getItem("ListaRespuestas");
            Lista = JSON.parse(Lista);
            if (Lista == null)
                Lista = [];
            //$(this).css("background", "#3498db");


            $('#Preguntas div').each(function(index, element) {



                if ($(this).css('display') == "block") {

                    if ($(this).next().next().attr("class") != "pregunta") {
                        $("#BtnSiguente").val("FINALIZAR");
                    }


                    if ($(this).find(".TipoPregunta").val() == "SeleccionMultiple") {

                        if ($(this).find('.respondido').text() == $(this).find(".respuesta").attr("value")) {

                            var respuesta = JSON.stringify({
                                Tipo: 'SeleccionMultiple',
                                Correcto: true,
                                ConceptoReferencia: $(this).find(".ConceptoReferenciado").val(),
                            });
                            Lista.push(respuesta);
                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                        } else {
                            var respuesta = JSON.stringify({
                                Tipo: 'SeleccionMultiple',
                                Correcto: false,
                                ConceptoReferencia: $(this).find(".ConceptoReferenciado").val(),
                            });
                            Lista.push(respuesta);
                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                        }
                    }



                    if ($(this).find(".TipoPregunta").val() == "TerminosPareados") {
                        var tabla = $(this).find("table");
                        $(tabla).find('tbody tr').each(function(index, element) {

                            var ingresado = $(this).find("td").eq(2).find("input").val();
                            var Correcta = $(this).find("td").eq(4).text();
                            if (ingresado != null) {

                                $(tabla).find('tbody tr').each(function(index, element) {

                                    if ($(this).find("td").eq(1).text() == Correcta) {

                                        var NumeroConcepto = $(this).find("td").eq(0).text();
                                        NumeroConcepto = NumeroConcepto.replace(/\D/g, '');
                                        var ConceptoReferenciado = $(this).find("td").eq(0).text();
                                        ConceptoReferenciado = ConceptoReferenciado.replace(/[0-9]/, "");
                                        ConceptoReferenciado = ConceptoReferenciado.replace(".", "");
                                        if (ingresado == NumeroConcepto) {

                                            var respuestaPareado = JSON.stringify({
                                                Tipo: 'TerminoPareado',
                                                Correcto: true,
                                                ConceptoReferencia: ConceptoReferenciado.trim(),
                                            });
                                            Lista.push(respuestaPareado);
                                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                                        } else {
                                            var respuestaPareado = JSON.stringify({
                                                Tipo: 'TerminoPareado',
                                                Correcto: false,
                                                ConceptoReferencia: ConceptoReferenciado.trim(),
                                            });
                                            Lista.push(respuestaPareado);
                                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                                        }
                                    }


                                });
                            }



                        });
                    }


                    if ($(this).find(".TipoPregunta").val() == "Completacion") {
                        var enunciado = $(this).find(".Completacion");
                        var correcto = true;
                        $(enunciado).find('input').each(function(index, element) {
                            if ($(this).attr("id") != $(this).val()) {
                                correcto = false;
                            }
                        });
                        if (correcto == true) {

                            var respuestaCompletacion = JSON.stringify({
                                Tipo: 'Completacion',
                                Correcto: true,
                                ConceptoReferencia: $(enunciado.parent()).find(".ConceptoReferenciado").val(),
                            });
                            Lista.push(respuestaCompletacion);
                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                        } else {

                            var respuestaCompletacion = JSON.stringify({
                                Tipo: 'Completacion',
                                Correcto: false,
                                ConceptoReferencia: $(enunciado.parent()).find(".ConceptoReferenciado").val(),
                            });
                            Lista.push(respuestaCompletacion);
                            localStorage.setItem("ListaRespuestas", JSON.stringify(Lista));
                        }


                    }


                    if ($(this).next().attr("class") != "pregunta") {

                        $(".ventana").css("display", "none");
                        //   $('#Contenedor').append("<div id='cargando'><img id='ImgCargando'  src='<c:url value="/resources/imagenes/loading-verde.gif" />' /></div>");

                        //    $('#ventanaResultados').find(".close").after(("<h1 id='TituloTest'>TUS RESULTADOS</h1>"));

                        // $("#detalles").html(localStorage.ListaRespuestas);


                        var preguntas = $("#Preguntas").html();
                        $("#RespuestasCorrectas #Correctas").append(preguntas);



                        $('#RespuestasCorrectas #Correctas .pregunta').each(function(index, element) {
                            $(this).css("display", "block");
                            $(this).css("height", "auto");
                            $(this).css("border-bottom", "1px solid #ccc");
                            $(this).css("padding-bottom", "50px");

                            if ($(this).find(".TipoPregunta").val() == "SeleccionMultiple") {
                                $('#RespuestasCorrectas #Correctas .pregunta').find("span").remove();
                                var respuesta = $(this).find(".respuesta").val();
                                var alternativas = $(this).find(".Alternativas .opcion p");
                                $(this).find(".Alternativas .opcion p").css("background-color", "white");

                                $(alternativas).each(function(index, element) {
                                    $(this).css("font-family", "Agency FB");
                                    $(this).css("font-size", "20px");
                                    if ($(this).text() == respuesta) {
                                        $(this).css("color", "red");
                                        //             $(this).css("font-family", "Arial");
                                        $(this).css("font-weight", "bold");
                                        //    $(this).css("padding", "10px");

                                    }
                                })

                                $(this).find(".Alternativas .opcion").removeClass();


                            }








                            if ($(this).find(".TipoPregunta").val() == "TerminosPareados") {
                                var tabla = $(this).find("table");
                                $(tabla).find('tbody tr').each(function(index, element) {

                                    var NumeroConcepto = null;
                                    var Correcta = $(this).find("td").eq(4).text();
                                    $(tabla).find('tbody tr').each(function(index, element) {

                                        if ($(this).find("td").eq(1).text() == Correcta) {
                                            NumeroConcepto = $(this).find("td").eq(0).text();
                                            NumeroConcepto = NumeroConcepto.replace(/\D/g, '');


                                        }


                                    });
                                    Correcta = $(this).find("td").find("input").val(NumeroConcepto);
                                    $(this).find("td").find("input").css("color", "red");
                                    $(this).find("td").find("input").css("font-weight", "bold");
                                    $(this).find("#ColumnaNumero").css("width", "24");


                                });
                            }


                            if ($(this).find(".TipoPregunta").val() == "Completacion") {
                                var preguntas = $(this);
                                preguntas = preguntas.find(".Completacion input");
                                $(preguntas).each(function(index, element) {
                                    $(this).val($(this).attr("id"));


                                    $(this).css("color", "red");
                                    $(this).css("font-weight", "bold");
                                })



                            }








                        })



                        Lista = localStorage.ListaRespuestas;
                        Lista = JSON.parse(Lista);

                        var ConceptosReferenciados = [];

                        for (var i in Lista) {
                            Lista[i] = JSON.parse(Lista[i])
                            sw = false;

                            for (var j in ConceptosReferenciados) {
                                if (Lista[i].ConceptoReferencia == ConceptosReferenciados[j]) {
                                    sw = true;
                                }

                            }

                            if (sw == false) {
                                ConceptosReferenciados.push(Lista[i].ConceptoReferencia);

                            }

                        }





                        var correctasTotal = 0;

                        for (var i in Lista) {
                            if (Lista[i].Correcto == true) {
                                correctasTotal = correctasTotal + 1;
                            }
                        }

                        $("#ventanaResultados").fadeIn("fast", function() {

                        });

                        $("#test-circle5").circliful({
                            animationStep: 5,
                            foregroundBorderWidth: 5,
                            backgroundBorderWidth: 15,
                            percent: (correctasTotal * 100 / Lista.length).toFixed(2),
                            icon: 'f14a',
                            iconPosition: 'middle',
                            text: 'RENDIMIENTO',
                            textBelow: true
                        });
                        $("#DetalleTotal ul").append("<li><p style='color: rgb(102, 102, 102);' >" + $('#seleccion').text() + "</p></li><li style='margin-left: 30px' ><img style='height: 20px;    vertical-align: text-top;' src='<c:url value="/resources/imagenes/list.png" />' /> TOTAL DE PREGUNTAS: " + Lista.length + "</li> <li style='margin-left: 30px' ><img style='height: 20px;     vertical-align: text-top;' src='<c:url value="/resources/imagenes/Tick.png" />' /> CORRECTAS: " + correctasTotal + " </li> <li style='margin-left: 30px' ><img style='height: 26px; vertical-align: text-bottom;' src='<c:url value="/resources/imagenes/close.png" />' /> INCORRECTAS: " + (Lista.length - correctasTotal) + "</li>");
                        //  

                        //  $("#ResultadosDetalles table").append("<tr><td>Total de preguntas</td><td>" + Lista.length + "</td><td>" + correctasTotal + "</td><td>" + (Lista.length - correctasTotal) + "</td><td>" + (correctasTotal * 100 / Lista.length).toFixed(2) + "%</td><td>" + ((Lista.length - correctasTotal) * 100 / Lista.length).toFixed(2) + "%</td></tr>");



                        correctasTotal = 0;
                        var total = 0;
                        for (var i in Lista) {

                            if (Lista[i].Tipo == "SeleccionMultiple") {

                                total = total + 1;
                                if (Lista[i].Correcto == true) {
                                    correctasTotal = correctasTotal + 1;
                                }
                            }
                        }

                        if (total > 0) {

                            if ((correctasTotal * 100 / total) < 60) {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>SELECCION MULTIPLE</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td style='color: red;'>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");
                            } else {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>SELECCION MULTIPLE</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");

                            }
                        }


                        correctasTotal = 0;
                        total = 0;
                        for (var i in Lista) {

                            if (Lista[i].Tipo == "TerminoPareado") {

                                total = total + 1;
                                if (Lista[i].Correcto == true) {
                                    correctasTotal = correctasTotal + 1;
                                }

                            }
                        }

                        if (total > 0) {

                            if ((correctasTotal * 100 / total) < 60) {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>TERMINOS PAREADOS</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td style='color: red;' >" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");
                            } else {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>TERMINOS PAREADOS</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");
                            }
                        }


                        correctasTotal = 0;
                        total = 0;
                        for (var i in Lista) {

                            if (Lista[i].Tipo == "Completacion") {

                                total = total + 1;
                                if (Lista[i].Correcto == true) {
                                    correctasTotal = correctasTotal + 1;
                                }

                            }
                        }
                        if (total > 0) {
                            if ((correctasTotal * 100 / total) < 60) {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>COMPLETACION</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td  style='color: red;' >" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");
                            } else {
                                $("#ResultadosDetalles #TablaTotalesItems tbody").append("<tr><td>COMPLETACION</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");

                            }
                        }



                        for (var i in ConceptosReferenciados) {
                            correctasTotal = 0;
                            total = 0;

                            for (var j in Lista) {

                                if (ConceptosReferenciados[i] == Lista[j].ConceptoReferencia) {
                                    total = total + 1;
                                    if (Lista[j].Correcto == true) {
                                        correctasTotal = correctasTotal + 1;
                                    }
                                }

                            }
                            if (total > 0) {

                                if ((correctasTotal * 100 / total) < 60) {
                                    $("#ResultadosDetalles #TablaTotales tbody").append("<tr><td>" + ConceptosReferenciados[i].toUpperCase() + "</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td style='color: red;' >" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");

                                } else {
                                    $("#ResultadosDetalles #TablaTotales tbody").append("<tr><td>" + ConceptosReferenciados[i].toUpperCase() + "</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");

                                }
                            }
                            // $("#ResultadosDetalles #TablaTotales tbody").append("<tr><td>" + ConceptosReferenciados[i].toUpperCase() + "</td><td>" + total + "</td><td>" + correctasTotal + "</td><td>" + (total - correctasTotal) + "</td><td>" + (correctasTotal * 100 / total).toFixed(2) + "%</td></tr>");
                            // <td>" + ((total - correctasTotal) * 100 / total).toFixed(2) + "%</td> 

                        }
                        /*
                         */
                        $.ajax({
                            type: 'POST',
                            url: '<c:url value="/test/nuevo" />',
                            data: {contenido_id: $('#Contenido').attr('value'), resultados: localStorage.ListaRespuestas}
                        }).success(function(response) {


                            $.ajax({
                                type: 'POST',
                                url: '<c:url value="/test/buscar" />',
                                data: {seleccionado: $('#Contenido').attr('value')}
                            }).success(function(response) {

                                var fechas = [];
                                var correctas = [];
                                var cantidades = [];
                                var incorrectas = [];
                                var maximo = 0;

                                for (var i = response.length - 1; i >= 0; i--) {
                                    maximo = maximo + 1;

                                    var estadistica = response[i].resultados;
                                    estadistica = JSON.parse(estadistica);
                                    for (var j in estadistica) {
                                        estadistica[j] = JSON.parse(estadistica[j])
                                    }

                                    var correctasTotal = 0;

                                    for (var j in estadistica) {
                                        if (estadistica[j].Correcto == true) {
                                            correctasTotal = correctasTotal + 1;
                                        }
                                    }

                                    correctas.push(correctasTotal);

                                    fechas.push(response[i].fecha);
                                    console.log(response[i].fecha)
                                    cantidades.push(estadistica.length);
                                    incorrectas.push(estadistica.length - correctasTotal);

                                    if (maximo == 7) {
                                        break;
                                    }

                                }





                                $(function() {
                                    Highcharts.chart('GraficoEstadisticas', {
                                        chart: {
                                            type: 'column'
                                        },
                                        colors: ['#153e5a', '#00C600', '#dc1111'],
                                        title: {
                                            text: 'TUS ESTADISTICAS'
                                        },
                                        subtitle: {
                                            text: $("#seleccion").text()
                                        },
                                        xAxis: {
                                            categories: fechas,
                                            crosshair: true
                                        },
                                        yAxis: {
                                            min: 0,
                                            title: {
                                                text: 'Rainfall (resp)'
                                            }
                                        },
                                        tooltip: {
                                            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
                                                name: 'TOTAL',
                                                data: cantidades

                                            }, {
                                                name: 'CORRECTAS',
                                                data: correctas

                                            }, {
                                                name: 'INCORRECTAS',
                                                data: incorrectas

                                            }]
                                    });
                                });


                            });











                        });
                    }













                    //  $(this).css("display", "none")
                    $(this).css("display", "none").fadeOut("fast", function() {
                    });
                    $(this).next().fadeIn("fast", function() {
                    });
                    return false;
                }

            });
        }

    </script>

    <div id="dialogo">
        <input type="hidden" id="Puntaje" />
        <input type="hidden" id="PuntajeTotal" />

        <a href="#close" class="close"> X </a>



        <div id="Preguntas">
            <div class="pregunta">
                <p id="cantidad">1/81</p>
                <p id="Enunciado">Completar el enunciado. </p>
                <p class="Completacion" >Lorem ipsum dolor sit amet, consectetur <input type="text"   placeholder="_______________________" maxlength="23" size="23"> elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut <input type="text"   placeholder="_______________________" maxlength="23" size="23"> enim ad minim veniam, quis nostrud exercitation ullamco <input type="text"   placeholder="_______________________" maxlength="23" size="23"> laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
        </div>
        <input type="button" id="BtnSiguente" onclick="Siguiente()" value="SIGUENTE">
    </div>
</div>

<div id="ventanaResultados">
    <div id="Resultados">
        <div id="TusResultados">TUS RESULTADOS</div><div id="CerrarResultados" >X</div>
        <div id="detalles" >

            <div class="MenuResultados" id="Porcentaje" >
                <div id="test-circle5"></div>
            </div>
            <div class="MenuResultados" id="DetalleTotal" >

                <ul>

                </ul>

            </div>
            <div style="clear:both"></div>
            <div id="ResultadosMenu">
                <input type="button" class="BtnGenerar" id="DetalleResultados" value="VER DETALLES" />
                <input type="button" class="BtnGenerar" id="VerCorrectas" value="RESPUESTAS CORRECTAS" />
                <input type="button" class="BtnGenerar" id="VerEstadisticas" value="ESTADISTICAS" />
                <input type="button" class="active" id="RepetirTest" value="REPETIR TEST" />

            </div>



        </div>
        <div id="ResultadosDetalles" >
            <table class="TablaTotales" id='TablaTotalesItems' >
                <thead>
                <th>ITEM</th>
                <th>TOTAL</th>
                <th>CORRECTAS</th>
                <th>INCORRECTAS</th>
                <th>RENDIMIENTO</th>
                </thead>
                <tbody>
                </tbody>
            </table> 

            <table class="TablaTotales" id='TablaTotales' >
                <thead>
                <th>CONCEPTO</th>
                <th>TOTAL</th>
                <th>CORRECTAS</th>
                <th>INCORRECTAS</th>
                <th>RENDIMIENTO</th>
                </thead>
                <tbody>
                </tbody>
            </table> 
            <div id="ResultadosMenu">
                <input type="button" class="BtnGenerar" id="VerTotales" value="VER TOTALES" />
                <input type="button" class="BtnGenerar" id="VerCorrectas" value="RESPUESTAS CORRECTAS" />
                <input type="button" class="BtnGenerar" id="VerEstadisticas" value="ESTADISTICAS" />
                <input type="button" class="active" id="RepetirTest" value="REPETIR TEST" />
            </div>
        </div>
        <div id="RespuestasCorrectas" >
            <div id="ResultadosMenu">
                <input type="button" class="BtnGenerar" id="VerTotales" value="VER TOTALES" /> 
                <input type="button" class="BtnGenerar" id="DetalleResultados" value="VER DETALLES" />
                <input type="button" class="BtnGenerar" id="VerEstadisticas" value="ESTADISTICAS" />
                <input type="button" class="active" id="RepetirTest" value="REPETIR TEST" />
            </div>
            <div id="Correctas" >

            </div>

        </div>
        <div id="Estadisticas" >
            <div id="GraficoEstadisticas" >

            </div>
            <div id="ResultadosMenu">
                <input type="button" class="BtnGenerar" id="VerTotales" value="VER TOTALES" />
                <input type="button" class="BtnGenerar" id="DetalleResultados" value="VER DETALLES" />
                <input type="button" class="BtnGenerar" id="VerCorrectas" value="RESPUESTAS CORRECTAS" />

                <input type="button" class="active" id="RepetirTest" value="REPETIR TEST" />
            </div>
        </div>
    </div>

</div>

<div id="ventanaMapa">
    <div id="MapaConceptual">
        <div id="TusResultados">MAPA CONCEPTUAL</div><div id="CerrarMapa" >X</div>

    </div>

</div>

<div id="Editor" >
    <div id="TusResultados">EDITAR</div><div id="CerrarEditor" >X</div>
    <textarea id="TxtEditor"></textarea>
    <br>

</div>

<div id="MensajeError"><div id="TusResultados">ERROR</div><div id="CerrarError" >X</div>
    <p></p>
</div>


</body>
</html>
