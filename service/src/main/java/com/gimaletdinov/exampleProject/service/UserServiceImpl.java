package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.exception.NoSuchObjectException;
import com.gimaletdinov.exampleProject.mapper.UserMapper;
import com.gimaletdinov.exampleProject.dao.UserRepository;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;
import com.gimaletdinov.exampleProject.service.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Класс реализация интерфейса UserService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final OfficeService officeService;

    private final CountryService countryService;

    private final DocumentTypeService documentTypeService;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OfficeService officeService, CountryService countryService, DocumentTypeService documentTypeService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.officeService = officeService;
        this.countryService = countryService;
        this.documentTypeService = documentTypeService;
        this.userMapper = userMapper;
    }

    /**
     * @see UserService#getAllUsersByPredicat(UserListRequestDto)
     * @param userListRequestDto
     * @return
     */
    @Override
    @Transactional
    public List<UserListResponseDto> getAllUsersByPredicat(UserListRequestDto userListRequestDto) {
        officeService.getOfficeByIdFromRepository(userListRequestDto.getOfficeId());
        countryService.getCountryByCode(userListRequestDto.getCountryCode());
        documentTypeService.getDocumentTypeByCode(userListRequestDto.getDocCode());

        List<User> userList = userRepository.findAll(UserSpecification.userSpecification(userListRequestDto));

        if (userList.isEmpty()){
            throw new NoSuchObjectException("Нет пользователей с такими параметрами.");
        }

        List<UserListResponseDto> userListResponseDtoList = userMapper.toResponseDtoList(userList);
        return userListResponseDtoList;
    }

    /**
     * @see UserService#getUserById(int)
     * @param id
     * @return
     */
    @Override
    @Transactional
    public UserResponseDto getUserById(int id) {
        User user = getUserByIdFromRepository(id);

        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return userResponseDto;
    }

    /**
     * @see UserService#updateUser(UserUpdateRequestDto)
     * @param userUpdateRequestDto
     * @return
     */
    @Override
    @Transactional
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {

        User user = getUserByIdFromRepository(userUpdateRequestDto.getId());

        userMapper.updateModel(userUpdateRequestDto, user);

        Office office = officeService.getOfficeByIdFromRepository(userUpdateRequestDto.getOfficeId());
        user.setOffice(office);

        Country country = countryService.getCountryByCode(userUpdateRequestDto.getCountryCode());
        user.setCountry(country);

        userRepository.save(user);
    }

    /**
     * @see UserService#saveUser(UserSaveRequestDto)
     * @param userSaveRequestDto
     * @return
     */
    @Override
    @Transactional
    public void saveUser(UserSaveRequestDto userSaveRequestDto) {

        User user = userMapper.toModel(userSaveRequestDto);

        Office office = officeService.getOfficeByIdFromRepository(userSaveRequestDto.getOfficeId());
        user.setOffice(office);

        Country country = countryService.getCountryByCode(userSaveRequestDto.getCountryCode());
        user.setCountry(country);

        DocumentType documentType = documentTypeService.getDocumentTypeByCode(userSaveRequestDto.getDocCode());
        Document document = user.getDocument();
        document.setDocumentType(documentType);
        document.setUser(user);

        //user.setDocument(document);

        userRepository.save(user);
    }

    /**
     * @see UserService#getUserByIdFromRepository(int)
     * @param id NoSuchObjectException ("Нет пользователя с id = " + id)
     * @throws
     * @return
     */
    @Override
    @Transactional
    public User getUserByIdFromRepository(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new NoSuchObjectException("Нет пользователя с id = " + id);
        }

        return optionalUser.get();
    }
}
