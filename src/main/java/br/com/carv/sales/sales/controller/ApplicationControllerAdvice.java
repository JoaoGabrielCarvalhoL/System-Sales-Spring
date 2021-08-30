package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.exceptions.Errors;
import br.com.carv.sales.sales.exceptions.RuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleRuleException(RuleException err) {
        String errorMessage = err.getMessage();
        return new Errors(errorMessage);
    }



}
