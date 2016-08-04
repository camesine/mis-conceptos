package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Caracteristica;
import java.util.List;



public interface ICaracteristicaDAO extends GenericDAO<Caracteristica, Number>{
    
       List<Caracteristica> BuscarConcepto(int concepto);
       List<Caracteristica> BuscarContenido(int contenido);
   
}