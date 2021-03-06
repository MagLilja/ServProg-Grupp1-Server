package se.yrgo.service;

import se.yrgo.dataaccess.ProfileDataAccess;
import se.yrgo.dataaccess.ProfileNotFoundException;
import se.yrgo.domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Class implementing the ProfileManagementService interface.
 *
 * @author - Magnus Lilja
 */
@Stateless
public class ProfileManagementImplementation implements ProfileManagementService {

    private ProfileManagementValidator validator = new ProfileManagementValidator(this);
    @Inject
    private ProfileDataAccess dao;


    /**
     * Method to call for the dao to register a new profile.
     * Checks whether the profile's username is valid.
     *
     */
    @Override
    public void registerProfile(Profile profile) throws ProfileUserNameAlreadyExistsException {
        if (validator.isValidUsername(profile)) {
            dao.insert(profile);
        }
    }

    public ProfileDataAccess getDao() {
        return dao;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return dao.findAll();
    }

    @Override
    public Profile getById(int id) throws ProfileNotFoundException {
        return dao.findById(id);
    }

    @Override
    public List<Profile> getProfilesByQuery(String firstName, String lastName) {
        return dao.getProfilesByQuery(firstName, lastName);
    }

    @Override
    public void deleteProfileById(int id) {
        dao.deleteProfileById(id);
    }

    @Override
    public void updateProfile(int id, Profile profile) throws ProfileNotFoundException {
        dao.updateProfile(id, profile);
    }

    @Override
    public Profile getProfileByUsername(String userName) throws ProfileNotFoundException {
        return dao.getProfileByUsername(userName);
    }


}
