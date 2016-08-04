package cl.preguntame.service;

import cl.preguntame.model.TipoRelacion;
import java.util.List;

public interface ITipoRelacionService {

    public int GuardarTipoRelacion(TipoRelacion t);

    public void ActualizarTipoRelacion(TipoRelacion t);

    public void EliminarTipoRelacion(TipoRelacion t);

    public TipoRelacion BuscarTipoRelacion(int id);

    public List<TipoRelacion> ListarTipoRelacion();

}
