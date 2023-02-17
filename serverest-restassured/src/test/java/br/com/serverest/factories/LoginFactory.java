package br.com.serverest.factories;

import br.com.serverest.pojos.requests.Login;

import static br.com.serverest.pojos.requests.Login.*;
import static br.com.serverest.utils.FakerData.*;

public class LoginFactory {

    public static Login loginCompleto() {
        return builder().email("fulano@qa.com").password("teste").build();
    }

    public static Login emailEmBranco() {
        return builder().email("").password("teste").build();
    }

    public static Login emailIgualNull() {
        return builder().password("teste").build();
    }

    public static Login passwordEmBranco() {
        return builder().email("fulano@qa.com").password("").build();
    }

    public static Login passwordIgualNull() {
        return builder().email("fulano@qa.com").build();
    }

    public static Login emailPasswordIgualNull() {
        return builder().build();
    }

    public static Login passwordEmailEmBranco() {
        return builder().email("").password("").build();
    }

    public static Login emailPasswordIncorreto() {
        return builder().email(email()).password(password()).build();
    }
}
