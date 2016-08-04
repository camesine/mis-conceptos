package cl.preguntame.service;

import cl.preguntame.model.Contenido;
import java.util.List;


public interface IContenidoService {
    
    public int GuardarContenido(Contenido c);

    public void ActualizarContenido(Contenido c);

    public void EliminarContenido(Contenido c);

    public Contenido BuscarContenido(int id);
    
    public List<Contenido> ListarContenido();
        
    
}
