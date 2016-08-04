package cl.preguntame.controller;

import cl.preguntame.model.TipoRelacion;
import cl.preguntame.service.TipoRelacionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tiporelacion")
public class TipoRelacionController {

    
 @ResponseBody
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public List<TipoRelacion> Buscar(HttpServletRequest req) {
        
        TipoRelacionService acceso = new TipoRelacionService();
        List<TipoRelacion> lista = acceso.ListarTipoRelacion();
       List<TipoRelacion> retorno = new ArrayList<>();
        
        for(int i = 0; i < lista.size(); i++){
            
            TipoRelacion t = new TipoRelacion();
            t.setId(lista.get(i).getId());
            t.setNombre(lista.get(i).getNombre());
            retorno.add(t);
            
        }
      
        
        return retorno;

    }
    
    
}
