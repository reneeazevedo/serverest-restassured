package br.com.serverest.pojos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Usuarios {
    private String nome;
    private String email;
    private String password;
    private String administrador;

}
