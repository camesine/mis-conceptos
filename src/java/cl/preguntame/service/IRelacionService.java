package cl.preguntame.service;


import cl.preguntame.model.Relacion;
import java.util.List;

public interface IRelacionService {
    
    public int GuardarRelacion(Relacion r);

    public void ActualizarRelacion(Relacion r);

    public void EliminarRelacion(Relacion r);

    public Relacion BuscarRelacion(int id);
    
    public List<Relacion> ListarRelacion();
        
    public List<Relacion> BuscarRelacionConcepto(int concepto_id);
    
    public List<Relacion> BuscarRelacionContenido(int contenido_id);
    
}