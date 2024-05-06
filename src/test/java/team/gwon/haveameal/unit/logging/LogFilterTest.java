package team.gwon.haveameal.unit.logging;


import team.gwon.haveameal.common.logging.LogFilterTestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class LogFilterTest {
	@Autowired
	private MockMvc mockMvc;

	@DisplayName("LogFilter Get 테스트")
	@Test
	public void testLogFilterGet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/logTest"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
