package br.com.carv.sales.sales.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Errors {

    private List<String> errors;

    public Errors(String errors) {
        this.errors = Arrays.asList(errors);
    }

    public Errors(List<String> messageError) {
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
