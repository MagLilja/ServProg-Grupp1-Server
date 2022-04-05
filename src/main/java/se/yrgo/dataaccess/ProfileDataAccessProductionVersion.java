package se.yrgo.dataaccess;

import se.yrgo.domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO implementation class for accessing the database
 *
 * @author - Magnus Lilja
 * @author - Andreas Karlsson
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

    /**
     * Method to query the database for all profiles.
     *
     * @returns a list of profiles
     */
    @Override
    public List<Profile> findAll() {
        TypedQuery<Profile> query = em.createQuery("select profile from Profile profile", Profile.class);
        return query.getResultList();
    }

    /**
     * Method to query the database for a user with a specified ID
     *
     * @param id of the profile query
     * @throws ProfileNotFoundException
     * @returns a mathing profile or
     */
    @Override
    public Profile findById(int id) throws ProfileNotFoundException {
        Query q = em.createQuery("select profile from Profile profile where profile.id = :id");
        q.setParameter("id", id);
        try {
            return (Profile) q.getSingleResult();
        } catch (NoResultException ex) {
            throw new ProfileNotFoundException();
        }
    }


    /**
     * Method to query the database for a search by firstname and lastname
     *
     * @param firstName
     * @param lastName
     * @returns a list of matching profiles. The returned profiles must match BOTH the firstname and the lastname
     */
    @Override
    public List<Profile> getProfilesByQuery(String firstName, String lastName) {
        // if both are null return all profiles.
        if (firstName == null && lastName == null) {
            return findAll();
        }
        // if both are not null search for profiles with first and lastname matching the params.
        if (firstName != null && lastName != null) {
            TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.lastName like :lastname AND profile.firstName like :firstname", Profile.class);
            query.setParameter("lastname", "%" + lastName + "%");
            query.setParameter("firstname", "%" + firstName + "%");
            return query.getResultList();
        }
        // if firstname is not null search for firstnames matching the params
        boolean onlyFirstname = firstName != null;
        if (onlyFirstname) {
            TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.firstName like :firstname", Profile.class);
            query.setParameter("firstname", "%" + firstName + "%");
            return query.getResultList();
        }

        // only lastname search left
        TypedQuery<Profile> query = em.createQuery("select profile from Profile profile WHERE profile.lastName like :lastname", Profile.class);
        query.setParameter("lastname", "%" + lastName + "%");
        return query.getResultList();
    }

    /**
     * Method to remove a profile from the database
     * @param id of the profile to be removed
     */
    @Override
    public void deleteProfileById(int id) {
        Profile profile = em.find(Profile.class, id);
        em.remove(profile);
    }

    /**
     * Method to update a profile
     * @param id of the profile to update
     * @param profile with the changes
     * @throws ProfileNotFoundException if the profile to update is not found.
     */
    @Override
    public void updateProfile(int id, Profile profile) throws ProfileNotFoundException {
        Profile profileDB = findById(id);
        profileDB.setFirstName(profile.getFirstName());
        profileDB.setLastName(profile.getLastName());
        profileDB.setUserName(profile.getUserName());

    }

    /**
     * Method to query the database for a profile by username
     * @param userName to query for
     * @returns a profile matching the query
     * @throws ProfileNotFoundException if no matching profile exists.
     */
    @Override
    public Profile getProfileByUsername(String userName) throws ProfileNotFoundException {
        try {
            Query q = em.createQuery("select profile from Profile profile where profile.userName = :userName");
            q.setParameter("userName", userName);
            return (Profile) q.getSingleResult();
        } catch (NoResultException ex) {
            throw new ProfileNotFoundException();
        }
    }
}
