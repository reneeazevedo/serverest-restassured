package br.com.serverest.pojos.responses;

import lombok.*;

import java.util.ArrayList;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootProdutos {
    public int quantidade;
    public ArrayList<ProdutosResponse> produtos;

}
