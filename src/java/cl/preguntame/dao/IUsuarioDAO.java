package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAO;
import cl.preguntame.model.Usuario;
import java.util.List;

public interface IUsuarioDAO extends GenericDAO<Usuario, Number> {
    
    List<Usuario> Buscar(String correo);
    boolean Login(String correo, String pass);
    
}
