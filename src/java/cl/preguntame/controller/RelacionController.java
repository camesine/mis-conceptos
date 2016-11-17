package cl.preguntame.controller;

import cl.preguntame.model.Concepto;
import cl.preguntame.model.Relacion;
import cl.preguntame.model.TipoRelacion;
import cl.preguntame.service.ConceptoService;
import cl.preguntame.service.RelacionService;
import cl.preguntame.service.TipoRelacionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/relacion")
public class RelacionController {

    @ResponseBody
    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String Nuevo(HttpServletRequest req) {

        Relacion r = new Relacion();

        Concepto c = new Concepto();
        c.setId(Integer.parseInt(req.getParameter("concepto_id")));

        r.setConceptoByConcepto1(c);
        r.setConceptoByConcepto2(c);
        r.setTipoRelacion(new TipoRelacionService().ListarTipoRelacion().get(0));

        return String.valueOf(new RelacionService().GuardarRelacion(r));

    }

    @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<Relacion> Buscar(HttpServletRequest req) {

        RelacionService acceso = new RelacionService();
        List<Relacion> lista = acceso.BuscarRelacionConcepto(Integer.parseInt(req.getParameter("concepto_id")));
        List<Relacion> retorno = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            Relacion r = new Relacion();
            r.setId(lista.get(i).getId());
            
            Concepto c1 = new Concepto();
            c1.setId(lista.get(0).getConceptoByConcepto1().getId());
            
            Concepto c2 = new Concepto();
            c2.setId(lista.get(0).getConceptoByConcepto2().getId());
            
            
            r.setConceptoByConcepto1(c1);
            r.setConceptoByConcepto2(c2);
            
            TipoRelacion t = new TipoRelacion();
            t.setId(lista.get(i).getTipoRelacion().getId());
            
            r.setTipoRelacion(t);
            
            retorno.add(r);

        }
      
       
       
        return retorno;
}
    
    
    @ResponseBody
    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String Editar(HttpServletRequest req) {
        
        RelacionService acceso = new RelacionService();
        
        Relacion r = new Relacion();
        r.setId(Integer.parseInt(req.getParameter("IdRelacion")));
        
        Concepto c1 = new Concepto();
        c1.setId(Integer.parseInt(req.getParameter("IdConcepto1")));
        
        
        Concepto c2 = new Concepto();
        c2.setId(Integer.parseInt(req.getParameter("IdConcepto2")));
        
        TipoRelacion tr =  new TipoRelacion();
        tr.setId(Integer.parseInt(req.getParameter("IdTipo")));
        
        r.setConceptoByConcepto1(c1);
        r.setConceptoByConcepto2(c2);
        r.setTipoRelacion(tr);
        
        acceso.ActualizarRelacion(r);
        
       return "ok";
        
    }
    
     @ResponseBody
    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public String Eliminar(HttpServletRequest req) {

        RelacionService acceso = new RelacionService();
        Relacion r = new Relacion();
        r.setId(Integer.parseInt(req.getParameter("id")));
      
        Concepto c = new Concepto();
        c.setId(0000);
        
        r.setConceptoByConcepto1(c);
        r.setConceptoByConcepto2(c);
        
        TipoRelacion t = new TipoRelacion();
        t.setId(000);
        
        r.setTipoRelacion(t);
        
        acceso.EliminarRelacion(r);
        
        
        return "ok";
        
        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/relacionConceptual", method = RequestMethod.POST)
    public String RelacionConceptual(HttpServletRequest req) {
        
        
        
        ConceptoService accesoConcepto = new ConceptoService();
        List<Concepto> listaConcepto = accesoConcepto.BuscarConceptoContenido(Integer.parseInt(req.getParameter("contenido_id")));
        
        String DefinicionConceptos = "";
        
        for(int i = 0; i < listaConcepto.size(); i++){
            DefinicionConceptos += "var v"+listaConcepto.get(i).getId()+" = graph.insertVertex(parent, null, '"+listaConcepto.get(i).getNombre().toUpperCase()+"', 0, 0, 150, 50);";
        }
        
        RelacionService accesoRelaciones = new RelacionService();
        List<Relacion> listaRelaciones = accesoRelaciones.BuscarRelacionContenido(Integer.parseInt(req.getParameter("contenido_id")));
        
        String DefinicionRelaciones = "";
        
        for(int i = 0; i < listaRelaciones.size(); i++){
            DefinicionRelaciones += "var e"+i+" = graph.insertEdge(parent, null, '"+listaRelaciones.get(i).getTipoRelacion().getNombre().toUpperCase()+"', v"+listaRelaciones.get(i).getConceptoByConcepto1().getId()+", v"+listaRelaciones.get(i).getConceptoByConcepto2().getId()+");";
        }
          
        
        return DefinicionConceptos + " " + DefinicionRelaciones;
        
    }
    /*
    public static void main(String[] args) {
        
        
        ConceptoService accesoConcepto = new ConceptoService();
        List<Concepto> listaConcepto = accesoConcepto.BuscarConceptoContenido(46);
        
        String DefinicionConceptos = "";
        
        for(int i = 0; i < listaConcepto.size(); i++){
            DefinicionConceptos += "var v"+listaConcepto.get(i).getId()+" = graph.insertVertex(parent, null, '"+listaConcepto.get(i).getNombre()+"', 0, 0, 80, 30);";
        }
        
        System.out.println(DefinicionConceptos);
        
        
        
        RelacionService accesoRelaciones = new RelacionService();
        List<Relacion> listaRelaciones = accesoRelaciones.BuscarRelacionContenido(46);
        
        String DefinicionRelaciones = "";
        
        for(int i = 0; i < listaRelaciones.size(); i++){
            DefinicionRelaciones += "var e"+i+" = graph.insertEdge(parent, null, '"+listaRelaciones.get(i).getTipoRelacion().getNombre()+"', v"+listaRelaciones.get(i).getConceptoByConcepto1().getId()+", v"+listaRelaciones.get(i).getConceptoByConcepto2().getId()+");";
        }
          System.out.println(DefinicionRelaciones);
    }
    */
}
