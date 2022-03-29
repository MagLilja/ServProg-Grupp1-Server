package se.yrgo.dataaccess;

import se.yrgo.domain.Profile;

import javax.ejb.Local;
import java.util.List;

/**
 * @Author - Magnus Lilja
 */
@Local
public interface ProfileDataAccess {

    public  void insert(Profile newProfile);

    public  List<Profile> findAll();

    public  List<Profile> findByAuthor(Profile author);

    public Profile findById(int id);

    public List<Profile> getProfilesByQuery(String firstName, String lastName);

    public void deleteProfileById(int id);
}