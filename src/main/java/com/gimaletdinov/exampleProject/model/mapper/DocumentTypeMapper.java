package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    @Mapping(source = "id", target = "code")
    DocumentTypeListResponseDto toResponseDto(DocumentType documentType);

    List<DocumentTypeListResponseDto> toResponseDtoList(List<DocumentType> documentType);
}
