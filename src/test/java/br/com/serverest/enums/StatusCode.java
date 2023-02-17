package br.com.serverest.enums;

public enum StatusCode {
    CODE_200(200, "Login realizado com sucesso");
    private final int code;
    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
