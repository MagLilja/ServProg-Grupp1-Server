package se.yrgo.dataaccess;

import se.yrgo.domain.Message;
import se.yrgo.domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @Author - Magnus Lilja
 */
@Stateless
@Default
public class MessageDataAccessProductionVersion implements MessageDataAccess {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void insert(Message newMessage) {
            em.persist(newMessage);
    }

    @Override
    public List<Message> findAll() {
        TypedQuery<Message> query = em.createQuery("select message from Message message", Message.class);
        return query.getResultList();
    }

    @Override
    public List<Message> findByAuthor(Profile author) {
        return null;
    }
//
//    @Override
//    public List<Message> findBySurname(String surname) {
//        Query q = em.createQuery("select message from Message employee where employee.surname = :surname");
//        q.setParameter("surname", surname);
//        return q.getResultList();
//    }

    @Override
    public Message findById(int id) {
        Query q = em.createQuery("select message from Message message where message.id = :id");
        q.setParameter("id", id);
        return (Message)q.getSingleResult() ;
    }
}
