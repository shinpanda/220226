package com.ds.developtask;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

/*
서버 API 기능
- 회원가입
- 로그인
- 로그아웃
- 상품 조회
- 상품 주문
- 회원 주문 내역 조회
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	int port;
	
	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}
	
	@Test
	void 회원_가입_성공() {
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("email", "test@test.com");
		requestData.put("password", "test");
		
		// 준비
		RestAssured.given()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.body(requestData)
		// 실행
					.when()
						.post("/signup")
		// 검증
					.then()
						.statusCode(HttpStatus.OK.value())
						.assertThat()
						.body("email", equalTo("test@test.com"));
	}
	
	@Test
	void 로그인_성공() {
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("email", "dsound72@gmail.com");
		requestData.put("password", "test");

		// 준비
		RestAssured.given()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.body(requestData)
		// 실행
					.when()
						.post("/login")
		// 검증
					.then()
						.statusCode(HttpStatus.OK.value());
	}
	

	@Test
	void 로그아웃_성공() {
		// TODO 구현 필요
	}
	
	@Test
	void 상품_조회_성공() { // TODO 로그인시 생성되는 Token 값 같이 넘겨주어야 함.
		String token = getAccessToken();
		// 준비
		RestAssured.given()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.header("X-AUTH-TOKEN", token)
		// 실행
					.when()
						.get("/product/1")
		// 검증
					.then()
						.statusCode(HttpStatus.OK.value())
						.assertThat()
						.body("id", equalTo(1));
	}
	
	@Test
	void 상품_주문_성공() { // TODO 로그인시 생성되는 Token 값 같이 넘겨주어야 함.
		// 준비
		String token = getAccessToken();

		Map<String, Object> requestData = new HashMap<>();
		requestData.put("productId", 1L);
		requestData.put("userEmail", "dsound72@gmail.com");
				
		RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(requestData)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.header("X-AUTH-TOKEN", token)
		// 실행
				.when()
					.post("/order")
		// 검증
				.then()
					.statusCode(HttpStatus.OK.value())
					.assertThat()
					.body("id", equalTo(1));
	}

	@Test
	void 회원_주문_내역_조회_성공(){
		// 준비
		String token = getAccessToken();

		RestAssured.given()
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.header("X-AUTH-TOKEN", token)
				// 실행
				.when()
				.post("/order/user")
				// 검증
				.then()
				.statusCode(HttpStatus.OK.value());
	}
	
	private String getAccessToken() {
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("email", "dsound72@gmail.com");
		requestData.put("password", "test");

		Response response = RestAssured.given()
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.body(requestData)
					.when()
						.post("/login");
		return response.getBody().asString();
	}
	
}
