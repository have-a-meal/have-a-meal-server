package team.gwon.haveameal.unit.logging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LogFilterTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("LogFilter Get 테스트")
	@Test
	public void testLogFilterGet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/logTest"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@DisplayName("LogFilter Post 테스트")
	@Test
	public void testLogFilterPost() throws Exception {
		LogFilterTestDto logFilterTestDto = LogFilterTestDto.builder()
			.userId("testId")
			.password("testPw")
			.name("test")
			.build();
		mockMvc.perform(MockMvcRequestBuilders.post("/test/logTest")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(logFilterTestDto)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}

	private <T> String toJson(T data) throws JsonProcessingException {
		return objectMapper.writeValueAsString(data);
	}
}
