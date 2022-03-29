package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OfficeRepository;
import com.gimaletdinov.exampleProject.dao.UserRepository;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.User;
import com.gimaletdinov.exampleProject.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeRepository officeRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public List<UserListResponseDto> getAllUsersByPredicat(UserListRequestDto userListRequestDto) {

        User user = userMapper.toModel(userListRequestDto);
        //user.setOffice(officeRepository.getOfficeById(user.getOffice().getId()));

        List<User> userList = userRepository.getAllUsersByPredicat(user);

        List<UserListResponseDto> userListResponseDtoList = userMapper.toResponseDtoList(userList);
        return userListResponseDtoList;
    }

    @Override
    @Transactional
    public UserResponseDto getUserById(int id) {
        User user = userRepository.getUserById(id);

        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return userResponseDto;
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {

        User user = userRepository.getUserById(userUpdateRequestDto.getId());

        userMapper.updateModel(userUpdateRequestDto, user);

        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void saveUser(UserSaveRequestDto userSaveRequestDto) {

        User user = userMapper.toModel(userSaveRequestDto);

        userRepository.saveUser(user);
    }
}
