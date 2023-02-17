package br.com.serverest.pojos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosResponseRoot {
    public int quantidade;
    public ArrayList<UsuariosResponse> usuarios;
}
