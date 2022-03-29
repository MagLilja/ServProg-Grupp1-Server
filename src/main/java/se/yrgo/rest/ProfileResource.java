package se.yrgo.rest;

import se.yrgo.domain.Profile;
import se.yrgo.service.ProfileManagementService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @Author - Andreas Karlsson
 */

@Stateless
@Path("/profiles")
@Produces("application/JSON")
@Consumes("application/JSON")
public class ProfileResource {

    @Inject
    private ProfileManagementService service;

    @GET
    public List<Profile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GET
    @Path("/search")
    public List<Profile> getProfilesByQuery(@QueryParam("firstname") String firstName,
                                            @QueryParam("lastname") String lastName) {
        return service.getProfilesByQuery(firstName, lastName);
    }

//    @GET
//    @Path("/query")
//    public List<Profile> getProfilesByQuery(@QueryParam("firstname") String firstName,
//                                            @QueryParam("lastname") String lastName) {
//        return service.getProfilesByFirstNameAndLastName();
//    }

//    SELECT profile FROM Profiles as profile WHERE firstName = :firstname

//    http://164.92.231.95:8080/Grupp1ServerApplication/api/profiles/query?firstname=andreas&lastname=karlsson
//    http://164.92.231.95:8080/Grupp1ServerApplication/api/profiles/query?firstname=andreas
//    http://164.92.231.95:8080/Grupp1ServerApplication/api/profiles/query?lastname=karlsson

//- Alla profiler på förnamn  /profiles/query?firstname=andreas
//- Alla profiler på efternamn
//- Alla profiler på fulla namn

    @GET
    @Path("{id}")
    public Profile findProfileById(@PathParam("id") int id) {
        return service.getById(id);
    }


    @GET
    @Path("{username}")
    public Profile findProfileByUsername(@PathParam("username") int id) {
        // TODO
        return null;
    }

    @POST
    @Produces("application/JSON")
    @Consumes("application/JSON")
    public Response createProfile(Profile profile) {
        if (profile.getLastName() == null || profile.getFirstName() == null || profile.getUserName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Status: 400 Bad Request - Required Fields Missing").build();
        }
        service.registerProfile(profile);
        return Response.status(Response.Status.ACCEPTED).entity(profile).build();
    }

}
