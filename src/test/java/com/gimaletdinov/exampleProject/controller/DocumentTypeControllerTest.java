package com.gimaletdinov.exampleProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectDataResponseDto;
import com.gimaletdinov.exampleProject.service.DocumentTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.gimaletdinov.exampleProject.Helper.DocumentTestHelper.getPopulateDocumentTypeListResponseDtoList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentTypeController.class)
class DocumentTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DocumentTypeService documentTypeService;

    private static final String GET_ALL_DOCUMENT_TYPES_URL_REQUEST = "/api/docs";

    @Test
    void getAllOrganizationsByPredicat() throws Exception {
        //Given
        List<DocumentTypeListResponseDto> documentTypeListResponseDtoList = getPopulateDocumentTypeListResponseDtoList();

        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
        dataResponseDto.setData(documentTypeListResponseDtoList);

        when(documentTypeService.getAllDocumentTypes()).thenReturn(documentTypeListResponseDtoList);

        //When
        mockMvc.perform(
                        get(GET_ALL_DOCUMENT_TYPES_URL_REQUEST))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
    }
}