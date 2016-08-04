package cl.preguntame.service;

import cl.preguntame.model.Observacion;
import java.util.List;

public interface IObservacionService {
    
    public int GuardarObservacion(Observacion o);

    public void ActualizarObservacion(Observacion o);

    public void EliminarObservacion(Observacion o);

    public Observacion BuscarObservacion(int id);
    
    public List<Observacion> ListarObservacion();
        
    public List<Observacion> BuscarObservacionConcepto(int concepto_id);
       
    public List<Observacion> BuscarObservacionContenido(int contenido_id);
    
}