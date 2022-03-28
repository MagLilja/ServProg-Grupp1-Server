package se.yrgo.rest;

import se.yrgo.domain.Message;
import se.yrgo.service.MessageManagementService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    private MessageManagementService service;

    @GET
    public List<Message> getAllMessages() {
        return service.getAllMessages();
    }

    @GET
    @Path("{employeeNo}")
    public Message findEmployeeById(@PathParam("employeeNo") int id) {
        return service.getById(id);
    }

    @POST
    @Produces("application/JSON")
    @Consumes("application/JSON")
    public Message createEmployee(Message message) {
        service.registerMessage(message);
        return message;
    }

}
