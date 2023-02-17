package br.com.serverest.pojos.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuariosResponse {
    public String nome;
    public String email;
    public String password;
    public String administrador;
    public String _id;
}
