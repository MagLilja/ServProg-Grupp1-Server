package se.yrgo.service;

import se.yrgo.domain.Profile;

/**
 *
 *
 * @author - Magnus Lilja
 */
public class ProfileManagementValidator {
    private final ProfileManagementImplementation profileManagementImplementation;

    public ProfileManagementValidator(ProfileManagementImplementation profileManagementImplementation) {
        this.profileManagementImplementation = profileManagementImplementation;
    }

    public boolean validateUserName(Profile profile) {
        return profileManagementImplementation.getDao().findAll().stream()
                .map(p -> p.getUserName())
                .anyMatch(username -> username.equals(profile.getUserName()));
    }
}