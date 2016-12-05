/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.preguntame.dao;

import cl.preguntame.generic.GenericDAOimp;
import cl.preguntame.model.Test;
import cl.preguntame.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hector
 */
public class TestDAO extends GenericDAOimp<Test, Number> implements ITestDAO{

    @Override
    public List<Test> BuscarContenido(int contenido) {
    
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Test> lista = session.createQuery("from Test where contenido_id ='" + contenido + "'").list();
        session.getTransaction().commit();
        return lista;
        
    }
    
    
}
