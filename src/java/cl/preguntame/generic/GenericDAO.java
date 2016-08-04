package cl.preguntame.generic;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<Entity, PK extends Serializable> {

    Integer Guardar(Entity t);

    void Actualizar(Entity t);

    Entity Buscar(PK id);

    void Eliminar(Entity t);

    List<Entity> Listar();
}
