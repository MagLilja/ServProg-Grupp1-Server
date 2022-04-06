package se.yrgo.service;

import se.yrgo.domain.Profile;

/**
 * Class for profile management validators
 *
 * @author - Magnus Lilja
 */
public class ProfileManagementValidator {
    private final ProfileManagementImplementation profileManagementImplementation;

    public ProfileManagementValidator(ProfileManagementImplementation profileManagementImplementation) {
        this.profileManagementImplementation = profileManagementImplementation;
    }

    /**
     * @param profile
     * @returns true if a profiles username is not already used in the database.
     * @throws ProfileUserNameAlreadyExistsException if the username is already taken.
     */
    public boolean isValidUsername(Profile profile) throws ProfileUserNameAlreadyExistsException {
        if (profileManagementImplementation.getDao().findAll().stream()
                .map(Profile::getUserName)
                .anyMatch(username -> username.equals(profile.getUserName()))) {
            throw new ProfileUserNameAlreadyExistsException();
        }
        else {
            return true;
        }
    }
}