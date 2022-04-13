package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(source = "id", target = "code")
    DocumentTypeListResponseDto toResponseDto(DocumentType documentType);

    /**
     * Метод для маппинга спсика DocumentType в формат ответа список DocumentTypeListResponseDto
     * @param documentType
     * @return спсиок DocumentTypeListResponseDto
     */
    List<DocumentTypeListResponseDto> toResponseDtoList(List<DocumentType> documentType);
}
