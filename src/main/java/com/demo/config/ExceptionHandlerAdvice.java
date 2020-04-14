package com.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.nhsoft.provider.common.ErrorCode;
import com.nhsoft.provider.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private ObjectMapper objectMapper = new ObjectMapper();{
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) throws JsonProcessingException {
        Response response;
        HttpStatus status;
        if(e instanceof DemoException) {
            DemoException exception = (DemoException) e;
            response = Response.error(exception.getCode(), exception.getMessage());
            status = HttpStatus.OK;
        } else {
            log.error(e.getMessage(), e);
            response = Response.error(ErrorCode.UNKNOWN_ERROR.getCode(), e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(response));
    }
}
