package cl.preguntame.service;

import cl.preguntame.dao.DefinicionDAO;
import cl.preguntame.model.Definicion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefinicionService implements IDefinicionService {

    DefinicionDAO Acceso;

    public DefinicionService() {
        Acceso = new DefinicionDAO();
    }

    @Override
    public int GuardarDefinicion(Definicion d) {
        return Acceso.Guardar(d);
    }

    @Override
    public void ActualizarDefinicion(Definicion d) {
        Acceso.Actualizar(d);
    }

    @Override
    public void EliminarDefinicion(Definicion d) {
        Acceso.Eliminar(d);
    }

    @Override
    public Definicion BuscarDefinicion(int id) {
        return Acceso.Buscar(id);
    }

    @Override
    public List<Definicion> ListarDefinicion() {
        return Acceso.Listar();
    }

    @Override
    public List<Definicion> BuscarDefinicionConcepto(int concepto_id) {
        return Acceso.BuscarConcepto(concepto_id);
    }

    @Override
    public List<Definicion> BuscarDefinicionContenido(int contenido_id) {
       List<Definicion> lista = Acceso.BuscarContenido(contenido_id);
        
        Iterator itr = lista.iterator();
        
      List<Definicion> Definiciones = new ArrayList<Definicion>();

        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Definicion def = (Definicion) obj[0]; 
            Definiciones.add(def);
        }
        
        return Definiciones;

    }
    
    
}
