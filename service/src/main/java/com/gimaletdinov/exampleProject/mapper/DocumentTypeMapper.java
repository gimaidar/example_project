package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.model.DocumentType;
import org.mapstruct.Mapper;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;

import java.util.List;

/**
 * Маппер для сущности Тип документа
 */
@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    /**
     * Метод для маппинга DocumentType в формат ответа DocumentTypeListResponseDto
     * @param documentType
     * @return DocumentTypeListResponseDto
     */
    DocumentTypeListResponseDto toResponseDto(DocumentType documentType);

    /**
     * Метод для маппинга спсика DocumentType в формат ответа список DocumentTypeListResponseDto
     * @param documentType
     * @return спсиок DocumentTypeListResponseDto
     */
    List<DocumentTypeListResponseDto> toResponseDtoList(List<DocumentType> documentType);
}
