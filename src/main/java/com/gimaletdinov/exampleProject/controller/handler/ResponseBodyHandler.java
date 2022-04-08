package com.gimaletdinov.exampleProject.controller.handler;

import com.gimaletdinov.exampleProject.dto.response.ObjectDataResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectErrorResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Перехватчик ответов от контроллеров
 */
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * Метоад проверяет тело ответа от контроллера, если ответ типа ObjectErrorResponseDto, ObjectSuccessResponseDto,
     * то отправляет ответ пользователю напрямую, если нет, то оборачивает ответ в ObjectDataResponseDto
     * @param body Тело ответа от контроллера
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return ObjectDataResponseDto
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        //Если ответ "ошибка" или "успешно" то ответ отправляется напрямую
        if ((body instanceof ObjectErrorResponseDto) || (body instanceof ObjectSuccessResponseDto)){
            return body;
        }

        //другие ответы оборачиваются в в data
        ObjectDataResponseDto objectDataResponseDto = new ObjectDataResponseDto();
        objectDataResponseDto.setData(body);

        return objectDataResponseDto;
    }
}
