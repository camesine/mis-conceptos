package cl.preguntame.service;

import cl.preguntame.dao.RelacionDAO;
import cl.preguntame.model.Relacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelacionService implements IRelacionService {

    RelacionDAO Acceso;

    public RelacionService() {
        Acceso = new RelacionDAO();
    }

    @Override
    public int GuardarRelacion(Relacion d) {
        return Acceso.Guardar(d);
    }

    @Override
    public void ActualizarRelacion(Relacion d) {
        Acceso.Actualizar(d);
    }

    @Override
    public void EliminarRelacion(Relacion d) {
        Acceso.Eliminar(d);
    }

    @Override
    public Relacion BuscarRelacion(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Relacion> ListarRelacion() {
        return Acceso.Listar();
    }

    @Override
    public List<Relacion> BuscarRelacionConcepto(int concepto_id) {
        return Acceso.BuscarConcepto(concepto_id);
    }
    
    @Override
    public List<Relacion> BuscarRelacionContenido(int contenido_id) {
        List<Relacion> lista = Acceso.BuscarContenido(contenido_id);

        Iterator itr = lista.iterator();

        List<Relacion> Relaciones = new ArrayList<Relacion>();

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Relacion rel = (Relacion) obj[0];
            Relaciones.add(rel);
        }

        return Relaciones;
    }
    
}