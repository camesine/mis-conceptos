package cl.preguntame.service;

import cl.preguntame.dao.ContenidoDAO;
import cl.preguntame.model.Contenido;
import java.util.List;

public class ContenidoService implements IContenidoService {

    ContenidoDAO Acceso;

    public ContenidoService() {
        Acceso = new ContenidoDAO();
    }

    @Override
    public int GuardarContenido(Contenido c) {
        return Acceso.Guardar(c);
    }

    @Override
    public void ActualizarContenido(Contenido c) {
        Acceso.Actualizar(c);
    }

    @Override
    public void EliminarContenido(Contenido c) {
        Acceso.Eliminar(c);
    }

    @Override
    public Contenido BuscarContenido(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Contenido> ListarContenido() {
        return Acceso.Listar();
    }
    
    
}
