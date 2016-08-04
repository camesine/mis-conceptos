package cl.preguntame.service;

import cl.preguntame.model.Caracteristica;
import java.util.List;

public interface ICaracteristicaService {
    
    public int GuardarCaracteristica(Caracteristica d);

    public void ActualizarCaracteristica(Caracteristica d);

    public void EliminarCaracteristica(Caracteristica d);

    public Caracteristica BuscarCaracteristica(int id);
    
    public List<Caracteristica> ListarCaracteristica();
        
    public List<Caracteristica> BuscarCaracteristicaConcepto(int concepto_id);
       
    public List<Caracteristica> BuscarCaracteristicaContenido(int contenido_id);
    
}
