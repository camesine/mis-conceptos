package cl.preguntame.generic;

import cl.preguntame.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

public class GenericDAOimp<Entity, K extends Serializable> implements GenericDAO<Entity, K> {

    public Class<Entity> domainClass = getDomainClass();
    private Session session;

    protected Class getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private Session getHibernateTemplate() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    @Override
    public Entity Buscar(K id) {
        Entity returnValue = (Entity) getHibernateTemplate().load(domainClass, id);
        session.getTransaction().commit();
        return returnValue;
    }

    @Override
    public void Actualizar(Entity t) {
        getHibernateTemplate().update(t);
        session.getTransaction().commit();
    }

    @Override
    public Integer Guardar(Entity t) {
        int id = (int) getHibernateTemplate().save(t);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public void Eliminar(Entity t) {
        getHibernateTemplate().delete(t);
        session.getTransaction().commit();
    }

    @Override
    public List<Entity> Listar() {
        getHibernateTemplate();
        List<Entity> lista = session.createQuery("from " + domainClass.getName()).list();
        session.getTransaction().commit();
        return lista;
    }
    
    

}
