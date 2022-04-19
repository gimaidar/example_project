package com.gimaletdinov.exampleProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectDataResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.service.OfficeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOfficeListRequestDto;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOfficeListResponseDtoList;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOfficeResponseDto;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOfficeSaveRequestDto;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOfficeUpdateRequestDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationResponseDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationSaveRequestDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationUpdateRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfficeController.class)
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OfficeService officeService;

    private static final String BASE_URL_REQUEST = "/api/office";
    private static final String GET_OFFICE_BY_ID_URL_REQUEST = BASE_URL_REQUEST + "/";
    private static final String GET_ALL_OFFICES_BY_PREDICAT_URL_REQUEST = BASE_URL_REQUEST + "/list";
    private static final String UPDATE_OFFICE_URL_REQUEST = BASE_URL_REQUEST + "/update";
    private static final String SAVE_OFFICE_URL_REQUEST = BASE_URL_REQUEST + "/save";

    @Test
    void getAllOfficesByPredicat() throws Exception {
        //Given
        OfficeListRequestDto officeListRequestDto = getPopulateOfficeListRequestDto();

        List<OfficeListResponseDto> officeListResponseDtoList = getPopulateOfficeListResponseDtoList();

        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
        dataResponseDto.setData(officeListResponseDtoList);

        when(officeService.getAllOfficesByPredicat(officeListRequestDto)).thenReturn(officeListResponseDtoList);

        //When
        mockMvc.perform(
                        get(GET_ALL_OFFICES_BY_PREDICAT_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(officeListRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
    }

    @Test
    void getOrganizationById() throws Exception {
        //Given
        OfficeResponseDto officeResponseDto = getPopulateOfficeResponseDto();

        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
        dataResponseDto.setData(officeResponseDto);

        when(officeService.getOfficeById(TEST_OFFICE_ID)).thenReturn(officeResponseDto);

        //When
        mockMvc.perform(
                        get(GET_OFFICE_BY_ID_URL_REQUEST + TEST_OFFICE_ID)
                                .content(objectMapper.writeValueAsString(officeResponseDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
    }

    @Test
    void updateOrganization() throws Exception {
        //Given
        OfficeUpdateRequestDto officeUpdateRequestDto = getPopulateOfficeUpdateRequestDto();

        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();

        //When
        mockMvc.perform(
                        post(UPDATE_OFFICE_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(officeUpdateRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
    }

    @Test
    void saveOrganization() throws Exception {
        //Given
        OfficeSaveRequestDto officeSaveRequestDto = getPopulateOfficeSaveRequestDto();

        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();

        //When
        mockMvc.perform(
                        post(SAVE_OFFICE_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(officeSaveRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
    }
}