package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.DocumentDtoTestHelper.getPopulateDocumentType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DocumentTypeMapperImpl.class)
class DocumentTypeMapperTest {

    @Autowired
    private DocumentTypeMapper documentTypeMapper;

    @Test
    @DisplayName("Тест: toModel (Маппинг из DocumentType -> DocumentTypeListResponseDto)")
    void toResponseDto() {
        DocumentType documentType = getPopulateDocumentType();
        DocumentTypeListResponseDto documentTypeListResponseDto = documentTypeMapper.toResponseDto(documentType);

        assertAll(
                () -> assertThat(documentTypeListResponseDto.getCode()).isEqualTo(documentType.getCode()),
                () -> assertThat(documentTypeListResponseDto.getName()).isEqualTo(documentType.getName())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDtoList (Маппинг из List<DocumentType> ->  List<DocumentTypeListResponseDto>)")
    void toResponseDtoList() {
        DocumentType documentType = getPopulateDocumentType();
        List<DocumentType> documentTypeList = new ArrayList<>();
        documentTypeList.add(documentType);

        List<DocumentTypeListResponseDto> documentTypeListResponseDtoList = documentTypeMapper.toResponseDtoList(documentTypeList);
        DocumentTypeListResponseDto documentTypeListResponseDto = documentTypeListResponseDtoList.get(0);

        assertAll(
                () -> assertThat(documentTypeListResponseDto.getCode()).isEqualTo(documentType.getCode()),
                () -> assertThat(documentTypeListResponseDto.getName()).isEqualTo(documentType.getName())
        );
    }
}