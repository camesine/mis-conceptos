package cl.preguntame.controller;

import cl.preguntame.model.Concepto;
import cl.preguntame.model.Definicion;
import cl.preguntame.service.DefinicionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/definicion")
public class DefinicionController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Definicion d = new Definicion();
        d.setDetalle(req.getParameter("detalle"));

        Concepto c = new Concepto();
        c.setId(Integer.parseInt(req.getParameter("concepto_id")));

        d.setConcepto(c);

        return String.valueOf(new DefinicionService().GuardarDefinicion(d));

    }

    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Definicion> Buscar(HttpServletRequest req) {

        DefinicionService acceso = new DefinicionService();
        List<Definicion> lista = acceso.BuscarDefinicionConcepto(Integer.parseInt(req.getParameter("concepto_id")));
        List<Definicion> retorno = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            Definicion d = new Definicion();
            d.setId(lista.get(i).getId());
            d.setDetalle(lista.get(i).getDetalle());
            retorno.add(d);

        }

        return retorno;

    }
    
    
    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        DefinicionService acceso = new DefinicionService();
        Definicion d = new Definicion();
        
        d.setId(Integer.parseInt(req.getParameter("id")));
        d.setDetalle(req.getParameter("detalle"));
        
        Concepto c = new Concepto();
        c.setId(Integer.parseInt(req.getParameter("concepto_id")));
        
        d.setConcepto(c);
        
        acceso.ActualizarDefinicion(d);
        
        
        return "ok";
        
        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        DefinicionService acceso = new DefinicionService();
        Definicion d = new Definicion();
        d.setId(Integer.parseInt(req.getParameter("id")));
        d.setDetalle("vacio");
        Concepto c = new Concepto();
        c.setId(0000);
        
        d.setConcepto(c);
        
        acceso.EliminarDefinicion(d);
        
        
        return "ok";
        
        
    }
    
    
    
}
