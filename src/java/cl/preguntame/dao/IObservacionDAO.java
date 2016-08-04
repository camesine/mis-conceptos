package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Observacion;
import java.util.List;



public interface IObservacionDAO extends GenericDAO<Observacion, Number>{
    
       List<Observacion> BuscarConcepto(int concepto);
       List<Observacion> BuscarContenido(int contenido);

}