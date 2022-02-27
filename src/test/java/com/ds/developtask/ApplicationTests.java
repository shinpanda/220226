package com.ds.developtask;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

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
		requestData.put("id", "ds");
		requestData.put("email", "dsound72@gmail.com");
		requestData.put("password", "test");
		
		// 준비
		RestAssured.given()
					.contentType("application/json")
					.body(requestData).log().all()
		// 실행
					.when()
						.post("/signup")
		// 검증
					.then()
						.statusCode(201)
						.assertThat().body("id", equalTo("ds"))
						.assertThat().body("email", equalTo("dsound72@gmail.com"))
						.log().all();
	}
}
