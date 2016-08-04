package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Concepto;
import java.util.List;


public interface IConceptoDAO extends GenericDAO<Concepto, Number>{
    
        List<Concepto> BuscarContenido(int contenido);
   
}
