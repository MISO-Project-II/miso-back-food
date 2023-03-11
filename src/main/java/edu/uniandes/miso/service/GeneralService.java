package edu.uniandes.miso.service;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import edu.uniandes.miso.dto.FoodPlanDto;
import edu.uniandes.miso.dto.response.InputFoodPlan;
import edu.uniandes.miso.entity.FoodPlan;
import edu.uniandes.miso.repository.FoodPlanRepository;
import edu.uniandes.miso.util.ObjectMapper;

@Path("suscribe-user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class GeneralService {

	@Inject
	Logger log;
	@ConfigProperty(name = "quarkus.google.cloud.project-id")
	String projectId;
	@Inject
	CredentialsProvider credentialsProvider;

	@Inject
	FoodPlanRepository foodPlanRepository;
	private TopicName topicName;

	@POST
	public Response response(InputFoodPlan inputFoodPlan) throws IOException, InterruptedException {
		Optional<FoodPlan> foodPlan =  foodPlanRepository.findById(inputFoodPlan.getIdFoodPlan());
		if(foodPlan.isPresent()) {
			log.info(foodPlan);
			FoodPlanDto foodPlanDto = ObjectMapper.mappingObject(foodPlan.get(), FoodPlanDto.class);
			foodPlanDto.setIdUser(inputFoodPlan.getIdUser());
			String publishMsg = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(foodPlanDto);
			sendMessagePubSub(publishMsg);
			return Response.status(Response.Status.CREATED).entity(foodPlanDto).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	private void sendMessagePubSub(String msg) throws InterruptedException, IOException {
		topicName = TopicName.of(projectId, "food-plan");

		Publisher publisher = Publisher.newBuilder(topicName).setCredentialsProvider(credentialsProvider).build();
		try {
			ByteString data = ByteString.copyFromUtf8(msg);
			PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
			ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
			ApiFutures.addCallback(messageIdFuture, new ApiFutureCallback<String>() {
				public void onSuccess(String messageId) {
					log.info("Message published: " + messageId + " cotent" + msg);
				}

				public void onFailure(Throwable t) {
					log.warn("failed to publish: {0}", t);
				}
			}, MoreExecutors.directExecutor());
		} finally {
			publisher.shutdown();
			publisher.awaitTermination(1, TimeUnit.MINUTES);
		}


	}
}
