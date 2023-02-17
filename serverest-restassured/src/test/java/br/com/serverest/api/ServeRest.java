package br.com.serverest.api;

import br.com.serverest.pojos.requests.Usuarios;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import static br.com.serverest.api.Routes.*;
import static br.com.serverest.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class ServeRest {
    public static Response postLogin(Object payload) {
        return given()
                .body(payload)
                .when()
                .post(LOGIN)
                .then()
                .log().all()
                .extract().response();

    }
    public static Response getUsuarios(){
        return given().when().get(USUARIOS)
                .then()
                //.log().all()
                .extract().response();
    }

    public static Response postUsuarios(Usuarios usuarios){
        return given()
                .body(usuarios)
                    //.log().all()

                .when()
                    .post(USUARIOS)
                .then()
                    //.log().all()
                .extract().response();
    }
    public static Response getAllProdutos(){
        return given().when().get(PRODUTOS).then().log().all().extract().response();
    }
    public static Response postProdutos(Object payload) {
        return given()
                .header("Authorization",getToken())
                .log().all()
                .body(payload)
                .when()
                .post(PRODUTOS)
                .then()
                .log().all().extract().response();

    }
    public static Response getProdutoById(String id){
        return  given()
                .pathParam("_id",id)
                .when()
                    .get(PRODUTOS+"/"+"{_id}")
                .then().log().all().extract().response();

    }

}
