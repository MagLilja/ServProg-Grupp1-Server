package se.yrgo.service;

import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @Author - Magnus Lilja
 */

@Local
public interface ProfileManagementService {
    public void registerProfile(Profile profile);

    public List<Profile> getAllProfiles();

    public Profile getById(int id);

    public Profile getProfileByFirstName();

    public List<Profile> getProfilesByFirstNameAndLastName();

    public List<Profile> getProfilesByQuery(String firstName, String lastName);

    public void deleteProfileById(int id);
}
