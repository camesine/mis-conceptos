package cl.preguntame.service;

import cl.preguntame.dao.ObservacionDAO;
import cl.preguntame.model.Observacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservacionService implements IObservacionService {

    ObservacionDAO Acceso;

    public ObservacionService() {
        Acceso = new ObservacionDAO();
    }

    @Override
    public int GuardarObservacion(Observacion o) {
        return Acceso.Guardar(o);

    }

    @Override
    public void ActualizarObservacion(Observacion o) {
        Acceso.Actualizar(o);
    }

    @Override
    public void EliminarObservacion(Observacion o) {
        Acceso.Eliminar(o);
    }

    @Override
    public Observacion BuscarObservacion(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Observacion> ListarObservacion() {
        return Acceso.Listar();
    }

    @Override
    public List<Observacion> BuscarObservacionConcepto(int concepto_id) {
        return Acceso.BuscarConcepto(concepto_id);
    }

    @Override
    public List<Observacion> BuscarObservacionContenido(int contenido_id) {
        List<Observacion> lista = Acceso.BuscarContenido(contenido_id);

        Iterator itr = lista.iterator();

        List<Observacion> Observaciones = new ArrayList<Observacion>();

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Observacion obs = (Observacion) obj[0];
            Observaciones.add(obs);
        }

        return Observaciones;
    }
    

}
