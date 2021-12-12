package com.hackathon.api.controller;

import com.hackathon.api.domain.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(Throwable.class)
//    @ResponseBody
    public ErrorResponse handleControllerException(HttpServletRequest req, Throwable ex) {

//        ResponseEntity.of()
//        ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>();


        return new ErrorResponse(ex.getMessage());
    }

}
