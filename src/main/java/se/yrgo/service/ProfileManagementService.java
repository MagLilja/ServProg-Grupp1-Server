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

	public Message getById(int id);
}
