package br.com.serverest.test;

import br.com.serverest.api.SpecBuilder;
import br.com.serverest.listeners.TestngListeners;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static br.com.serverest.api.SpecBuilder.*;

public class BaseTest {

    @BeforeSuite
    public void setUp() {


        RestAssured.requestSpecification = getRequestSpec();
        RestAssured.responseSpecification =getResponseSpec();


    }
}
