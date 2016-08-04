package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Definicion;
import java.util.List;



public interface IDefinicionDAO extends GenericDAO<Definicion, Number>{
    
       List<Definicion> BuscarConcepto(int concepto);
       List<Definicion> BuscarContenido(int contenido);
}