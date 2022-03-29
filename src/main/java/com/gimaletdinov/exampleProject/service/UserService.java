package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserListResponseDto> getAllUsersByPredicat(UserListRequestDto userListRequestDto);

    UserResponseDto getUserById(int id);

    void updateUser(UserUpdateRequestDto userUpdateRequestDto);

    void saveUser(UserSaveRequestDto userSaveRequestDto);
}
