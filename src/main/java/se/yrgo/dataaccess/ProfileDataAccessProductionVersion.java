package se.yrgo.dataaccess;

import se.yrgo.domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author - Magnus Lilja
 */
@Stateless
@Default
public class ProfileDataAccessProductionVersion implements ProfileDataAccess {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Profile newProfile) {
        em.persist(newProfile);
    }

    @Override
    public List<Profile> findAll() {
        TypedQuery<Profile> query = em.createQuery("select profile from Profile profile", Profile.class);
        return query.getResultList();
    }

    @Override
    public Profile findById(int id) {
        Query q = em.createQuery("select profile from Profile profile where profile.id = :id");
        q.setParameter("id", id);
        return (Profile) q.getSingleResult();
    }

    @Override
    public List<Profile> getProfilesByQuery(String firstName, String lastName) {
        if (firstName != null && lastName == null) {
            TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.firstName like :firstname", Profile.class);
            query.setParameter("firstname", "%" + firstName + "%");
            return query.getResultList();
        } else if (firstName == null && lastName != null) {
            TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.lastName like :lastname", Profile.class);
            query.setParameter("lastname", "%" + lastName + "%");
            return query.getResultList();
        } else if (firstName != null) {
            TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.lastName like :lastname AND profile.firstName like :firstname", Profile.class);
            query.setParameter("lastname", "%" + lastName + "%");
            query.setParameter("firstname", "%" + firstName + "%");
            return query.getResultList();
        } else {
            return findAll();
        }
    }

    @Override
    public void deleteProfileById(int id) {
        Profile profile = em.find(Profile.class, id);
        em.remove(profile);
    }

    @Override
    public void updateProfile() {
        // TODO
    }
}
