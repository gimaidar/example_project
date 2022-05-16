package com.gimaletdinov.exampleProject.controller.handler;

import com.gimaletdinov.exampleProject.exception.NoSuchObjectException;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.gimaletdinov.exampleProject.dto.response.ObjectErrorResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обработчик ошибок, возникших в программе
 * Все ошибки отпраляются пользователю в видео объекта ObjectErrorResponseDto
 */
@RestControllerAdvice
@Log4j2
public class ExceptionHandler {

    /**
     * Обработчик исключения NoSuchObjectException
     * @param exception
     * @return ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND)
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(NoSuchObjectException exception) {
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();
        objectErrorResponseDto.setError(exception.getMessage());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик исключения MethodArgumentNotValidException, возникающий при валидации DTO, используемые при запросе
     * В методе фильтруется текст ошибки, для отправки ошибки в удобном для пользователя виде
     * @param exception
     * @return ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND)
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(MethodArgumentNotValidException exception) {
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();

        List errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectErrorResponseDto.setError(errors.toString());

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик всех остальных исключений
     * В методе генерируется код ошибки, записывается в лог код ошибки с текстом ошибки,
     * пользователю отправляется ответ в виде "[код ошибки] - Неожиданная ошибка - [Время ошибки]"
     * @param exception
     * @return ResponseEntity<>(objectErrorResponseDto, HttpStatus.NOT_FOUND)
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ObjectErrorResponseDto> handleException(Exception exception) {

        //генерация кода ошибки и получение времени ошибки
        String errorCode = RandomStringUtils.randomAlphanumeric(10);
        LocalDateTime errorDateTime = LocalDateTime.now();

        //формирование сообщени об ошибке и запись в лог полной информации
        String errorMessage = errorCode + " - Неожиданная ошибка - ";
        log.error(errorMessage + exception.getMessage());

        //формирование ответа пользователю "[код ошибки] - Неожиданная ошибка - [Время ошибки]"
        ObjectErrorResponseDto objectErrorResponseDto = new ObjectErrorResponseDto();
        objectErrorResponseDto.setError(errorMessage + " [" + errorDateTime + "]");

        return new ResponseEntity<>(objectErrorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
