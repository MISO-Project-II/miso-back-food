package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.response.ResponseService;
import edu.uniandes.miso.entity.Food;
import edu.uniandes.miso.entity.FoodType;
import edu.uniandes.miso.repository.FoodRepository;
import edu.uniandes.miso.repository.FoodTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("foods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodService {

    @Inject
    FoodRepository repository;
    @Inject
    FoodTypeRepository foodTypeRepository;
    ResponseService responseService = new ResponseService();

    @GET
    @Path("type")
    public Response findAllFoodType() {
        responseService.setSuccess(true);
        responseService.setMessage("OK");
        responseService.setResult(foodTypeRepository.findAll());
        return Response.status(Response.Status.OK).entity(responseService).build();
    }
    @POST
    @Path("type")
    public Response createFoodType(FoodType foodType) {
        foodType.setIdFoodType(null);
        FoodType getFoodType = foodTypeRepository.save(foodType);
        responseService.setSuccess(true);
        responseService.setMessage("OK");
        responseService.setResult(getFoodType);
        return Response.status(Response.Status.OK).entity(responseService).build();
    }
    @PUT
    @Path("type/{id}")
    public Response updateFoodType(@PathParam("id")  Long idFoodType, FoodType foodType) {
        if(foodTypeRepository.existsById(idFoodType)){
            foodType.setIdFoodType(idFoodType);
            responseService.setSuccess(true);
            responseService.setMessage("OK");
            foodTypeRepository.findById(idFoodType).ifPresent(foodTypeRepository::save);
            return Response.status(Response.Status.OK).entity(foodTypeRepository.save(foodType)).build();
        }
        return null;
    }

    @GET
    public Response findAll() {
        responseService.setSuccess(true);
        responseService.setMessage("OK");
        responseService.setResult(repository.findAll());
        return Response.status(Response.Status.OK).entity(responseService).build();
    }

    @POST
    public Response create(Food food) {
        food.setIdFood(null);
        Food getFood = repository.save(food);
        repository.save(getFood);
        responseService.setSuccess(true);
        responseService.setMessage("OK");
        responseService.setResult(getFood);
        return Response.status(Response.Status.OK).entity(responseService).build();
    }


}
