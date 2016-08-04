package cl.preguntame.service;

import cl.preguntame.dao.ConceptoDAO;
import cl.preguntame.model.Concepto;
import java.util.List;

public class ConceptoService implements IConceptoService {

    ConceptoDAO Acceso;

    public ConceptoService() {
        Acceso = new ConceptoDAO();
    }

    @Override
    public int GuardarConcepto(Concepto c) {
        return Acceso.Guardar(c);
    }

    @Override
    public void ActualizarConcepto(Concepto c) {
        Acceso.Actualizar(c);
    }

    @Override
    public void EliminarConcepto(Concepto c) {
        Acceso.Eliminar(c);
    }

    @Override
    public Concepto BuscarConcepto(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Concepto> ListarConcepto() {
        return Acceso.Listar();
    }

    @Override
    public List<Concepto> BuscarConceptoContenido(int contenido_id) {
        return Acceso.BuscarContenido(contenido_id);
    }

}
