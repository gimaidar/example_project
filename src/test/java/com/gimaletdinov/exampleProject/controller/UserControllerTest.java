package com.gimaletdinov.exampleProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectDataResponseDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.service.UserService;
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
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.TEST_USER_ID;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserListRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserListResponseDtoList;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserResponseDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserSaveRequestDto;
import static com.gimaletdinov.exampleProject.dao.UserTestHelper.getPopulateUserUpdateRequestDto;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private static final String BASE_URL_REQUEST = "/api/user";
    private static final String GET_USER_BY_ID_URL_REQUEST = BASE_URL_REQUEST + "/";
    private static final String GET_ALL_USERS_BY_PREDICAT_URL_REQUEST = BASE_URL_REQUEST + "/list";
    private static final String UPDATE_USER_URL_REQUEST = BASE_URL_REQUEST + "/update";
    private static final String SAVE_USER_URL_REQUEST = BASE_URL_REQUEST + "/save";

    @Test
    void getAllUsersByPredicat() throws Exception {
        //Given
        UserListRequestDto userListRequestDto = getPopulateUserListRequestDto();

        List<UserListResponseDto> userListResponseDtoList = getPopulateUserListResponseDtoList();

        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
        dataResponseDto.setData(userListResponseDtoList);

        when(userService.getAllUsersByPredicat(userListRequestDto)).thenReturn(userListResponseDtoList);

        //When
        mockMvc.perform(
                        get(GET_ALL_USERS_BY_PREDICAT_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(userListRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
    }

    @Test
    void getUserById() throws Exception {
        //Given
        UserResponseDto userResponseDto = getPopulateUserResponseDto();

        ObjectDataResponseDto dataResponseDto = new ObjectDataResponseDto();
        dataResponseDto.setData(userResponseDto);

        when(userService.getUserById(TEST_USER_ID)).thenReturn(userResponseDto);

        //When
        mockMvc.perform(
                        get(GET_USER_BY_ID_URL_REQUEST + TEST_OFFICE_ID)
                                .content(objectMapper.writeValueAsString(userResponseDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dataResponseDto)));
    }

    @Test
    void updateUser() throws Exception {
        //Given
        UserUpdateRequestDto userUpdateRequestDto = getPopulateUserUpdateRequestDto();

        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();

        //When
        mockMvc.perform(
                        post(UPDATE_USER_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(userUpdateRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
    }

    @Test
    void saveUser() throws Exception {
        //Given
        UserSaveRequestDto userSaveRequestDto = getPopulateUserSaveRequestDto();

        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();

        //When
        mockMvc.perform(
                        post(SAVE_USER_URL_REQUEST)
                                .content(objectMapper.writeValueAsString(userSaveRequestDto))
                                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(objectSuccessResponseDto)));
    }
}