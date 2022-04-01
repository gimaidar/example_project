package com.gimaletdinov.exampleProject.controller.handler;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.response.ObjectErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(NoSuchObjectException exception) {
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();
        objectErrorResponseDto.setError(exception.getMessage());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(MethodArgumentNotValidException exception) {
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();

        // Получить все ошибки валидации в удобном для пользователя виде
        List errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectErrorResponseDto.setError(errors.toString());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(Exception exception) {
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();
        objectErrorResponseDto.setError(exception.getMessage());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
