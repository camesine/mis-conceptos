package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Caracteristica;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class CaracteristicaDAO extends GenericDAOimp<Caracteristica, Number> implements ICaracteristicaDAO {

    @Override
    public List<Caracteristica> BuscarConcepto(int concepto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Caracteristica> lista = session.createQuery("from Caracteristica where concepto_id ='" + concepto + "'").list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public List<Caracteristica> BuscarContenido(int contenido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Caracteristica> lista = session.createQuery("from Caracteristica car join car.concepto con where car.concepto.id = con.id and con.contenido.id = '" + contenido + "' ").list();
        session.getTransaction().commit();
        return lista;
    }

}
