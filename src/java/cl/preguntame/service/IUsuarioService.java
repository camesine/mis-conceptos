package cl.preguntame.service;

import cl.preguntame.model.Usuario;
import java.util.List;

public interface IUsuarioService {

    public boolean GuardarUsuario(Usuario u);

    public void ActualizarUsuario(Usuario u);

    public void EliminarUsuario(Usuario u);

    public Usuario BuscarUsuario(int id);
    
    public List<Usuario> ListarUsuarios();
    
    public boolean Login(String correo, String pass);
}
