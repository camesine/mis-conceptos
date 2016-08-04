package cl.preguntame.service;

import cl.preguntame.dao.UsuarioDAO;
import cl.preguntame.model.Usuario;
import java.util.List;

public class UsuarioService implements IUsuarioService {

    private UsuarioDAO Acceso;

    public UsuarioService() {
        Acceso = new UsuarioDAO();
    }

    @Override
    public boolean GuardarUsuario(Usuario u) {

        if (this.BuscarUsuario(u.getCorreo()).isEmpty()) {
            Acceso.Guardar(u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ActualizarUsuario(Usuario u) {
        Acceso.Actualizar(u);
    }

    @Override
    public void EliminarUsuario(Usuario u) {
        Acceso.Eliminar(u);
    }

    @Override
    public List<Usuario> ListarUsuarios() {
        return Acceso.Listar();
    }

    @Override
    public Usuario BuscarUsuario(int id) {
        return Acceso.Buscar(id);
    }

    public List<Usuario> BuscarUsuario(String correo) {
        return Acceso.Buscar(correo);
    }

    public boolean Login(String correo, String pass) {
        return Acceso.Login(correo, pass);
    }

  
    
}
