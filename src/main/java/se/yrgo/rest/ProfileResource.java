package se.yrgo.rest;

import se.yrgo.dataaccess.ProfileNotFoundException;
import se.yrgo.domain.Profile;
import se.yrgo.service.ProfileManagementService;
import se.yrgo.service.ProfileUserNameAlreadyExistsException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.status;

/**
 * REST resource class for a social media profile
 *
 * @author - Andreas Karlsson
 * @author - Magnus Lilja
 */
@Stateless
@Path("/profiles")
@Produces("application/JSON")
@Consumes("application/JSON")
public class ProfileResource {

    /**
     *
     */
    @Inject
    private ProfileManagementService service;

    /**
     * The base GET resource.
     *
     * @return a list of all profiles in the database
     */
    @GET
    public List<Profile> getAllProfiles() {
        return service.getAllProfiles();
    }

    /**
     * The search GET resource.
     * /search?firstname=firstName
     * or /search?lastname=lastName
     * or /search?lastname=lastName&firstname=firstName
     *
     * @param firstName - Query parameter from the URI
     * @param lastName  - Query parameter from the URI
     * @return a list of profiles with first- and/or lastnames containing the search params
     */
    @GET
    @Path("/search")
    public List<Profile> getProfilesByQuery(@QueryParam("firstname") String firstName,
                                            @QueryParam("lastname") String lastName) {
        return service.getProfilesByQuery(firstName, lastName);
    }

    /**
     * GET resource for getting a profile by ID
     *
     * @param id - the id of the profile
     * @return a profile matching the id.
     */
    @GET
    @Path("{id}")
    public Response findProfileById(@PathParam("id") int id) {
        try {
            Profile result = service.getById(id);
            return Response.ok(result).build();
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
            return status(404).build();
        }

    }

    /**
     * GET resource for getting a profile by the unique username
     *
     * @param userName - the username of the profile
     * @return a profile matching the username.
     */
    @GET
    @Path("/username/{username}")
    public Response findProfileByUsername(@PathParam("username") String userName) {
        try {
            Profile profileByUsername = service.getProfileByUsername(userName);
            return Response.ok(profileByUsername).build();
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
            return status(404).build();
        }
    }

    /**
     * POST resource for creating a new profile in the database
     * Accepts a JSON string matching the Profile class
     * If lastname, firstname or username is missing it returns a BAD REQUEST
     *
     * @param newProfile - the profile to be created
     * @return a response with the added profile
     */
    @POST
    public Response createProfile(Profile newProfile) {
        if (newProfile.getLastName() == null || newProfile.getFirstName() == null || newProfile.getUserName() == null) {
            return status(Response.Status.BAD_REQUEST).entity("Status: 400 Bad Request - Required Fields Missing").build();
        }
        try {
            service.registerProfile(newProfile);
        } catch (ProfileUserNameAlreadyExistsException e) {
            e.printStackTrace();
            return status(Response.Status.BAD_REQUEST).entity("Status: 400 Bad Request - UserName Already In Use.").build();
        }
        return status(Response.Status.ACCEPTED).entity(newProfile).build();
    }

    /**
     * DELETE resource to remove a profile from the database.
     *
     * @param id -
     */
    @DELETE
    @Path("{id}")
    public void deleteProfileById(@PathParam("id") int id) {
        service.deleteProfileById(id);
    }

    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") int id, Profile profile) {
        // TODO
        try {
            service.updateProfile(id, profile);
            return status(Response.Status.ACCEPTED)
                    .entity(service.getById(id))
                    .build();
        }
//        catch (ProfileUserNameAlreadyExistsException e) {
//            e.printStackTrace();
//            return status(Response.Status.BAD_REQUEST).entity("Status: 400 Bad Request - UserName Already In Use.").build();
//        }
        catch (ProfileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
