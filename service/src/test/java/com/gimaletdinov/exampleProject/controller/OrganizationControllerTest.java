//package com.gimaletdinov.exampleProject.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gimaletdinov.exampleProject.controller.OrganizationController;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
//import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
//import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
//import com.gimaletdinov.exampleProject.dto.response.ObjectDataResponseDto;
//import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
//import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
//import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
//import com.gimaletdinov.exampleProject.service.OrganizationService;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(OrganizationController.class)
//class OrganizationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private OrganizationService organizationService;
//
//    private static final String BASE_URL_REQUEST = "/api/organization";
//    private static final String GET_ORGANIZATION_BY_ID_URL_REQUEST = BASE_URL_REQUEST + "/";
//    private static final String GET_ALL_ORGANIZATIONS_BY_PREDICAT_URL_REQUEST = BASE_URL_REQUEST + "/list";
//    private static final String UPDATE_ORGANIZATION_URL_REQUEST = BASE_URL_REQUEST + "/update";
//    private static final String SAVE_ORGANIZATION_URL_REQUEST = BASE_URL_REQUEST + "/save";
//
//    @Test
//    void getAllOrganizationsByPredicat() throws Exception {
//        //Given
//        OrganizationListRequestDto organizationListRequestDto = getPopulateOrganizationListRequestDto();
//
//        List<OrganizationListResponseDto> organizationListResponseDtoList = getPopulateOrganizationListResponseDtoList();
//
//        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
//        dataResponseDto.setData(organizationListResponseDtoList);
//
//        when(organizationService.getAllOrganizationsByPredicat(organizationListRequestDto)).thenReturn(organizationListResponseDtoList);
//
//        //When
//        mockMvc.perform(
//                        get(GET_ALL_ORGANIZATIONS_BY_PREDICAT_URL_REQUEST)
//                                .content(objectMapper.writeValueAsString(organizationListRequestDto))
//                                .contentType(MediaType.APPLICATION_JSON))
//                //Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
//    }
//
//    @Test
//    void getOrganizationById() throws Exception {
//        //Given
//        OrganizationResponseDto organizationResponseDto = getPopulateOrganizationResponseDto();
//
//        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
//        dataResponseDto.setData(organizationResponseDto);
//
//        when(organizationService.getOrganizationById(TEST_ORG_ID)).thenReturn(organizationResponseDto);
//
//        //When
//        mockMvc.perform(
//                get(GET_ORGANIZATION_BY_ID_URL_REQUEST + TEST_ORG_ID)
//                        .content(objectMapper.writeValueAsString(organizationResponseDto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                //Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
//    }
//
//    @Test
//    void updateOrganization() throws Exception {
//        //Given
//        OrganizationUpdateRequestDto organizationUpdateRequestDto = getPopulateOrganizationUpdateRequestDto();
//
//        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();
//
//        //When
//        mockMvc.perform(
//                        post(UPDATE_ORGANIZATION_URL_REQUEST)
//                                .content(objectMapper.writeValueAsString(organizationUpdateRequestDto))
//                                .contentType(MediaType.APPLICATION_JSON))
//                //Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
//    }
//
//    @Test
//    void saveOrganization() throws Exception {
//        //Given
//        OrganizationSaveRequestDto organizationSaveRequestDto = getPopulateOrganizationSaveRequestDto();
//
//        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();
//
//        //When
//        mockMvc.perform(
//                        post(SAVE_ORGANIZATION_URL_REQUEST)
//                                .content(objectMapper.writeValueAsString(organizationSaveRequestDto))
//                                .contentType(MediaType.APPLICATION_JSON))
//                //Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
//    }
//}