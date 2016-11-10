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
        <title>PERFIL</title>

        <script type="text/javascript" >

        </script>


    </head>

    <body>
    <header>
        <ul>
            <li><a href="<c:url value="/usuario/plataforma" />">INICIO</a></li>
            <li><a href="#">NOSOTROS</a></li>
            <li><a href="#">CONTACTO</a></li>
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
                            <input type="text" name="TxtCorreo" id="TxtCorreoNuevo" value="" placeholder="Email">
                            <div id="LblForm" ><label>NOMBRE</label></div>
                            <input type="text" name="TxtNombre" id="TxtNombreNuevo" value="" placeholder="Nombre">
                            <div id="LblForm" ><label>APELLIDO</label></div>
                            <input type="text" name="TxtApellido" id="TxtApellidoNuevo" value="" placeholder="Apellido">
                            <div id="LblForm" ><label>CONTRASEÑA</label></div>
                            <input type="password" name="TxtPass" id="TxtPassNuevo"  value="" placeholder="Contraseña">
                           
                        </form>
                        <input type="submit" id="Btn" name="commit" value="CONFIRMAR">
            
        </section>
        
        <section id="contenidos" >
            <h1>MIS CONTENIDOS</h1>
            <table>
                <tr><td class="TDnombreContenido"><input type="text" CONTENIDO1/></td><td><button>INFORME</button></td><td></td><td><button>ELIMINAR</button></td></tr>
                <tr><td class="TDnombreContenido"><input type="text" CONTENIDO1/></td><td><button>INFORME</button></td><td></td><td><button>ELIMINAR</button></td></tr>
                <tr><td class="TDnombreContenido"><input type="text" CONTENIDO1/></td><td><button>INFORME</button></td><td></td><td><button>ELIMINAR</button></td></tr>
                </table>
            
        </section>
        
        
    </div>

    <footer>
        <h1>MisConceptos.CL</h1>
    </footer>


</body>
</html>
