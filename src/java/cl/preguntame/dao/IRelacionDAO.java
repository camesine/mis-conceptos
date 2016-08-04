package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Relacion;
import java.util.List;

public interface IRelacionDAO extends GenericDAO<Relacion, Number> {
    List<Relacion> BuscarConcepto(int concepto);
    List<Relacion> BuscarContenido(int contenido);
}
