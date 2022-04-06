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
     *
     * @param profile
     * @returns true if a profiles username already exists in the database.
     */
    public boolean isInvalidUsername(Profile profile) {
        return profileManagementImplementation.getDao().findAll().stream()
                .map(p -> p.getUserName())
                .anyMatch(username -> username.equals(profile.getUserName()));
    }
}