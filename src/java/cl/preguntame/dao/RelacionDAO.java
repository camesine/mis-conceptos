package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Relacion;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class RelacionDAO extends GenericDAOimp<Relacion, Number> implements IRelacionDAO {

    @Override
    public List<Relacion> BuscarConcepto(int concepto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Relacion> lista = session.createQuery("from Relacion where concepto1 ='" + concepto + "'").list();
        session.getTransaction().commit();
        return lista;
    }
    
    @Override
    public List<Relacion> BuscarContenido(int contenido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Relacion> lista = session.createQuery("from Relacion rel join rel.conceptoByConcepto1 con where rel.conceptoByConcepto1.id = con.id and con.contenido.id = '" + contenido + "' ").list();
        session.getTransaction().commit();
        return lista;
    }

}
