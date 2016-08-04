package cl.preguntame.service;

import cl.preguntame.dao.TipoRelacionDAO;
import cl.preguntame.model.TipoRelacion;
import java.util.List;

public class TipoRelacionService implements ITipoRelacionService {

    TipoRelacionDAO Acceso;

    public TipoRelacionService() {
        Acceso = new TipoRelacionDAO();
    }
    
    @Override
    public int GuardarTipoRelacion(TipoRelacion t) {
        return Acceso.Guardar(t);
    }

    @Override
    public void ActualizarTipoRelacion(TipoRelacion t) {
        Acceso.Actualizar(t);
    }

    @Override
    public void EliminarTipoRelacion(TipoRelacion t) {
        Acceso.Eliminar(t);
    }

    @Override
    public TipoRelacion BuscarTipoRelacion(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<TipoRelacion> ListarTipoRelacion() {
        return Acceso.Listar();
    }

}
