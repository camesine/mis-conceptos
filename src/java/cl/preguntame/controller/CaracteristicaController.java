package cl.preguntame.controller;

import cl.preguntame.model.Caracteristica;
import cl.preguntame.model.Concepto;
import cl.preguntame.service.CaracteristicaService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Caracteristica car = new Caracteristica();
        car.setDetalle(req.getParameter("detalle"));

        Concepto c = new Concepto();
        c.setId(Integer.parseInt(req.getParameter("concepto_id")));

        car.setConcepto(c);

        return String.valueOf(new CaracteristicaService().GuardarCaracteristica(car));

    }
    
    
    
    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Caracteristica> Buscar(HttpServletRequest req) {

        CaracteristicaService acceso = new CaracteristicaService();
        List<Caracteristica> lista = acceso.BuscarCaracteristicaConcepto(Integer.parseInt(req.getParameter("concepto_id")));
        List<Caracteristica> retorno = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            Caracteristica d = new Caracteristica();
            d.setId(lista.get(i).getId());
            d.setDetalle(lista.get(i).getDetalle());
            retorno.add(d);

        }

        return retorno;

    }
    
    
    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {

        CaracteristicaService acceso = new CaracteristicaService();
        Caracteristica c = new Caracteristica();
        
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setDetalle(req.getParameter("detalle"));
        
        Concepto concepto = new Concepto();
        concepto.setId(Integer.parseInt(req.getParameter("concepto_id")));
        
        c.setConcepto(concepto);
        
        acceso.ActualizarCaracteristica(c);
        
        
        return "ok";
        
        
    }
    
    @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        CaracteristicaService acceso = new CaracteristicaService();
        Caracteristica c = new Caracteristica();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setDetalle("vacio");
        Concepto concepto = new Concepto();
        concepto.setId(0000);
        
        c.setConcepto(concepto);
        
        acceso.EliminarCaracteristica(c);
        
        
        return "ok";
        
        
    }
   
   
}
