package com.gimaletdinov.exampleProject.controller.handler;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.response.ObjectErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(NoSuchObjectException exception){
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();
        objectErrorResponseDto.setError(exception.getMessage());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND);
    }
}
