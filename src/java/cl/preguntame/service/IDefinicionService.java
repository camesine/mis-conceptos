package cl.preguntame.service;

import cl.preguntame.model.Definicion;
import java.util.List;

public interface IDefinicionService {
    
    public int GuardarDefinicion(Definicion d);

    public void ActualizarDefinicion(Definicion d);

    public void EliminarDefinicion(Definicion d);

    public Definicion BuscarDefinicion(int id);
    
    public List<Definicion> ListarDefinicion();
        
    public List<Definicion> BuscarDefinicionConcepto(int concepto_id);
    
    public List<Definicion> BuscarDefinicionContenido(int contenido_id);
}