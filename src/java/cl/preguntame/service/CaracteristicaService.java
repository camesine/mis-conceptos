package cl.preguntame.service;

import cl.preguntame.dao.CaracteristicaDAO;
import cl.preguntame.model.Caracteristica;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CaracteristicaService implements ICaracteristicaService {

    CaracteristicaDAO Acceso;

    public CaracteristicaService() {
        Acceso = new CaracteristicaDAO();
    }

    @Override
    public int GuardarCaracteristica(Caracteristica c) {
        return Acceso.Guardar(c);

    }

    @Override
    public void ActualizarCaracteristica(Caracteristica c) {
        Acceso.Actualizar(c);
    }

    @Override
    public void EliminarCaracteristica(Caracteristica c) {
        Acceso.Eliminar(c);
    }

    @Override
    public Caracteristica BuscarCaracteristica(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Caracteristica> ListarCaracteristica() {
        return Acceso.Listar();
    }

    @Override
    public List<Caracteristica> BuscarCaracteristicaConcepto(int concepto_id) {
        return Acceso.BuscarConcepto(concepto_id);
    }

    @Override
    public List<Caracteristica> BuscarCaracteristicaContenido(int contenido_id) {
        List<Caracteristica> lista = Acceso.BuscarContenido(contenido_id);

        Iterator itr = lista.iterator();

        List<Caracteristica> Caracteristicas = new ArrayList<Caracteristica>();

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Caracteristica car = (Caracteristica) obj[0];
            Caracteristicas.add(car);
        }

        return Caracteristicas;
    }
    

}
