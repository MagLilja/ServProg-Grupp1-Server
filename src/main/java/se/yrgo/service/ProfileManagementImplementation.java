package se.yrgo.service;

import se.yrgo.dataaccess.ProfileDataAccess;
import se.yrgo.domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @Author - Magnus Lilja
 */
@Stateless
public class ProfileManagementImplementation implements ProfileManagementService {

    @Inject
    private ProfileDataAccess dao;

    @Override
    public void registerProfile(Profile profile) {
        dao.insert(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return dao.findAll();
    }

    @Override
    public Profile getById(int id) {
        return dao.findById(id);
    }

    @Override
    public Profile getProfileByFirstName() {
        return null;
    }

    @Override
    public List<Profile> getProfilesByFirstNameAndLastName() {
        return null;
    }

    @Override
    public List<Profile> getProfilesByQuery(String firstName, String lastName) {
        return dao.getProfilesByQuery(firstName, lastName);
    }

    @Override
    public void deleteProfileById(int id) {
        dao.deleteProfileById(id);
    }


}
