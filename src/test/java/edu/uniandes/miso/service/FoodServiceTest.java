package edu.uniandes.miso.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.uniandes.miso.dto.InputFood;
import edu.uniandes.miso.entity.Food;
import edu.uniandes.miso.entity.FoodType;
import edu.uniandes.miso.repository.FoodRepository;
import edu.uniandes.miso.repository.FoodTypeRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@QuarkusTest
class FoodServiceTest {

	@Inject
	Logger log;
	@InjectMock
	FoodRepository foodRepository;
	@InjectMock
	FoodTypeRepository foodTypeRepository;
	@Inject
	FoodService foodService;

	Food food;
	FoodType foodType;
	PodamFactory factory = new PodamFactoryImpl();

	@BeforeEach
	void setUp() {
		food = factory.manufacturePojo(Food.class);
		food.setIdFood(1L);
		foodType = factory.manufacturePojo(FoodType.class);
		foodType.setIdFoodType(1L);
		food.setFoodType(foodType);

	}

	@Test
	void findAllFoodType() {
		Mockito.when(foodTypeRepository.findAll()).thenReturn(Arrays.asList(foodType));
		Response response = foodService.findAllFoodType();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void findAllFood() {
		Mockito.when(foodRepository.findAll()).thenReturn(Arrays.asList(food));
		Response response = foodService.findAll();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void createFoodType() {
		Mockito.when(foodTypeRepository.save(foodType)).thenReturn(foodType);
		Response response = foodService.createFoodType(new FoodType());
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void updateFoodType() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(1L)).thenReturn(Optional.of(foodType));
		Response response = foodService.updateFoodType(1L, foodType);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void updateFoodTypeFail() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(5L)).thenReturn(Optional.of(foodType));
		Response response = foodService.updateFoodType(5L, foodType);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void deleteFoodType() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Long id = 1L;
		foodTypeRepository.deleteById(id);
		Mockito.verify(foodTypeRepository, Mockito.times(1)).deleteById((1l));
		Response response = foodService.deleteFoodType(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	@Test
	void deleteFoodTypeFail() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Long id = 1L;
		foodTypeRepository.deleteById(id);
		Mockito.verify(foodTypeRepository, Mockito.times(1)).deleteById(1l);
		Response response = foodService.deleteFoodType(3L);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void deleteFood() {
		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		food = new Food();
		foodRepository.delete(food);
		Mockito.verify(foodRepository).delete(food);
		Response response = foodService.deleteFood(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

	}
	@Test
	void deleteFoodFail() {
		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Long id = 1L;
		foodRepository.deleteById(id);
		Mockito.verify(foodRepository, Mockito.times(1)).deleteById(1L);
		Response response = foodService.deleteFoodType(1L);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void findAll() {
		Mockito.when(foodRepository.findAll()).thenReturn(Arrays.asList(food));
		Response response = foodService.findAllFoodType();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void create() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(1L)).thenReturn(Optional.of(foodType));

		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
		Mockito.when(foodRepository.save(food)).thenReturn(food);

		InputFood inputFood = new InputFood();
		inputFood.setIdFood(1L);
		inputFood.setIdFoodType(1L);
		Response response = foodService.create(inputFood);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	@Test
	void createFail() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(1L)).thenReturn(Optional.of(foodType));

		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
		Mockito.when(foodRepository.save(food)).thenReturn(food);

		InputFood inputFood = new InputFood();
		inputFood.setIdFood(2L);
		inputFood.setIdFoodType(3L);
		Response response = foodService.create(inputFood);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void update() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(1L)).thenReturn(Optional.of(foodType));

		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
		Mockito.when(foodRepository.save(food)).thenReturn(food);

		InputFood inputFood = new InputFood();
		inputFood.setIdFood(1L);
		inputFood.setIdFoodType(1L);
		Response response = foodService.update(1L,inputFood);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void updateFail() {
		Mockito.when(foodTypeRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodTypeRepository.findById(1L)).thenReturn(Optional.of(foodType));

		Mockito.when(foodRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
		Mockito.when(foodRepository.save(food)).thenReturn(food);

		InputFood inputFood = new InputFood();
		inputFood.setIdFood(2L);
		inputFood.setIdFoodType(3L);
		Response response = foodService.update(3L,inputFood);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void anotherTest() {

	}
}