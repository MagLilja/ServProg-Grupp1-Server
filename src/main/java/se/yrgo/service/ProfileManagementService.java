package se.yrgo.service;

import se.yrgo.dataaccess.ProfileNotFoundException;
import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @author - Magnus Lilja
 */

@Local
public interface ProfileManagementService {

     void registerProfile(Profile profile) throws ProfileUserNameAlreadyExistsException;

     List<Profile> getAllProfiles();

     Profile getById(int id) throws ProfileNotFoundException;

     List<Profile> getProfilesByQuery(String firstName, String lastName);

     void deleteProfileById(int id);

     void updateProfile(int id, Profile profile) throws ProfileNotFoundException;

     Profile getProfileByUsername(String userName) throws ProfileNotFoundException;
}
