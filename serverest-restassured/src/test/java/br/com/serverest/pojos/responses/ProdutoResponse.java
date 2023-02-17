package br.com.serverest.pojos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
    private String _id;

}
