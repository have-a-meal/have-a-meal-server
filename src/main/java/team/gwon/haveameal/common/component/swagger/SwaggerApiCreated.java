package team.gwon.haveameal.common.component.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation()
@ApiResponses(value = {
	@ApiResponse(responseCode = "201")
})
public @interface SwaggerApiCreated {
	String summary() default "";

	Class<?> implementation() default Void.class;
}
