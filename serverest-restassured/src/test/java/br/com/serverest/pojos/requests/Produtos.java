package br.com.serverest.pojos.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Produtos {
    private String nome;
    private String preco;
    private String descricao;
    private String quantidade;

}
