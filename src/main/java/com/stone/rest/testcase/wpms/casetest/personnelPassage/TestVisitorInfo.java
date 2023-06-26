package com.stone.rest.testcase.wpms.casetest.personnelPassage;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * @author guocang.shi
 */
public class TestVisitorInfo {

    @Test
    void test_demo() {

        // 自定义HttpClientConfig对象
        // 设置响应超时时长为3秒，单位是毫秒
        HttpClientConfig clientConfig = HttpClientConfig
                .httpClientConfig()
                .setParam("http.socket.timeout", 3000);
        // 定义RestAssuredConfig对象
        // 传入自定义的HttpClientConfig对象
        RestAssuredConfig myTimeout = RestAssuredConfig
                .config()
                .httpClient(clientConfig);
        String jsonData = "{\"visitorName\":\"\",\"visitorMobile\":\"\",\"visitedName\":\"\",\"startDate\":\"2023-05-27 00:00:00\",\"endDate\":\"2023-06-26 23:59:59\",\"pageNo\":1,\"limit\":10}";
        given()
                .config(myTimeout)
                .header("Authorization","bearer sf-5c22b76f-01d6-4b72-8db9-17b5bb379a92").
                body(jsonData).contentType(ContentType.JSON).
        when()
                .post("http://10.171.197.10:30365/api/wpms-service/"+"visitorInfo/queryPage")
        .then()
                .statusCode(200)
                .body("data.list", Matchers.notNullValue()) //验证值不为空
                .log().all();
    }

    @Test
    void test_Schema()
    {
        // 自定义HttpClientConfig对象
        // 设置响应超时时长为3秒，单位是毫秒
        HttpClientConfig clientConfig = HttpClientConfig
                .httpClientConfig()
                .setParam("http.socket.timeout", 3000);
        // 定义RestAssuredConfig对象
        // 传入自定义的HttpClientConfig对象
        RestAssuredConfig myTimeout = RestAssuredConfig
                .config()
                .httpClient(clientConfig);
        String jsonData = "{\"visitorName\":\"\",\"visitorMobile\":\"\",\"visitedName\":\"\",\"startDate\":\"2023-05-27 00:00:00\",\"endDate\":\"2023-06-26 23:59:59\",\"pageNo\":1,\"limit\":10}";
        given()
                .config(myTimeout)
                .header("Authorization","bearer sf-5c22b76f-01d6-4b72-8db9-17b5bb379a92").
                body(jsonData).contentType(ContentType.JSON).
                when()
                .post("http://10.171.197.10:30365/api/wpms-service/"+"visitorInfo/queryPage").
        then()
                .statusCode(200)
                .assertThat()
                .body("code",lessThanOrEqualTo(1))  //值不大于 1
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VisitorInfo.json"))
                //.log().all()
                ;
    }



}
