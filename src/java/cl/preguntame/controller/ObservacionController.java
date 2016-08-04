package cl.preguntame.controller;

import cl.preguntame.model.Concepto;
import cl.preguntame.model.Observacion;
import cl.preguntame.service.ObservacionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/observacion")
public class ObservacionController {
    
    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Observacion o = new Observacion();
        o.setDetalle(req.getParameter("detalle"));

        Concepto c = new Concepto();
        c.setId(Integer.parseInt(req.getParameter("concepto_id")));

        o.setConcepto(c);

        return String.valueOf(new ObservacionService().GuardarObservacion(o));

    }
    
    
    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Observacion> Buscar(HttpServletRequest req) {

        ObservacionService acceso = new ObservacionService();
        List<Observacion> lista = acceso.BuscarObservacionConcepto(Integer.parseInt(req.getParameter("concepto_id")));
        List<Observacion> retorno = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            Observacion o = new Observacion();
            o.setId(lista.get(i).getId());
            o.setDetalle(lista.get(i).getDetalle());
            retorno.add(o);
        }

        return retorno;

    }
    
    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        ObservacionService acceso = new ObservacionService();
        Observacion o = new Observacion();
        
        o.setId(Integer.parseInt(req.getParameter("id")));
        o.setDetalle(req.getParameter("detalle"));
        
        Concepto concepto = new Concepto();
        concepto.setId(Integer.parseInt(req.getParameter("concepto_id")));
        
        o.setConcepto(concepto);
        
        acceso.ActualizarObservacion(o);
        
        
        return "ok";
        
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        ObservacionService acceso = new ObservacionService();
        Observacion o = new Observacion();
        o.setId(Integer.parseInt(req.getParameter("id")));
        o.setDetalle("vacio");
        Concepto concepto = new Concepto();
        concepto.setId(0000);
        
        o.setConcepto(concepto);
        
        acceso.EliminarObservacion(o);
        
        
        return "ok";
        
        
    }
    
}
