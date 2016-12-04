package cl.preguntame.controller;

import cl.preguntame.clases.Cabecera;
import cl.preguntame.clases.Generador;
import cl.preguntame.clases.ListadoPDF;
import cl.preguntame.model.Caracteristica;
import cl.preguntame.model.Concepto;
import cl.preguntame.model.Contenido;
import cl.preguntame.model.Definicion;
import cl.preguntame.model.Observacion;
import cl.preguntame.model.Usuario;
import cl.preguntame.service.CaracteristicaService;
import cl.preguntame.service.ConceptoService;
import cl.preguntame.service.ContenidoService;
import cl.preguntame.service.DefinicionService;
import cl.preguntame.service.ObservacionService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contenido")
public class ContenidoController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Usuario user = new Usuario();
        user.setId(Integer.parseInt(req.getParameter("usuario_id")));

        Contenido c = new Contenido();
        c.setNombre(req.getParameter("nombre"));
        c.setUsuario(user);

        return String.valueOf(new ContenidoService().GuardarContenido(c));

    }

    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public Contenido BuscarContenido(HttpServletRequest req) {

        ContenidoService cs = new ContenidoService();
        Contenido c = cs.BuscarContenido(Integer.parseInt(req.getParameter("seleccionado")));
        Contenido p = new Contenido();
        p.setId(c.getId());
        p.setNombre(c.getNombre());
        p.setTexto(c.getTexto());

        return p;
    }

    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        Contenido c = new Contenido();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setNombre(req.getParameter("nombre"));
        c.setTexto(req.getParameter("texto"));

        Usuario u = new Usuario();
        u.setId(Integer.parseInt(req.getParameter("usuario_id")));

        c.setUsuario(u);

        ContenidoService cs = new ContenidoService();
        cs.ActualizarContenido(c);

        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/preguntas", method = RequestMethod.POST)
    public ArrayList Preguntas(HttpServletRequest req) {

        Generador AccesoPreguntas = new Generador(Integer.parseInt(req.getParameter("seleccionado")));
        return AccesoPreguntas.PreguntasSeleccion();

    }

    @ResponseBody
    @RequestMapping(value = "/preguntasTerminosPareados", method = RequestMethod.POST)
    public String preguntasTerminosPareados(HttpServletRequest req) {

        Generador AccesoPreguntas = new Generador(Integer.parseInt(req.getParameter("seleccionado")));
        return AccesoPreguntas.PreguntasTerminosPareados();

    }

    @ResponseBody
    @RequestMapping(value = "/preguntasCompletacion", method = RequestMethod.POST)
    public String preguntasCompletacion(HttpServletRequest req) throws IOException {

        Generador AccesoPreguntas = new Generador(Integer.parseInt(req.getParameter("seleccionado")));
        return AccesoPreguntas.PreguntaCompletacion();

    }

    @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        Contenido contenido = new Contenido();
        contenido.setId(Integer.parseInt(req.getParameter("id")));
        contenido.setNombre("");
        contenido.setTexto("");
        Usuario u = new Usuario();
        u.setId(000);
        contenido.setUsuario(u);

        ContenidoService acceso = new ContenidoService();
        acceso.EliminarContenido(contenido);

        return "true";

    }

    @ResponseBody
    @RequestMapping(value = "/editarNombre", method = RequestMethod.POST)
    public String EditarNombre(HttpServletRequest req) {

        ContenidoService acceso = new ContenidoService();
        Contenido c = acceso.BuscarContenido(Integer.parseInt(req.getParameter("id")));

        Contenido Update = new Contenido();
        Update.setId(Integer.parseInt(req.getParameter("id")));
        Update.setNombre(req.getParameter("nombre"));
        Update.setTexto(c.getTexto());

        Usuario u = new Usuario();
        u.setId(Integer.parseInt(req.getParameter("usuario_id")));

        Update.setUsuario(u);

        acceso.ActualizarContenido(Update);

        return "true";

    }

    @ResponseBody
    @RequestMapping(value = "/informe", method = RequestMethod.GET)
    public void Informe(HttpServletRequest req, HttpServletResponse res) throws IOException, DocumentException {

        ContenidoService AccesoContenido = new ContenidoService();
        Contenido contenido = AccesoContenido.BuscarContenido(Integer.parseInt(req.getParameter("contenido")));

        ConceptoService AccesoConcepto = new ConceptoService();
        List<Concepto> ListaConceptos = AccesoConcepto.BuscarConceptoContenido(Integer.parseInt(req.getParameter("contenido")));

        DefinicionService AccesoDefinicion = new DefinicionService();
        List<Definicion> ListaDefiniciones = AccesoDefinicion.BuscarDefinicionContenido(Integer.parseInt(req.getParameter("contenido")));

        CaracteristicaService AccesoCaracteristica = new CaracteristicaService();
        List<Caracteristica> ListaCaracteristicas = AccesoCaracteristica.BuscarCaracteristicaContenido(Integer.parseInt(req.getParameter("contenido")));

        ObservacionService AccesoObservacion = new ObservacionService();
        List<Observacion> ListaObservaciones = AccesoObservacion.BuscarObservacionContenido(Integer.parseInt(req.getParameter("contenido")));

        res.setContentType("application/pdf");
        Document Informe = new Document();
        PdfWriter writer = PdfWriter.getInstance(Informe, res.getOutputStream());

        Cabecera encabezado = new Cabecera();
        encabezado.setEncabezado(contenido.getNombre());
        writer.setPageEvent(encabezado);
        Informe.setMargins(30, 30, 55, 30);
       // com.itextpdf.text.List lista = new com.itextpdf.text.List();
        Informe.open();

       // Paragraph titulo = new Paragraph(contenido.getNombre(), FontFactory.getFont("arial", 16, BaseColor.BLACK));
       // titulo.setAlignment(Element.ALIGN_LEFT);
        /* Image imagen = Image.getInstance("C:\\Users\\Hector\\Documents\\GitHub\\preguntame\\web\\WEB-INF\\jsp\\resources\\imagenes\\loading-verde.gif");
         imagen.setAlignment(Element.ALIGN_RIGHT);
         imagen.scalePercent(20);
         Informe.add(imagen);*/
       // Informe.add(titulo);

        char punto = 4;
        for (int i = 0; i < ListaConceptos.size(); i++) {

            Informe.add(new Paragraph("\n"));
            Informe.add(new Paragraph("CONCEPTO: " + ListaConceptos.get(i).getNombre().toUpperCase(), FontFactory.getFont("arial", 12, Font.BOLD)));

            //    Informe.add(new Paragraph("DEFINICIONES:", FontFactory.getFont("arial", 10, Font.BOLD)));
            Paragraph p = new Paragraph("DEFINICIONES:", FontFactory.getFont("arial", 10, Font.BOLD));
            p.setIndentationLeft(18);
            Informe.add(p);

            ArrayList<ListItem> items = new ArrayList<>();

            for (int y = 0; y < ListaDefiniciones.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaDefiniciones.get(y).getConcepto().getId()) {

                    items.add(new ListItem(ListaDefiniciones.get(y).getDetalle()));
                }
            }
            Informe.add(new ListadoPDF(items).getListado());

            items.clear();

            p = new Paragraph("CARACTERISTICAS:", FontFactory.getFont("arial", 10, Font.BOLD));
            p.setIndentationLeft(18);
            Informe.add(p);

            for (int y = 0; y < ListaCaracteristicas.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaCaracteristicas.get(y).getConcepto().getId()) {

                    items.add(new ListItem(ListaCaracteristicas.get(y).getDetalle()));
                }
            }
            Informe.add(new ListadoPDF(items).getListado());
            items.clear();

            p = new Paragraph("OBSERVACIONES:", FontFactory.getFont("arial", 10, Font.BOLD));
            p.setIndentationLeft(18);
            Informe.add(p);

            for (int y = 0; y < ListaObservaciones.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaObservaciones.get(y).getConcepto().getId()) {

                    items.add(new ListItem(ListaObservaciones.get(y).getDetalle()));
                }
            }
            Informe.add(new ListadoPDF(items).getListado());
            items.clear();
        }

        Informe.close();
    }

}
