package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Usuario;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class UsuarioDAO extends GenericDAOimp<Usuario, Number> implements IUsuarioDAO {

    @Override
    public List<Usuario> Buscar(String correo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Usuario> lista = session.createQuery("from Usuario where correo ='" + correo + "'").list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public boolean Login(String correo, String pass) {
        List<Usuario> lista = this.Buscar(correo);

        if (!lista.isEmpty()) {
            if (lista.get(0).getContrasena().equals(pass)) {
                return true;
            }
        }

        return false;
    }

}
