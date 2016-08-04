package cl.preguntame.controller;

import cl.preguntame.model.Concepto;
import cl.preguntame.model.Contenido;
import cl.preguntame.service.ConceptoService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/concepto")
public class ConceptoController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {
        Concepto concepto = new Concepto();
        concepto.setNombre(req.getParameter("concepto"));

        Contenido contenido = new Contenido();
        contenido.setId(Integer.parseInt(req.getParameter("contenido")));

        concepto.setContenido(contenido);

        ConceptoService acceso = new ConceptoService();
        int id = acceso.GuardarConcepto(concepto);

        return String.valueOf(id);

    }
    
    
    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Concepto> Buscar(HttpServletRequest req) {
        
        ConceptoService acceso = new ConceptoService();
        List<Concepto> lista = acceso.BuscarConceptoContenido(Integer.parseInt(req.getParameter("seleccionado")));
        List<Concepto> retorno = new ArrayList<>();
        
        for(int i = 0; i < lista.size(); i++){
            
            Concepto c = new Concepto();
            c.setId(lista.get(i).getId());
            c.setNombre(lista.get(i).getNombre());
            retorno.add(c);
            
        }
        
        
        return retorno;

    }
    
    
     @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        Concepto concepto = new Concepto();
        concepto.setId(Integer.parseInt(req.getParameter("id")));
        concepto.setNombre("vacio");

        Contenido contenido = new Contenido();
        contenido.setId(000);

        concepto.setContenido(contenido);

        ConceptoService acceso = new ConceptoService();
        acceso.EliminarConcepto(concepto);

        return "ok";
        
        
    }
    
    
    
}
