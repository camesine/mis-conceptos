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
                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/contenido/buscar" />',
                    data: {seleccionado: x},
                })
                        .success(function(response) {
                            $('#seleccion').text(response.nombre.toUpperCase());
                            $('#TxtMateria').val(response.texto);
                            $('#Contenido').attr("value", response.id);
                        });




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


        </script>



        <script type="text/javascript">

            $(document).ready(function(e) {
                $('#TxtMateria').on('input', function() {
                    $.ajax({
                        type: 'POST',
                        url: '<c:url value="/contenido/editar" />',
                        data: {id: $('#Contenido').attr('value'),
                            nombre: $('#seleccion').text(),
                            texto: $('#TxtMateria').val(),
                            usuario_id: ${usuario.id}
                        },
                    })
                            .success(function(response) {
                                console.log(response)
                            });
                });




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



// GENERACION DE PREGUNTAS

                $("#b1").click(function() {
                    $('.ventana').css('display', 'block');
                    
                    
                    /*
                    $.ajax({
                    type: 'POST',
                    url: '<c:url value="/concepto/buscar" />',
                    data: {seleccionado: $('#Contenido').attr('value')},
                })
                        .success(function(response) {

                            console.log(response);
                        });
*/



                });



                $('.close').click(function() {
                    $('.ventana').css('display', 'none');

                });



                $("#Texto").select(function() {
                    x = (document.getSelection());
                    $('#marcado').text(x);
                });




                $("#btnAgregar").click(function() {
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
            </ul>
        <li><a href="">MAPAS CONCEPTUALES</a>
        <li><a href="">INFORMES</a>
        <li><a href="">NOSOTROS</a>
        <li><a href="">CONTACTO</a></li>
        <ul style="float:right;list-style-type:none;">
            <li><a href="#">TUTORIAL</a></li>
            <li><a class="active" href="logout">CERRAR SESION</a></li>

        </ul>
    </ul>





</header>

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
            <input type="button" id="b1" value="GENERAR TEST" />
            <textarea id="TxtMateria"></textarea>

        </article>
    </section>


</div>

<div style="clear:both"></div>
<footer>
    <h1>PREGUNTAME.CL</h1>
</footer>


<div class="ventana">

    <div id="dialogo">
        <a href="#close" class="close"> X </a>

    </div>



</div>

</body>
</html>
