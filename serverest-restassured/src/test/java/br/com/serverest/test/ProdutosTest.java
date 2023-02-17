package br.com.serverest.test;

import br.com.serverest.pojos.requests.Produtos;
import br.com.serverest.pojos.responses.ProdutoResponse;
import br.com.serverest.pojos.responses.ProdutoResponseMessage;
import br.com.serverest.pojos.responses.ProdutosResponse;
import br.com.serverest.pojos.responses.RootProdutos;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static br.com.serverest.api.ServeRest.*;
import static br.com.serverest.pojos.requests.Produtos.*;
import static br.com.serverest.utils.FakerData.*;

public class ProdutosTest extends BaseTest {

    @Test
    public void cadastrarProdutoComSucessoComMap(){

        //criando payload com MAP

        Map<String, Object> payload = new HashMap<>();
        payload.put("nome", nomeProduto());
        payload.put("preco",precoProduto());
        payload.put("descricao", descricaoProduto());
        payload.put("quantidade",quantidade());

        postProdutos(payload);

    }
    @Test
    public void cadastrarProdutoComSucessoComPOJO(){

        final Produtos payload = builder()
                .nome(nomeProduto())
                .preco(precoProduto())
                .descricao(descricaoProduto())
                .quantidade(quantidade())
                .build();

        Response response = postProdutos(payload);
        Assert.assertEquals(response.getStatusCode(), 201);
        ProdutoResponseMessage produtoResponseMessage = response.as(ProdutoResponseMessage.class);
        Assert.assertEquals(produtoResponseMessage.getMessage(),"Cadastro realizado com sucesso");
        Assert.assertNotNull(produtoResponseMessage.get_id());


    }
    /*Utilizacao JsonPath para pegar o id do produto cadatrado*/
    @Test
    public void findProdutoById(){
        final Produtos payload = builder()
                .nome(nomeProduto())
                .preco(precoProduto())
                .descricao(descricaoProduto())
                .quantidade(quantidade())
                .build();
        Response responsePostProdutos = postProdutos(payload);
        String id = responsePostProdutos.jsonPath().getString("_id");
        Response responseProdutoById = getProdutoById(id);
        ProdutoResponse produtoResponse = responseProdutoById.as(ProdutoResponse.class);

        Assert.assertEquals(responseProdutoById.getStatusCode(), 200);
        Assert.assertEquals(produtoResponse.getNome(), payload.getNome());
        Assert.assertNotNull(produtoResponse.get_id());
        Assert.assertEquals(produtoResponse.getDescricao(), payload.getDescricao());
        Assert.assertEquals(produtoResponse.getPreco(), Integer.parseInt(payload.getPreco()));
        Assert.assertEquals(produtoResponse.getQuantidade(), Integer.parseInt(payload.getQuantidade()));



    }
    @Test
    public void listarTodosProdutos(){
        //Desserializando response body
        Response response = getAllProdutos();
        RootProdutos rootProdutos = response.as(RootProdutos.class);
        ArrayList<ProdutosResponse> produtos = rootProdutos.getProdutos();

        for (ProdutosResponse produto:produtos
             ) {
            System.out.println("id: " + produto._id + " Nome: " +produto.nome+ "" +produto.descricao+ " Preço: " +produto.preco+ " Imagem: " +produto);

        }

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(rootProdutos.getQuantidade()>0);

    }

}
