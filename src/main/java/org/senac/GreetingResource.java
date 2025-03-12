package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/myEntity")
    public List<MyEntity> findAll() {
        return MyEntity.listAll();
    }

    @GET
    @Path("{id}")
    public MyEntity findById(@PathParam("id") int id) {
        return MyEntity.findById(id);
    }

    @POST
    @Transactional
    public MyEntity create(MyEntity entity) {
        MyEntity.persist(entity);
        return entity;
    }


    @PUT
    @Path("{id}")
    @Transactional
    public MyEntity update(@PathParam("id") int id, MyEntity entity) {
        MyEntity currentEntity = MyEntity.findById(id);

        currentEntity.setField(
                entity.getField()
        );

        return currentEntity;
    }
}
