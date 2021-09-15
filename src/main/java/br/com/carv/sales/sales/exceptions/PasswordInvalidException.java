package br.com.carv.sales.sales.exceptions;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException() {
        super("Invalid Password!");
    }
}
