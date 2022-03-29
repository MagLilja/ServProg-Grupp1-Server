package se.yrgo.service;

import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @author - Magnus Lilja
 */

@Local
public interface ProfileManagementService {

     void registerProfile(Profile profile);

     List<Profile> getAllProfiles();

     Profile getById(int id);

     List<Profile> getProfilesByQuery(String firstName, String lastName);

     void deleteProfileById(int id);

     void updateProfile();
}
