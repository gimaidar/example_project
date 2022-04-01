package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.CountryRepository;
import com.gimaletdinov.exampleProject.dao.OfficeRepository;
import com.gimaletdinov.exampleProject.dao.UserRepository;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Office;
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

    private final CountryRepository countryRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeRepository officeRepository, CountryRepository countryRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.countryRepository = countryRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public List<UserListResponseDto> getAllUsersByPredicat(UserListRequestDto userListRequestDto) {

        User user = userMapper.toModel(userListRequestDto);

        List<User> userList = userRepository.getAllUsersByPredicat(user);

        if (userList.isEmpty()){
            throw new NoSuchObjectException("Нет пользователей с такими параметрами.");
        }

        List<UserListResponseDto> userListResponseDtoList = userMapper.toResponseDtoList(userList);
        return userListResponseDtoList;
    }

    @Override
    @Transactional
    public UserResponseDto getUserById(int id) {
        User user = userRepository.getUserById(id);

        if (user == null){
            throw new NoSuchObjectException("Нет пользователя с id = " + id);
        }

        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return userResponseDto;
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {

        User user = userRepository.getUserById(userUpdateRequestDto.getId());
        if (user == null){
            throw new NoSuchObjectException("Нет пользователя с id = " + userUpdateRequestDto.getId());
        }

        userMapper.updateModel(userUpdateRequestDto, user);

        Office office = officeRepository.getOfficeById(user.getOffice().getId());
        if (office == null){
            throw new NoSuchObjectException("Нет офиса с id = " + user.getOffice().getId());
        }

        Country country = countryRepository.getById(user.getCountry().getId());
        if (country == null){
            throw new NoSuchObjectException("Нет страны с id = " + user.getOffice().getId());
        }

        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void saveUser(UserSaveRequestDto userSaveRequestDto) {

        User user = userMapper.toModel(userSaveRequestDto);

        Office office = officeRepository.getOfficeById(user.getOffice().getId());
        if (office == null){
            throw new NoSuchObjectException("Нет офиса с id = " + user.getOffice().getId());
        }

        Country country = countryRepository.getById(user.getCountry().getId());
        if (country == null){
            throw new NoSuchObjectException("Нет страны с id = " + user.getOffice().getId());
        }

        userRepository.saveUser(user);
    }
}
