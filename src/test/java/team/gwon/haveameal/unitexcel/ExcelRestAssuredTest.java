package team.gwon.haveameal.unitexcel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExcelRestAssuredTest {

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void assuredaTest() throws Exception {
		// String fileName = "april week 1 menu";
		// String contentType = "xlsx";
		// String filePath = "src/test/resources/excel/april week 1 menu.xlsx";
		// MockMultipartFile multipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType,
		// 	new FileInputStream(filePath));
		// URL resourceUrl = getClass().getClassLoader().getResource("april week 1 menu.xlsx");
		Resource resource = new ClassPathResource("excels/testtextfiles.txt");
		// Resource resource = new ClassPathResource("excels/april week 1 menus.xlsx");
		// JSONObject requestBody = new JSONObject();
		// requestBody.put("excel", resource.getFile());

		ExtractableResponse<Response> response = RestAssured.given().log().all()
			// .body(requestBody.toString())
			// .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart(resource.getFile())
			// .multiPart("multipartFile", resource.getFile(), MediaType.MULTIPART_FORM_DATA_VALUE)
			.when()
			.post("/manager/excel")
			.then()
			.log().all()
			.extract();
		// // given
		// JSONObject requestBody = new JSONObject();
		// requestBody.put("content", "이것은 테스트");
		//
		// // when
		// RequestSpecification request = RestAssured.given();
		// Response response = request
		// 	.body(requestBody.toString())
		// 	.contentType("application/json")
		// 	.post("/todos");
		assertEquals(200, response.statusCode());
	}

	@Test
	void naver_check() throws Exception {
		Resource resource = new ClassPathResource("excels/april week 1 menus_need_check.xlsx");
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart(resource.getFile())
			.when()
			.post("/manager/excel")
			.then()
			.log().all()
			.extract();
		assertEquals(200, response.statusCode());

	}

	@Test
	void restAssured_PutcontrollerTest() throws Exception {
		Resource resource = new ClassPathResource("excels/april week 1 newmenus.xlsx");
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.multiPart(resource.getFile())
			.when()
			.put("/manager/excel")
			.then()
			.log().all()
			.extract();
		assertEquals(200, response.statusCode());
	}

	@Test
	void restAssured_oneDayMenuTest() throws Exception {
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.when()
			.get("/Menu/2024-03-01")
			.then().log().all().extract();
		assertEquals(200, response.statusCode());
	}

	@Test
	void restAssured_nullDateTest() throws Exception {
		ExtractableResponse<Response> response = RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.when()
			.get("/Menu")
			.then().log().all().extract();
		assertEquals(404, response.statusCode());
	}
}
