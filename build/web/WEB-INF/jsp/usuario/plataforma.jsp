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

        <script src="<c:url value="/resources/js/tinymce/tinymce.min.js" />"></script>
        <script src="<c:url value="/resources/js/tinymce/init-tinymce.js" />"></script>



        <title>Preguntame.cl</title>

        <script type="text/javascript">

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
                            tinymce.activeEditor.setContent(response.texto);
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


        </script>



        <script type="text/javascript">

            $(document).ready(function(e) {

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




                $("#b2").click(function() {
                    window.open("<c:url value="/contenido/informe" /> " + "?contenido=" + $('#Contenido').attr('value'), "_blank");
                });





// GENERACION DE PREGUNTAS

                $("#b1").click(function() {
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
                                console.log(response)
                                for (var i = 0; i < response.length; i++) {
                                    etiqueta = etiqueta + "<div class='pregunta'><input type='hidden' class='TipoPregunta' value='SeleccionMultiple' /><input type='hidden' class='ConceptoReferenciado' value='" + response[i].conceptoReferencia + "' /><p id='Enunciado'>" + response[i].enunciado + "</p><input type='hidden' class='respuesta' value='" + response[i].respuesta + "' /> <ul class='Alternativas' ><li class='opcion'><p class='tooltip' >" + response[i].opcion1 + "<span>" + response[i].opcion1 + "</span></p></li><li  class='opcion'><p class='tooltip' >" + response[i].opcion2 + "<span>" + response[i].opcion2 + "</span></p></li><li  class='opcion'><p class='tooltip'>" + response[i].opcion3 + "<span>" + response[i].opcion3 + "</span></p></li><li  class='opcion'><p class='tooltip' >" + response[i].opcion4 + "<span>" + response[i].opcion4 + "</span></p></li></ul></div>";
                                }

                                $('#Preguntas').html(etiqueta);


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

                    nombre = $('#marcado').text();
                    nombre = $.trim(nombre);


                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/concepto/nuevo" />',
                        data: {concepto: nombre, contenido: $('#Contenido').attr('value')}
                    })
                            .success(function(response) {

                                $('#Conceptos').prepend("<div id=" + response + " class='Concepto' ><div style='float:left' class='titulo' onclick='Cconcepto(" + response + ")'>" + $('#marcado').text().toUpperCase() + "</div><button onClick='EliminarC(" + response + ")' class='EliminarC' ><img src='<c:url value="/resources/imagenes/error.png" />' /> </button>  <ul style='display:none'><li><input type='button' class='AgregarDefinicion' onclick='AgregarDef(" + response + ")' value='DEFINICION' /> </li><div class='def'> </div><li><input type='button' class='AgregarCaracteristica' onclick='AgregarCar(" + response + ")' value='CARACTERISTICA' /> </li><div class='Car'> </div></li> <li><input type='button' onclick='AgregarObs(" + response + ")' class='AgregarObservacion' value='OBSERVACION' /></li><div class='Obs'> </div><li><input type='button' onclick='AgregarRel(" + response + ")' class='AgregarRelacion' value='RELACION' /></li><div class='rel'> </div> </ul> </div>");
                            });




                });
            });



            function AgregarDef(x) {


                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                detalle = $('#marcado').text();
                detalle = $.trim(detalle);


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



            }



            function AgregarCar(x) {
                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                detalle = $('#marcado').text();
                detalle = $.trim(detalle);

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

            }



            function AgregarObs(x) {
                $('#marcado').text(tinymce.activeEditor.selection.getContent({format: 'text'}));
                detalle = $('#marcado').text();
                detalle = $.trim(detalle);

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
        <li><a href="#">${fn:toUpperCase(usuario.nombre)}</a>
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
            <li><a class="active" href="logout">CERRAR SESION</a></li>

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

            <textarea class="tinymce" id="TxtMateria"></textarea>

        </article>
    </section>


</div>

<div style="clear:both"></div>
<footer>
    <h1>MisConceptos.cl</h1>
</footer>


<div class="ventana">

    <script type="text/javascript">
        function Siguiente() {

            //$(this).css("background", "#3498db");

            $('#Preguntas div').each(function(index, element) {



                if ($(this).css('display') == "block") {

                    if ($(this).next().next().attr("class") != "pregunta") {
                        $("#BtnSiguente").val("FINALIZAR");
                    }

                    if ($(this).find('.respondido').text() == $(this).find(".respuesta").attr("value")) {
                        $("#Puntaje").attr("value", parseInt($("#Puntaje").attr("value")) + 1)
                    }

                    var Completacion = 0;
                    var TerminosPareados = 0;
                    var Seleccion = 0;

                    if ($(this).next().attr("class") != "pregunta") {

                        $('#Preguntas .pregunta .TipoPregunta').each(function(index, element) {
                            if ($(this).val() == "SeleccionMultiple") {
                                Seleccion = Seleccion + 1;
                            }
                            if ($(this).val() == "TerminosPareados") {
                                TerminosPareados = TerminosPareados + 1;
                            }
                            if ($(this).val() == "Completacion") {
                                Completacion = Completacion + 1;

                            }

                        })

                        alert("El ultimo" + " Puntaje : " + $("#Puntaje").attr("value") + " de " + $("#PuntajeTotal").attr("value"));
                        alert("seleccion " + Seleccion)
                        alert("Terminos p" + TerminosPareados)
                        alert("Completacion" + Completacion)


                        return false;
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



</body>
</html>
