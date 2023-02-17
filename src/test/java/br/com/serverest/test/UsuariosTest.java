package br.com.serverest.test;

import br.com.serverest.api.ServeRest;
import br.com.serverest.pojos.requests.Usuarios;
import br.com.serverest.pojos.responses.UsuariosResponse;
import br.com.serverest.pojos.responses.UsuariosResponseRoot;
import br.com.serverest.utils.FakerData;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static br.com.serverest.api.ServeRest.*;

public class UsuariosTest extends BaseTest{

    @Test
    public void ValidateSearchAllUsers() throws InterruptedException {
        Usuarios usuarios = Usuarios.builder().nome(FakerData.nomeUsuario()).email(FakerData.email()).password(FakerData.password()).administrador("true").build();
        String idUsuario = postUsuarios(usuarios).body().jsonPath().getString("_id");
        Response response = getUsuarios();
        UsuariosResponseRoot usuariosResponseRoot = response.as(UsuariosResponseRoot.class);

        boolean userFound = false;
        String nome = null;
        String password = null;
        String email = null;
        String id = null;

        for (UsuariosResponse u:usuariosResponseRoot.getUsuarios()) {
            if(idUsuario.equals(u.get_id())) {
                userFound = true;
                nome = u.getNome();
                password = u.getPassword();
                email = u.getEmail();
                id =  u.get_id();
                break;
            }
        }
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(nome, usuarios.getNome());
        Assert.assertEquals(email, usuarios.getEmail());
        Assert.assertEquals(password, usuarios.getPassword());
        Assert.assertNotNull(id);
        Assert.assertTrue(userFound);


    }
    @Test
    public void ValidatePostUser(){
        Usuarios usuarios = Usuarios.builder()
                .nome(FakerData.nomeUsuario())
                .email(FakerData.email())
                .password(FakerData.password())
                .administrador("true")
                .build();
        Response response = postUsuarios(usuarios);
        response.then().assertThat().statusCode(201);


    }
}
