package org.example;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    public String getBrands() {
        return "success";
    }

}
