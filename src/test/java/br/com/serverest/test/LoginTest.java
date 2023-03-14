package br.com.serverest.test;

import br.com.serverest.enums.StatusCode;
import br.com.serverest.pojos.requests.Login;
import br.com.serverest.pojos.responses.LoginError;
import br.com.serverest.pojos.responses.LoginErrorMessage;
import br.com.serverest.pojos.responses.LoginResponseSuccess;
import br.com.serverest.utils.ExcelReader;
import br.com.serverest.utils.NormalizerString;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Utf8;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

import static br.com.serverest.api.ServeRest.*;
import static br.com.serverest.factories.LoginFactory.*;
import static br.com.serverest.utils.JsonReader.*;
import static br.com.serverest.utils.NormalizerString.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest extends BaseTest {
    @Test
    public void validarJsonSchema(){
        postLogin(loginCompleto())
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("jsonSchemaLogin.json"));

    }
    @Test
    public void readExcelLogin() throws IOException {
        JSONObject jsonObject = ExcelReader.readExcel("src/test/resources/", "user.xlsx", "Pagina1");
        System.out.println(jsonObject);
        postLogin(jsonObject.toString())
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonSchemaLogin.json"));

    }
    @Test
    public void serializationJavaObjectToJsonObjectUsingJacksonApi() throws IOException {
        Login loginRequest = Login.builder().email("fulano@qa.com").password("teste").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String convertedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginRequest);
        Response response = postLogin(convertedJson);
        Assert.assertEquals(response.getStatusCode(), 200);
        String userDir = System.getProperty("user.dir");
        File outputJsonFile = new File(userDir + "/src/test/resources/EmployeePayload.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputJsonFile, loginRequest);

    }
    @Test
    public void SerializeJavaToJsonObjectUsingGSON() throws IOException {
        Login loginRequest = Login.builder().email("fulano@qa.com").password("teste").build();
        Gson gson = new Gson();
        String convertedJson = gson.toJson(loginRequest);
        Response response = postLogin(convertedJson);
        Assert.assertEquals(response.getStatusCode(), 200);



    }
    /*Lendo arquivo json e passando no payload*/
    @Test
    public void loginPayloadJsonFile() throws IOException, ParseException {
        Object payload = readJsonFile("src/test/resources/usuario.json");
        Response response = postLogin(payload);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Test
    public void loginDeserializeJsonToJava() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/usuario.json");
        Login login = objectMapper.readValue(file, Login.class);
        Response response = postLogin(login);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Test
    public void loginPayloadObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("email","fulano@qa.com");
        payload.put("password","teste");

        Response response = postLogin(payload);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void loginSucesso() {
        Response response = postLogin(loginCompleto());
        Assert.assertEquals(response.getStatusCode(), StatusCode.CODE_200.getCode());
        LoginResponseSuccess loginSucesso = response.as(LoginResponseSuccess.class);
        Assert.assertNotNull(loginSucesso.getAuthorization());
        Assert.assertEquals(loginSucesso.getMessage(), StatusCode.CODE_200.getMsg());


    }

    @Test
    public void loginEmailIgualNull() {

        Response response = postLogin(emailIgualNull());
        Assert.assertEquals(response.getStatusCode(), 400);
        LoginError loginError = response.as(LoginError.class);
        Assert.assertEquals(loginError.getEmail(), "email deve ser uma string");

        response.then().body("email", equalTo("email deve ser uma string"));


    }

    @Test
    public void loginEmailIgualEmBranco() throws UnsupportedEncodingException {
        String msg = "email não pode ficar em branco";
        Response response = postLogin(emailEmBranco());

        response.then().body("email", equalTo(getNormalizerString(msg)));
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void loginPasswordIgualNull() {

        Response response = postLogin(passwordIgualNull());

        response.then().body("password", equalTo("password deve ser uma string"));
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void loginEmailEPasswordIgualNull() {
        String msgEmailNull ="email deve ser uma string";
        String msgPasswordNull ="password deve ser uma string";

        Response response = postLogin(emailPasswordIgualNull());

        response.then().body("email", equalTo(msgEmailNull));
        response.then().body("password", equalTo(msgPasswordNull));
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void loginPasswordEmBranco() throws UnsupportedEncodingException {
        String msgPasswordEmBranco ="password não pode ficar em branco";
        Response response = postLogin(passwordEmBranco());
        response.then().body("password", equalTo((getNormalizerString(msgPasswordEmBranco))));
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void loginEmailEPasswordEmBranco() throws UnsupportedEncodingException {
        String msgEmaildEmBranco ="email não pode ficar em branco";
        String msgPasswordEmBranco ="password não pode ficar em branco";

        Response response = postLogin(passwordEmailEmBranco());

        response.then().body("email", equalTo(getNormalizerString(msgEmaildEmBranco)));
        response.then().body("password", equalTo(getNormalizerString(msgPasswordEmBranco)));
        Assert.assertEquals(response.getStatusCode(), 400);

    }

    @Test
    public void loginEmailPasswordIncorreto() {

        Response response = postLogin(emailPasswordIncorreto());
        Assert.assertEquals(response.getStatusCode(), 401);
        LoginErrorMessage loginErrorMessage = response.as(LoginErrorMessage.class);

        Assert.assertEquals(loginErrorMessage.getMessage(), "Email e/ou senha inválidos2");


    }


}
