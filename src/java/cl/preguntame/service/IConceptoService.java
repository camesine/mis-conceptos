package cl.preguntame.service;


import cl.preguntame.model.Concepto;
import java.util.List;


public interface IConceptoService {
    
    public int GuardarConcepto(Concepto c);

    public void ActualizarConcepto(Concepto c);

    public void EliminarConcepto(Concepto c);

    public Concepto BuscarConcepto(int id);
    
    public List<Concepto> ListarConcepto();
    
    public List<Concepto> BuscarConceptoContenido(int contenido_id);
        
    
}
