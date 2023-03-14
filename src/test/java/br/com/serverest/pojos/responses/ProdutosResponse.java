package br.com.serverest.pojos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties("imagem")
public class ProdutosResponse {
    public int preco;
    public String nome;
    public int quantidade;
    public String descricao;
    public String _id;
    public String imagem;
}