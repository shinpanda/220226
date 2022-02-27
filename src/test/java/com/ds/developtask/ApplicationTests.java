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
���� API ���
- ȸ������
- �α���
- �α׾ƿ�
- ��ǰ ��ȸ
- ��ǰ �ֹ�
- ȸ�� �ֹ� ���� ��ȸ

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
		
		// �غ�
		RestAssured.given()
					.contentType("application/json")
					.body(requestData).log().all()
		//����
					.when()
						.post("/signup")
		//����
					.then()
						.statusCode(201)
						.assertThat().body("id", equalTo("ds"))
						.assertThat().body("email", equalTo("dsound72@gmail.com"))
						.log().all();
	}
}
