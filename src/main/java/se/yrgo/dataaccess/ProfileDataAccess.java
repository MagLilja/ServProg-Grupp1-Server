package se.yrgo.dataaccess;

import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @author - Magnus Lilja
 */
@Local
public interface ProfileDataAccess {

    void insert(Profile newProfile);


    List<Profile> findAll();

    Profile findById(int id);

    List<Profile> getProfilesByQuery(String firstName, String lastName);

    void deleteProfileById(int id);

    void updateProfile();
}