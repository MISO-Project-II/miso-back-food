package edu.uniandes.miso.service;

import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uniandes.miso.dto.InputFood;
import edu.uniandes.miso.dto.response.Reply;
import edu.uniandes.miso.entity.Food;
import edu.uniandes.miso.entity.FoodType;
import edu.uniandes.miso.repository.FoodRepository;
import edu.uniandes.miso.repository.FoodTypeRepository;
import edu.uniandes.miso.util.ObjectMapper;

@Path("foods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodService {

	@Inject
	FoodRepository repository;
	@Inject
	FoodTypeRepository foodTypeRepository;

	@GET
	@Path("type")
	public Response findAllFoodType() {
		return Reply.ok(foodTypeRepository.findAll());
	}
	@POST
	@Path("type")
	public Response createFoodType(FoodType foodType) {
		foodType.setIdFoodType(null);
		FoodType getFoodType = foodTypeRepository.save(foodType);
		return Reply.ok(getFoodType);
	}
	@PUT
	@Path("type/{id}")
	public Response updateFoodType(@PathParam("id") Long idFoodType, FoodType foodType) {
		if (foodTypeRepository.existsById(idFoodType)) {
			foodType.setIdFoodType(idFoodType);
			Optional<FoodType> findFoodType = foodTypeRepository.findById(idFoodType);
			return Reply.ok(findFoodType);
		}
		return Reply.notFound(null);
	}

	@DELETE
	@Path("type/{id}")
	public Response deleteFoodType(@PathParam("id") Long idFoodType) {
		if (foodTypeRepository.existsById(idFoodType)) {
			foodTypeRepository.deleteById(idFoodType);
			return Reply.ok(null);
		}
		return Reply.notFound(null);
	}

	@DELETE
	@Path("{id}")
	public Response deleteFood(@PathParam("id") Long idFood){
		if(repository.existsById(idFood)){
			repository.deleteById(idFood);
			return Reply.ok(null);
		}
		return Reply.notFound(null);
	}

	@GET
	public Response findAll() {
		return Reply.ok(repository.findAll());
	}

	@POST
	public Response create(InputFood inputFood) {
		return getResponse(inputFood);
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long idFood, InputFood inputFood) {
		return getResponse(inputFood);
	}

	private Response getResponse(InputFood inputFood) {
		if (foodTypeRepository.existsById(inputFood.getIdFoodType())) {
			Optional<FoodType> getFoodType = foodTypeRepository.findById(inputFood.getIdFoodType());
			Food getFood = ObjectMapper.mappingObject(inputFood, Food.class);
			getFood.setListFoodType(Arrays.asList(getFoodType.get()));
			repository.save(getFood);
			return Reply.ok(getFood);
		}
		return Reply.notFound(null);
	}
}
