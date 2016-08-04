package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Definicion;
import cl.preguntame.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;

public class DefinicionDAO extends GenericDAOimp<Definicion, Number> implements IDefinicionDAO {

    @Override
    public List<Definicion> BuscarConcepto(int concepto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Definicion> lista = session.createQuery("from Definicion where concepto_id ='" + concepto + "'").list();
        session.getTransaction().commit();
        return lista;
    }

    @Override
    public List<Definicion> BuscarContenido(int contenido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Definicion> lista = session.createQuery("from Definicion def join def.concepto con where def.concepto.id = con.id and con.contenido.id = '" + contenido + "' ").list();
        session.getTransaction().commit();
        return lista;
    }

    
        
    

}
