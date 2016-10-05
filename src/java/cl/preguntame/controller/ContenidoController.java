package cl.preguntame.controller;

import cl.preguntame.clases.Generador;
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
        PdfWriter.getInstance(Informe, res.getOutputStream());
        Informe.open();

        Paragraph titulo = new Paragraph(contenido.getNombre(), FontFactory.getFont("arial", 22, BaseColor.BLACK));
        titulo.setAlignment(Element.ALIGN_CENTER);
        Informe.add(titulo);
        char punto = 4;
        for (int i = 0; i < ListaConceptos.size(); i++) {
            Informe.add(new Paragraph("\n"));
            Informe.add(new Paragraph(ListaConceptos.get(i).getNombre().toUpperCase(), FontFactory.getFont("arial", 15, Font.BOLD)));
            
            
            
            Informe.add(new Paragraph("DEFINICIONES", FontFactory.getFont("arial", 12, Font.BOLD)));
            for (int y = 0; y < ListaDefiniciones.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaDefiniciones.get(y).getConcepto().getId()) {

                    Informe.add(new Paragraph("- " +  ListaDefiniciones.get(y).getDetalle(), FontFactory.getFont("arial", 12, BaseColor.BLACK)));

                }
            }
         
            Informe.add(new Paragraph("CARACTERISTICAS", FontFactory.getFont("arial", 12, Font.BOLD)));
            for (int y = 0; y < ListaCaracteristicas.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaCaracteristicas.get(y).getConcepto().getId()) {

                    Informe.add(new Paragraph("- " + ListaCaracteristicas.get(y).getDetalle(), FontFactory.getFont("arial", 12, BaseColor.BLACK)));

                }
            }
 
             Informe.add(new Paragraph("OBSERVACIONES", FontFactory.getFont("arial", 12, Font.BOLD)));
            for (int y = 0; y < ListaObservaciones.size(); y++) {
                if (ListaConceptos.get(i).getId() == ListaObservaciones.get(y).getConcepto().getId()) {

                    Informe.add(new Paragraph("- " + ListaObservaciones.get(y).getDetalle(), FontFactory.getFont("arial", 12, BaseColor.BLACK)));

                }
            }

        }

        Informe.close();
    }

}
