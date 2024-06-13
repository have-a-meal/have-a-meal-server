package team.gwon.haveameal.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI haveAMealOpenApi() {
		return new OpenAPI()
			.info(new Info()
				.title("HaveAMeal API")
				.description("Have a meal API 명세서"));
	}

	@Bean
	public GroupedOpenApi memberApi() {
		return GroupedOpenApi.builder()
			.group("Member")
			.pathsToMatch("/members/*")
			.build();
	}

	@Bean
	public GroupedOpenApi managerApi() {
		return GroupedOpenApi.builder()
			.group("Manager")
			.pathsToMatch("/manager/*")
			.build();
	}

	@Bean
	public GroupedOpenApi menuApi() {
		return GroupedOpenApi.builder()
			.group("Menu")
			.pathsToMatch("/menu", "/menu/*")
			.build();
	}

	@Bean
	public GroupedOpenApi paymentApi() {
		return GroupedOpenApi.builder()
			.group("Payment")
			.pathsToMatch("/payment", "/payment/*", "/payment/*/*")
			.build();
	}

	@Bean
	public GroupedOpenApi ticketApi() {
		return GroupedOpenApi.builder()
			.group("Ticket")
			.pathsToMatch("/ticket", "/ticket/*")
			.build();
	}
}
