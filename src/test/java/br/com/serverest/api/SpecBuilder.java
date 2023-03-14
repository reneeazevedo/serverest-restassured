package br.com.serverest.api;

import br.com.serverest.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.nio.charset.StandardCharsets;

import static br.com.serverest.api.Routes.LOGIN;
import static br.com.serverest.utils.ConfigLoader.*;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class SpecBuilder {
    public static String getToken(){
        return given().log().all().body("{ \"email\": \"fulano@qa.com\", \"password\": \"teste\" }").
                when().post(LOGIN).then().log().all().extract().response().jsonPath().getString("authorization");

    }

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                 .addFilter(new AllureRestAssured())
                //.setBaseUri("https://serverest.dev")
                .setBaseUri(getInstance().getUrl())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setConfig(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true)))
                //.setConfig(RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("ISO-8859-1")))
                .build();

    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .setDefaultParser(Parser.JSON)
                .build();

    }

}
