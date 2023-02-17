package br.com.serverest.pojos.responses;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseMessage {
    private String message;
    private String _id;
}
