package br.com.loja.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseDAO<T> {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja-pu");
    private EntityManager em = emf.createEntityManager();
    private Class<T> clazz;

    public EntityManager getEntityManager(){
        return this.em;
    }

    public BaseDAO(Class clazz){
        this.clazz = clazz;
    }

    public T create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (PersistenceException e) {
            throw new PersistenceException("Erro ao persistir objeto");
        }
    }

    public List<T> findAll() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        return this.em.createQuery(cq).getResultList();
    }

    public T getOne(Long id){
        return  em.find(clazz, id);
    }

    public void removeAll() throws Exception {
        List<T> list = this.findAll();
        for (T t : list) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }

    public T update(T entity) throws Exception {
        try{
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }catch (PersistenceException e){
            throw new PersistenceException("Erro ao persistir objeto");
        }
    }

    public void close(){
        if(em.isOpen()){
            em.close();
        }
        if(emf.isOpen()){
            emf.close();
        }
    }
}
