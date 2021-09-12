package br.com.carv.sales.sales.controller;

import br.com.carv.sales.sales.exceptions.Errors;
import br.com.carv.sales.sales.exceptions.RuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleRuleException(RuleException err) {
        String errorMessage = err.getMessage();
        return new Errors(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleMethodValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return new Errors(errors);
    }



}
