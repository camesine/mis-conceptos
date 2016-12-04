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
        <title>QUIENES SOMOS</title>


    </head>

    <body>
        ${correo}
    <header>
        <ul>
            <li><a href="<c:url value="/usuario/index"  />">INICIO</a></li>
            <li><a href="<c:url value="/plataforma/nosotros" />">QUIENES SOMOS</a></li>
            <ul style="float:right;list-style-type:none;">
                <li><a class="active" href="#">TUTORIAL</a></li>
            </ul>
        </ul>
    </header>

    <div id="Contenedor">

        <div id="Nosotros">
            <h1>MIS CONCEPTOS</h1>
            <p>Somos una empresa que crea soluciones informaticas para posibilitar al alumno a tomar mejores decisiones en su proceso de aprendizaje, así mismo, darle prioridad a aquellos factores que este considere deficientes en su estudio, mediante una autoevaluación constante de la información que considere relevante</p>
            <h2>Mision</h2>
            <p>Ayudar a los alumnos y alumnas a mejorar los factores deficientes de su proceso de aprendizaje entregándoles herramientas que le permitan tomar mejores decisiones en su proceso de aprendizaje.</p>
            <h2>Vision</h2>
            <p>Lograr ser una plataforma educativa reconocida a nivel nacional, que represente una importante ayuda en los procesos de aprendizaje autónomo de los alumnos, entregando herramientas que posibiliten al estudiante mejorar gradualmente su rendimiento académico así como sus hábitos de estudio.</p>
        </div>
        
        
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
               
                <a href="#">NOSOTROS</a>
               
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


</body>
</html>
