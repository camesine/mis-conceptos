package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Concepto;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


public class ConceptoDAO extends GenericDAOimp<Concepto, Number> implements IConceptoDAO {

    @Override
    public List<Concepto> BuscarContenido(int contenido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Concepto> lista = session.createQuery("from Concepto where contenido_id ='" + contenido + "'").list();
        session.getTransaction().commit();
        return lista;
    }
    
   
}

    
    
    
    

