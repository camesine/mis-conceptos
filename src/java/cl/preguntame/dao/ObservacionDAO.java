package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Observacion;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ObservacionDAO extends GenericDAOimp<Observacion, Number>  implements IObservacionDAO{

    @Override
    public List<Observacion> BuscarConcepto(int concepto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Observacion> lista = session.createQuery("from Observacion where concepto_id ='" + concepto + "'").list();
        session.getTransaction().commit();
        return lista;
    }
    
    @Override
    public List<Observacion> BuscarContenido(int contenido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Observacion> lista = session.createQuery("from Observacion obs join obs.concepto con where obs.concepto.id = con.id and con.contenido.id = '" + contenido + "' ").list();
        session.getTransaction().commit();
        return lista;
    }
    
}
