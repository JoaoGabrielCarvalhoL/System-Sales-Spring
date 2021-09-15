package br.com.carv.sales.sales.dto;

public class TokenDto {
    private String loginUser;
    private String token;

    public TokenDto() {

    }

    public TokenDto(String loginUser, String token) {
        this.loginUser = loginUser;
        this.token = token;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
